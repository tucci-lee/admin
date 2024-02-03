package cc.tucci.admin.domain.system.entity;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.List;

/**
 * @author tucci
 */
@Data
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final Long ADMIN_UID = 1L;

    private Long uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickname;
    private String remarks;
    private Long deptId;
    private Boolean isLock;
    private Long createTime;
    private Long updateTime;
    private List<Long> roleIds;

    public interface Verify {
        String PHONE_REGEX = "^1[3-9][0-9]{9}$";
        String PASSWORD_REGEX = "^.{6,16}$";
        String SECOND_PASSWORD_REGEX = "^[0-9]{6}$";
        int USERNAME_SIZE = 20;
        String USERNAME_REGEX = "^[0-9a-zA-Z_\\.]{1,20}$";
        int EMAIL_SIZE = 50;
    }
    public String encodePassword() {
        return BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

    public void verifyPassword(String plaintext) {
        Assert.isTrue(verifyPassword(plaintext, this.password), BizCode.PASSWORD_ERROR);
    }

    public static boolean verifyPassword(String plaintext, String ciphertext){
        try {
            return BCrypt.checkpw(plaintext, ciphertext);
        } catch(RuntimeException e) {
            return false;
        }
    }

    public void verifyLock() {
        Assert.isTrue(!this.getIsLock(), BizCode.ACCOUNT_LOCKED);
    }

    public static boolean isAdmin(Long uid) {
        return ADMIN_UID.equals(uid);
    }

}
