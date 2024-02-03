package cc.tucci.admin.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tucci
 */
@Data
public class SysUserUpdateRoleBody {

    @NotNull
    private Long uid;
    private List<Long> roleIds;
}
