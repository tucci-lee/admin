package cc.tucci.admin.domain.authorize.token;

/**
 * @author tucci
 */
public interface TokenGenerator {

    /**
     * 主题编码生成token
     *
     * @param subject 主题
     * @return token
     */
    String generate(String subject);

}
