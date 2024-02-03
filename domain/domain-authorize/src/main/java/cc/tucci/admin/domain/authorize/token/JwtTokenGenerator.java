package cc.tucci.admin.domain.authorize.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tucci
 */
public class JwtTokenGenerator implements TokenGenerator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Algorithm algorithm;

    public JwtTokenGenerator(String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public JwtTokenGenerator(String publicKey, String privateKey) {
        try {
            byte[] decodePublicKey = Base64.getDecoder().decode(publicKey);
            byte[] decodePrivateKey = Base64.getDecoder().decode(privateKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(decodePublicKey));
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(decodePrivateKey));
            this.algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.warn("init JWT token codec error, rsa256 info error, use default algorithm");
            this.algorithm = Algorithm.HMAC256("");
        }
    }

    @Override
    public String generate(String subject) {
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return JWT.create()
                .withHeader(header)
                .withIssuedAt(new Date())
                .withSubject(subject)
                .sign(this.algorithm);
    }

}
