package cc.tucci.admin.app.system.dto.vo;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class SysRoleVO {
    private Long id;
    private String roleChar;
    private String name;
    private String remarks;
    private Long createTime;
    private Long updateTime;
}
