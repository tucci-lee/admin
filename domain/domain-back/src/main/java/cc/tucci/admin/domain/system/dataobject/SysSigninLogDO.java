package cc.tucci.admin.domain.system.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
@TableName("sys_signin_log")
public class SysSigninLogDO {

    @TableId
    private Long id;
    private String username;
    private String os;
    private String browser;
    private String ip;
    private Long createTime;
    private Boolean status;
    private String message;

}