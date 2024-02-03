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
@TableName("sys_dept")
public class SysDeptDO {

    @TableId
    private Long id;
    private String name;
    private Long pid;
    private Integer seq;
    private String manager;
    private String managerPhone;
    private Long createTime;
    private Long updateTime;
    private Boolean isDeleted;

}