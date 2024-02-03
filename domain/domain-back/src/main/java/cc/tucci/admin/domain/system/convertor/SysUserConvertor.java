package cc.tucci.admin.domain.system.convertor;

import cc.tucci.admin.domain.system.dataobject.SysUserDO;
import cc.tucci.admin.domain.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class SysUserConvertor {

    public static SysUser toEntity(SysUserDO entity) {
        if (entity == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(entity, sysUser);
        return sysUser;
    }

    public static SysUserDO toCreate(SysUser entity) {
        return new SysUserDO()
                .setUsername(entity.getUsername().toLowerCase())
                .setPassword(entity.encodePassword())
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail())
                .setNickname(entity.getNickname())
                .setRemarks(entity.getRemarks())
                .setDeptId(entity.getDeptId())
                .setCreateTime(System.currentTimeMillis());
    }

    public static SysUserDO toUpdate(SysUser entity) {
        return new SysUserDO()
                .setUid(entity.getUid())
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail())
                .setNickname(entity.getNickname())
                .setRemarks(entity.getRemarks())
                .setIsLock(entity.getIsLock())
                .setDeptId(entity.getDeptId())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysUserDO toUpdatePassword(SysUser entity) {
        return new SysUserDO()
                .setUid(entity.getUid())
                .setPassword(entity.encodePassword())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysUserDO toUpdateLock(SysUser entity) {
        return new SysUserDO()
                .setUid(entity.getUid())
                .setIsLock(entity.getIsLock())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysUserDO toDelete(Long uid) {
        return new SysUserDO()
                .setUid(uid)
                .setIsDeleted(Boolean.TRUE)
                .setUpdateTime(System.currentTimeMillis());
    }
}
