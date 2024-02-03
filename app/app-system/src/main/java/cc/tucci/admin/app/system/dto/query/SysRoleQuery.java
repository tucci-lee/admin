package cc.tucci.admin.app.system.dto.query;

import cc.tucci.admin.domain.core.dto.PageQuery;
import lombok.Data;

/**
 * @author tucci
 */
@Data
public class SysRoleQuery extends PageQuery {

    private String name;

}

