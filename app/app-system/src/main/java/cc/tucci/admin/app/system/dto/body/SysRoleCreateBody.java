package cc.tucci.admin.app.system.dto.body;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author tucci
 */
@Data
public class SysRoleCreateBody {

    @NotBlank
    @Length(max = 20)
    private String name;

    @Size(max = 100)
    private String roleChar;

    @Size(max = 200)
    private String remarks;

    private List<Long> resIds;
}
