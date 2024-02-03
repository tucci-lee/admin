package cc.tucci.admin.app.system.dto.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class ImageCaptchaVO {

    private String key;
    private String base64;
}
