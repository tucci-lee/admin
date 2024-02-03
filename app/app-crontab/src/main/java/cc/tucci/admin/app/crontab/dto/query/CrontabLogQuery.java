package cc.tucci.admin.app.crontab.dto.query;

import cc.tucci.admin.domain.core.dto.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tucci
 */
@Data
public class CrontabLogQuery extends PageQuery {

    @NotNull
    private Long crontabId;
}
