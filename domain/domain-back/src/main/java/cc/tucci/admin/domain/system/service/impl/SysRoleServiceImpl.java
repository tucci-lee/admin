package cc.tucci.admin.domain.system.service.impl;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.system.constant.CacheConst;
import cc.tucci.admin.domain.system.convertor.SysRoleConvertor;
import cc.tucci.admin.domain.system.dataobject.SysRoleDO;
import cc.tucci.admin.domain.system.dataobject.SysRoleResDO;
import cc.tucci.admin.domain.system.dataobject.SysUserRoleDO;
import cc.tucci.admin.domain.system.entity.SysRole;
import cc.tucci.admin.domain.system.mapper.SysRoleMapper;
import cc.tucci.admin.domain.system.mapper.SysRoleResMapper;
import cc.tucci.admin.domain.system.mapper.SysUserRoleMapper;
import cc.tucci.admin.domain.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;
    private final SysRoleResMapper sysRoleResMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper,
                              SysRoleResMapper sysRoleResMapper,
                              SysUserRoleMapper sysUserRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysRoleResMapper = sysRoleResMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Transactional
    @Override
    public void create(SysRole entity) {
        // 校验角色名称是否有相同的
        Assert.isNull(this.getByName(entity.getName()), BizCode.ROLE_NAME_EXIST);

        SysRoleDO create = SysRoleConvertor.toCreate(entity);
        sysRoleMapper.insert(create);
        // 添加关联的资源
        List<Long> resIds = entity.getResIds();
        if(!CollectionUtils.isEmpty(resIds)) {
            sysRoleResMapper.insertList(create.getId(), resIds);
        }
    }

    @Override
    public void update(SysRole entity) {
        // 校验角色名称是否有相同的
        SysRoleDO get = this.getByName(entity.getName());
        Assert.isTrue(get == null || get.getId().equals(entity.getId()), BizCode.ROLE_NAME_EXIST);

        SysRoleDO updateRole = SysRoleConvertor.toUpdate(entity);
        sysRoleMapper.updateById(updateRole);
    }

    @Override
    public void delete(Long id) {
        // 是否有用户关联
        LambdaQueryWrapper<SysUserRoleDO> wrapper = Wrappers.lambdaQuery(SysUserRoleDO.class)
                .eq(SysUserRoleDO::getRoleId, id);
        int userCount = sysUserRoleMapper.selectCount(wrapper);
        Assert.isTrue(userCount == 0, BizCode.ROLE_RELATED);

        SysRoleDO delete = SysRoleConvertor.toDelete(id);
        sysRoleMapper.updateById(delete);
    }

    @CacheEvict(value = CacheConst.USER_RES, allEntries = true)
    @Transactional
    @Override
    public void updateRes(SysRole entity) {
        // 删除之前绑定的资源
        LambdaUpdateWrapper<SysRoleResDO> wrapper = Wrappers.lambdaUpdate(SysRoleResDO.class)
                .eq(SysRoleResDO::getRoleId, entity.getId());
        sysRoleResMapper.delete(wrapper);
        // 添加新绑定的资源
        List<Long> resIds = entity.getResIds();
        if(!CollectionUtils.isEmpty(resIds)) {
            sysRoleResMapper.insertList(entity.getId(), resIds);
        }
    }

    @Override
    public List<SysRole> listByUid(Long uid) {
        List<SysRoleDO> sysRoleDOList = sysRoleMapper.selectByUid(uid);
        return sysRoleDOList.stream()
                .map(SysRoleConvertor::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * 根据名称查询角色
     *
     * @param name 名称
     * @return 角色
     */
    private SysRoleDO getByName(String name) {
        LambdaQueryWrapper<SysRoleDO> wrapper = Wrappers.lambdaQuery(SysRoleDO.class)
                .eq(SysRoleDO::getIsDeleted, false)
                .eq(SysRoleDO::getName, name);
        return sysRoleMapper.selectOne(wrapper);
    }
}
