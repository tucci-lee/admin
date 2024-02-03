package cc.tucci.admin.domain.system.service.impl;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.system.constant.CacheConst;
import cc.tucci.admin.domain.system.convertor.SysUserConvertor;
import cc.tucci.admin.domain.system.dataobject.SysUserDO;
import cc.tucci.admin.domain.system.dataobject.SysUserRoleDO;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.mapper.SysUserMapper;
import cc.tucci.admin.domain.system.mapper.SysUserRoleMapper;
import cc.tucci.admin.domain.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tucci
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper,
                              SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUserDO> wrapper = Wrappers.lambdaQuery(SysUserDO.class)
                .eq(SysUserDO::getIsDeleted, false)
                .eq(SysUserDO::getUsername, username);
        SysUserDO get = sysUserMapper.selectOne(wrapper);
        return SysUserConvertor.toEntity(get);
    }

    @Override
    public SysUser getByUid(Long uid) {
        LambdaQueryWrapper<SysUserDO> wrapper = Wrappers.lambdaQuery(SysUserDO.class)
                .eq(SysUserDO::getIsDeleted, false)
                .eq(SysUserDO::getUid, uid);
        SysUserDO get = sysUserMapper.selectOne(wrapper);
        return SysUserConvertor.toEntity(get);
    }

    @Transactional
    @Override
    public void create(SysUser entity) {
        // 校验用户名是否有相同的
        SysUser get = this.getByUsername(entity.getUsername());
        Assert.isNull(get, BizCode.ACCOUNT_EXIST);

        SysUserDO sysUserDO = SysUserConvertor.toCreate(entity);
        sysUserMapper.insert(sysUserDO);

        List<Long> roleIds = entity.getRoleIds();
        // 添加关联的角色信息
        if(!CollectionUtils.isEmpty(roleIds)) {
            sysUserRoleMapper.insertList(sysUserDO.getUid(), roleIds);
        }
    }

    @Override
    public void update(SysUser entity) {
        SysUserDO sysUserDO = SysUserConvertor.toUpdate(entity);
        sysUserMapper.updateById(sysUserDO);
    }

    @Override
    public void updatePassword(SysUser entity) {
        SysUserDO update = SysUserConvertor.toUpdatePassword(entity);
        sysUserMapper.updateById(update);
    }

    @CacheEvict(value = {CacheConst.USER_RES}, key = "#p0")
    @Override
    public void delete(Long uid) {
        SysUserDO delete = SysUserConvertor.toDelete(uid);
        sysUserMapper.updateById(delete);
    }

    @CacheEvict(value = CacheConst.USER_RES, key = "#p0.uid")
    @Transactional
    @Override
    public void updateRole(SysUser entity) {
        // 删除之前绑定的角色
        LambdaUpdateWrapper<SysUserRoleDO> wrapper = Wrappers.lambdaUpdate(SysUserRoleDO.class)
                .eq(SysUserRoleDO::getUid, entity.getUid());
        sysUserRoleMapper.delete(wrapper);

        List<Long> roleIds = entity.getRoleIds();
        if(!CollectionUtils.isEmpty(roleIds)) {
            // 添加新绑定的角色
            sysUserRoleMapper.insertList(entity.getUid(), roleIds);
        }
    }
}
