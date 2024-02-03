package cc.tucci.admin.app.crontab.dto.body;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tucci
 */
@Data
public class CrontabUpdateStatusBody {

    @NotNull
    private Long id;
    @NotNull
    private Boolean status;
}
