package cc.tucci.admin.app.system.dto.body;


import cc.tucci.admin.domain.system.entity.SysUser;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author tucci
 */
@Data
public class SysUserCreateBody {

    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Length(min = 6, max = 32)
    private String password;
    @Pattern(regexp = SysUser.Verify.PHONE_REGEX)
    private String phone;
    @Email
    private String email;
    @Size(max = 20)
    private String nickname;
    @Size(max = 200)
    private String remarks;
    private Long deptId;

    private List<Long> roleIds;
}
