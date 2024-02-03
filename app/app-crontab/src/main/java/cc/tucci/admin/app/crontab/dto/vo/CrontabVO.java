package cc.tucci.admin.app.crontab.dto.vo;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class CrontabVO {
    private Long id;
    private String name;
    private String className;
    private String cron;
    private Boolean status;
    private String remarks;
    private Long createTime;
    private Long updateTime;
}
