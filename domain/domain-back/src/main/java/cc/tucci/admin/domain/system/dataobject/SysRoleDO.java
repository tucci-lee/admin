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
@TableName("sys_role")
public class SysRoleDO {

    @TableId
    private Long id;
    private String roleChar;
    private String name;
    private String remarks;
    private Long createTime;
    private Long updateTime;
    private Boolean isDeleted;

}