package cc.tucci.admin.app.system.dto.body;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author tucci
 */
@Data
public class ProfileUpdateBody {

    @Size(max = 20)
    private String nickname;
    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;
    @Email
    private String email;
}
