package cc.tucci.admin.app.system.dto.body;


import cc.tucci.admin.domain.system.entity.SysUser;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author tucci
 */
@Data
public class SysUserUpdateBody {

    @NotNull
    private Long uid;
    @Pattern(regexp = SysUser.Verify.PHONE_REGEX)
    private String phone;
    @Email
    private String email;
    @Size(max = 20)
    private String nickname;
    @Size(max = 200)
    private String remarks;
    private Boolean isLock;
    private Long deptId;
}
