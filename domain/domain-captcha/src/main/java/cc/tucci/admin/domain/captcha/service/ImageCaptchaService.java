package cc.tucci.admin.domain.captcha.service;

import cc.tucci.admin.domain.captcha.entity.Captcha;
import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.core.exception.BizException;

import java.awt.image.BufferedImage;

/**
 * @author tucci
 */
public interface ImageCaptchaService {

    /**
     * 生成图片验证码
     *
     * @param type 验证码类型（方便扩展）
     * @param key  缓存图片验证码的key
     * @return BufferedImage
     */
    BufferedImage generate(Integer type, String key, String captcha);

    /**
     * 校验图片验证码
     *
     * @param type    验证码类型
     * @param key     缓存图片验证码的key
     * @param captcha 需要校验的验证码
     */
    void verify(Integer type, String key, String captcha);


    default String getCacheKey(int type, String key) {
        Assert.notEmpty(key, BizCode.IMAGE_CAPTCHA_ERROR);

        switch (type) {
            case Captcha.Type.SIGNIN:
                return Captcha.IMAGE_CAPTCHA_SIGNIN + key;
            default:
                throw new BizException(BizCode.IMAGE_CAPTCHA_ERROR);
        }
    }
}
