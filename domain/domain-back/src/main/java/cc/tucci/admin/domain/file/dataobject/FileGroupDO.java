package cc.tucci.admin.domain.file.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


/**
* @author tucci
*/
@Data
@Accessors(chain = true)
@TableName("file_group")
public class FileGroupDO {

    @TableId
    private Long id;
    private String name;
    private Integer sort;
    private String remarks;
    private Long createTime;
    private Long updateTime;
    private Boolean isDeleted;

}
