package cc.tucci.admin.domain.system.entity;


import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class SysDept {

    public static final Long TOP_ID = 0L;

    private Long id;
    private String name;
    private Long pid;
    private Integer seq;
    private String manager;
    private String managerPhone;
    private Long createTime;
    private Long updateTime;

    /**
     * 校验层级
     */
    public void verifyLevel(){
        Assert.isTrue(!this.id.equals(this.pid), BizCode.LEVEL_ERROR);
    }

}
