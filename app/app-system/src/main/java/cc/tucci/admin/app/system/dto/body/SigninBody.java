package cc.tucci.admin.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author tucci
 */
@Data
public class SigninBody {

    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String captcha;
    @NotBlank
    private String captchaKey;
}
