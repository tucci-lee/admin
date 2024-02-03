package cc.tucci.admin.domain.system.entity;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class SysRes implements Serializable {

    public static final long serialVersionUID = 1L;

    public static final Long TOP_ID = 0L;

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

    public interface Type {
        int MENU = 1;
        int PERMISSION = 2;
    }

    public void verifyLevel() {
        Assert.isTrue(!this.id.equals(this.pid), BizCode.LEVEL_ERROR);
    }


}