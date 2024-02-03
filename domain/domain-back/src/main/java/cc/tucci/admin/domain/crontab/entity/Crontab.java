package cc.tucci.admin.domain.crontab.entity;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class Crontab {

    private Long id;
    private String name;
    private String className;
    private String cron;
    private Boolean status;
    private String remarks;
    private Long createTime;
    private Long updateTime;

}
