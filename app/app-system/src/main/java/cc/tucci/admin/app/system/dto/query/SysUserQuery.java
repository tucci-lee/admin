package cc.tucci.admin.app.system.dto.query;
import cc.tucci.admin.domain.core.dto.PageQuery;
import lombok.Data;

/**
 * @author tucci
 */
@Data
public class SysUserQuery extends PageQuery {

    private String username;
    private String phone;
    private Boolean isLock;
    private Long deptId;

}
