package cc.tucci.admin.app.system.controller;

import cc.tucci.admin.app.system.dto.query.ImageCaptchaQuery;
import cc.tucci.admin.app.system.service.CaptchaAppService;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author tucci
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    private final CaptchaAppService captchaService;

    public CaptchaController(CaptchaAppService captchaService) {
        this.captchaService = captchaService;
    }

    /**
     * 生成图片验证码
     */
    @GetMapping("image")
    public Response image(ImageCaptchaQuery query) throws IOException {
        return captchaService.image(query);
    }

}
