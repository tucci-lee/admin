package cc.tucci.admin.domain.file.entity;

import lombok.Data;
import lombok.experimental.Accessors;


/**
* @author tucci
*/
@Data
@Accessors(chain = true)
public class File {

    private Long id;
    private Long groupId;
    private String path;
    private Long createTime;
    private Long updateTime;

}
