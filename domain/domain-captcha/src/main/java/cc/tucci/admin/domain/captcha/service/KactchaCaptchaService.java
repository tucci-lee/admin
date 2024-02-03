package cc.tucci.admin.domain.captcha.service;

import cc.tucci.admin.domain.captcha.entity.Captcha;
import cc.tucci.admin.domain.core.cache.CacheOperate;
import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import com.google.code.kaptcha.Producer;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @author tucci
 */
public class KactchaCaptchaService implements ImageCaptchaService {

    private final Producer producer;
    private final CacheOperate cacheOperate;

    public KactchaCaptchaService(Producer producer,
                                 CacheOperate cacheOperate) {
        this.producer = producer;
        this.cacheOperate = cacheOperate;
    }

    @Override
    public BufferedImage generate(Integer type, String key, String captcha) {
        String cacheKey = getCacheKey(type, key);
        cacheOperate.set(cacheKey, captcha.toLowerCase(), Captcha.IMAGE_CAPTCHA_EXPIRE);
        return producer.createImage(captcha);
    }

    @Override
    public void verify(Integer type, String key, String captcha) {
        Assert.notEmpty(captcha, BizCode.IMAGE_CAPTCHA_ERROR);
        String cacheKey = getCacheKey(type, key);
        String cacheCaptcha = cacheOperate.get(cacheKey);
        Assert.isTrue(Objects.equals(captcha.toLowerCase(), cacheCaptcha), BizCode.IMAGE_CAPTCHA_ERROR);
        cacheOperate.delete(cacheKey);
    }

}
