package cc.tucci.admin.app.system.service;

import cc.tucci.admin.app.system.dto.query.ImageCaptchaQuery;
import cc.tucci.admin.app.system.exe.ImageCaptchaExe;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author tucci
 */
@Service
public class CaptchaAppService {

    private final ImageCaptchaExe imageCaptchaExe;

    public CaptchaAppService(ImageCaptchaExe imageCaptchaExe) {
        this.imageCaptchaExe = imageCaptchaExe;
    }


    /**
     * 生成图片验证码
     */
    public Response image(ImageCaptchaQuery query) throws IOException {
        return imageCaptchaExe.execute(query);
    }
}
