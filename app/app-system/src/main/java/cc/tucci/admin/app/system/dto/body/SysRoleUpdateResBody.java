package cc.tucci.admin.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tucci
 */
@Data
public class SysRoleUpdateResBody {

    @NotNull
    private Long id;
    private List<Long> resIds;
}
