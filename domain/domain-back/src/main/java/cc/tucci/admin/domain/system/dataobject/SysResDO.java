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
@TableName("sys_res")
public class SysResDO {

    @TableId
    private Long id;
    private String name;
    private Integer type;
    private String url;
    private Long pid;
    private String resChar;
    private Integer seq;
    private String icon;
    private Long createTime;
    private Long updateTime;
    private Boolean isDeleted;

}