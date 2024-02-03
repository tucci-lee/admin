package cc.tucci.admin.domain.authorize;

/**
 * 不使用Shiro自带的主体获取，适合架构、方便更换shiro
 *
 * @author tucci
 */
public class AuthorizeContextHolder {

    private static final ThreadLocal<Authorize> contextHolder = new ThreadLocal<>();

    public static void set(Authorize authorize) {
        contextHolder.set(authorize);
    }

    public static Authorize get() {
        return contextHolder.get();
    }

    public static Long getUid() {
        return contextHolder.get().getUid();
    }

    public static void clear() {
        contextHolder.remove();
    }

}
