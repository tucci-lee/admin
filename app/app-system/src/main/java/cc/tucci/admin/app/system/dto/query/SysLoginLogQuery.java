package cc.tucci.admin.app.system.dto.query;
import cc.tucci.admin.domain.core.dto.PageQuery;
import lombok.Data;

/**
 * @author tucci
 */
@Data
public class SysLoginLogQuery extends PageQuery {

    private String username;
    private String ip;
    private Boolean status;
    private Long beginTime;
    private Long endTime;

}
