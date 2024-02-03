package cc.tucci.admin.domain.risk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author tucci
 */
public class RSASecurityService implements SecurityService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    static final String algorithm = "RSA";
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public RSASecurityService(String publicKey, String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] privateBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        this.privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        byte[] publicBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
        this.publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
    }

    @Override
    public String encode(String plaintext) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
            byte[] encodedBytes = cipher.doFinal(plaintext.getBytes());
            return Base64.getEncoder().encodeToString(encodedBytes);
        } catch (Exception e) {
            logger.error("RSA encode error", e);
            return null;
        }
    }

    @Override
    public String decode(String ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
            byte[] decodeBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
            return new String(decodeBytes);
        } catch (Exception e) {
            logger.error("RSA decode error", e);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //设置密钥大小，RSA算法的模长=最大加密数据的大小
        keyPairGenerator.initialize(1024);
        //调用函数生成公钥私钥对象（以对生成密钥）
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
    }
}
