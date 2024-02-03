package cc.tucci.admin.domain.captcha.entity;

/**
 * @author tucci
 */
public class Captcha {

    public static final String IMAGE_CAPTCHA_SIGNIN = "captcha:image:signin:";
    public static final String IMAGE_CAPTCHA_SIGNUP = "captcha:image:signup:";
    public static final String IMAGE_CAPTCHA_PASSWORD = "captcha:image:password:";
    public static final String IMAGE_CAPTCHA_SECOND_PASSWORD = "captcha:image:second_password:";
    public static final String IMAGE_CAPTCHA_PHONE = "captcha:image:phone:";
    public static final String IMAGE_CAPTCHA_EMAIL = "captcha:image:email:";
    public static final int IMAGE_CAPTCHA_EXPIRE = 5 * 60;

    /**
     * 验证码类型
     * 101-登录
     */
    public interface Type {
        int SIGNIN = 101;
    }
}
