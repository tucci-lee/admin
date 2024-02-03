package cc.tucci.admin.domain.risk.service;

/**
 * @author tucci
 */
public interface SecurityService {

    String encode(String plaintext);

    String decode(String ciphertext);
}
