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
@TableName("sys_user")
public class SysUserDO {

    @TableId
    private Long uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickname;
    private String remarks;
    private Long deptId;
    private Boolean isLock;
    private Long createTime;
    private Long updateTime;
    private Boolean isDeleted;

}