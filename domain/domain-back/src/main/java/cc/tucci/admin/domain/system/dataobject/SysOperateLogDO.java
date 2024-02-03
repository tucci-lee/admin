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
@TableName("sys_operate_log")
public class SysOperateLogDO {

    @TableId
    private Long id;
    private String username;
    private String ip;
    private String url;
    private String method;
    private String params;
    private String result;
    private String description;
    private String errorMessage;
    private Long createTime;
    private Boolean status;

}