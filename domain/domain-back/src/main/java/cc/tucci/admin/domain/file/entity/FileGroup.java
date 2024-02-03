package cc.tucci.admin.domain.file.entity;

import lombok.Data;
import lombok.experimental.Accessors;


/**
* @author tucci
*/
@Data
@Accessors(chain = true)
public class FileGroup {

    private Long id;
    private String name;
    private Integer sort;
    private String remarks;
    private Long createTime;
    private Long updateTime;

}
