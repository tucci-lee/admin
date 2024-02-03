package cc.tucci.admin.domain.system.entity;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class SysSigninLog {

    private Long id;
    private String username;
    private String os;
    private String browser;
    private String ip;
    private Boolean status;
    private String message;
    private Long createTime;

}
