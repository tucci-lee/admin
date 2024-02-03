package cc.tucci.admin.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tucci
 */
@Data
public class SysUserUpdateLockBody {

    @NotNull
    private Long uid;
    @NotNull
    private Boolean isLock;
}
