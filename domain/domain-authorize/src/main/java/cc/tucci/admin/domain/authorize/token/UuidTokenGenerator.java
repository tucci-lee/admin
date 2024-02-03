package cc.tucci.admin.domain.authorize.token;

import java.util.UUID;

/**
 * @author tucci
 */
public class UuidTokenGenerator implements TokenGenerator {

    @Override
    public String generate(String subject) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
