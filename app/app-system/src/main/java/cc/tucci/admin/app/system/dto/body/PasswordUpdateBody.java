package cc.tucci.admin.app.system.dto.body;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author tucci
 */
@Data
public class PasswordUpdateBody {

    @NotBlank
    @Length(min = 6, max = 32)
    private String oldPassword;
    @NotBlank
    @Length(min = 6, max = 32)
    private String password;
}
