package cc.tucci.admin.app.system.dto.body;

import cc.tucci.admin.domain.system.entity.SysUser;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author tucci
 */
@Data
public class SysDeptUpdateBody {

    @NotNull
    private Long id;
    private Long pid;
    @NotBlank
    @Length(max = 20)
    private String name;
    @NotBlank
    @Size(max = 100)
    private String manager;
    @NotBlank
    @Pattern(regexp = SysUser.Verify.PHONE_REGEX)
    private String managerPhone;
    @Range(max = 999)
    private Integer seq;
}
