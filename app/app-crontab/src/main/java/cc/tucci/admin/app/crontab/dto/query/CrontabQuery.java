package cc.tucci.admin.app.crontab.dto.query;

import cc.tucci.admin.domain.core.dto.PageQuery;
import lombok.Data;

/**
 * @author tucci
 */
@Data
public class CrontabQuery extends PageQuery {

    private String name;
    private Boolean status;

}
