package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.query.ImageCaptchaQuery;
import cc.tucci.admin.app.system.dto.vo.ImageCaptchaVO;
import cc.tucci.admin.domain.captcha.service.ImageCaptchaService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import cc.tucci.admin.domain.core.util.RandomUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author tucci
 */
@Service
public class ImageCaptchaExe {

    private final ImageCaptchaService imageCaptchaService;

    public ImageCaptchaExe(ImageCaptchaService imageCaptchaService) {
        this.imageCaptchaService = imageCaptchaService;
    }

    public Response execute(ImageCaptchaQuery query) throws IOException {
        String key = RandomUtils.uuid();
        BufferedImage image = imageCaptchaService.generate(query.getType(), key, RandomUtils.randomNumber(4));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        byte[] array = os.toByteArray();
        String base64 = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(array);
        ImageCaptchaVO vo = new ImageCaptchaVO()
                .setKey(key)
                .setBase64(base64);
        return SingletonResponse.success(vo);
    }
}
