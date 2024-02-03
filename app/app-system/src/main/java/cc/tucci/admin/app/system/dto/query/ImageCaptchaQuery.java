package cc.tucci.admin.app.system.dto.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tucci
 */
@Data
public class ImageCaptchaQuery {

    @NotNull
    private Integer type;
}
