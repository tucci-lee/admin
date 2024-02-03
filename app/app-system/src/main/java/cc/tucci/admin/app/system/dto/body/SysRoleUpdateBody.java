package cc.tucci.admin.app.system.dto.body;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tucci
 */
@Data
public class SysRoleUpdateBody {

    @NotNull
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String name;
    @Size(max = 100)
    private String roleChar;
    @Size(max = 200)
    private String remarks;
}
