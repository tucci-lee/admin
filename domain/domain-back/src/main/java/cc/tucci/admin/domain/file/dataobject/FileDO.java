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
@TableName("file")
public class FileDO {

    @TableId
    private Long id;
    private Long groupId;
    private String path;
    private Long createTime;
    private Long updateTime;
    private Boolean isDeleted;

}
