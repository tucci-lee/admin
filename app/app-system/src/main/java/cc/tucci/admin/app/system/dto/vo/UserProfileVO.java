package cc.tucci.admin.app.system.dto.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class UserProfileVO {

    private Long uid;
    private String username;
    private String phone;
    private String email;
    private String nickname;
    private Long createTime;
    private Long updateTime;
}
