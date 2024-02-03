package cc.tucci.admin.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author tucci
 */
@Data
public class SysUserUpdatePasswordBody {
    @NotNull
    private Long uid;
    @NotBlank
    @Size(min = 6, max = 32)
    private String password;
}
