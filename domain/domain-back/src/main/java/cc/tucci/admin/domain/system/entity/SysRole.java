package cc.tucci.admin.domain.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class SysRole {

    private Long id;
    private String roleChar;
    private String name;
    private String remarks;
    private Long createTime;
    private Long updateTime;
    private List<Long> resIds;

}
