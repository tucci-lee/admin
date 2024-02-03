package cc.tucci.admin.domain.system.service.impl;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.system.constant.CacheConst;
import cc.tucci.admin.domain.system.convertor.SysResConvertor;
import cc.tucci.admin.domain.system.dataobject.SysResDO;
import cc.tucci.admin.domain.system.dataobject.SysRoleResDO;
import cc.tucci.admin.domain.system.entity.SysRes;
import cc.tucci.admin.domain.system.mapper.SysResMapper;
import cc.tucci.admin.domain.system.mapper.SysRoleResMapper;
import cc.tucci.admin.domain.system.service.SysResService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysResServiceImpl implements SysResService {

    private final SysResMapper sysResMapper;
    private final SysRoleResMapper sysRoleResMapper;

    public SysResServiceImpl(SysResMapper sysResMapper,
                             SysRoleResMapper sysRoleResMapper) {
        this.sysResMapper = sysResMapper;
        this.sysRoleResMapper = sysRoleResMapper;
    }

    @Cacheable(value = CacheConst.USER_RES, key = "#root.methodName")
    @Override
    public List<SysRes> listAll() {
        LambdaQueryWrapper<SysResDO> wrapper = Wrappers.lambdaQuery(SysResDO.class)
                .eq(SysResDO::getIsDeleted, false);
        return sysResMapper.selectList(wrapper).stream()
                .map(SysResConvertor::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SysRes getById(Long id) {
        LambdaQueryWrapper<SysResDO> wrapper = Wrappers.lambdaQuery(SysResDO.class)
                .eq(SysResDO::getId, id)
                .eq(SysResDO::getIsDeleted, false);
        SysResDO get = sysResMapper.selectOne(wrapper);
        return SysResConvertor.toEntity(get);
    }

    @CacheEvict(value = CacheConst.USER_RES, allEntries = true)
    @Override
    public void create(SysRes entity) {
        // 校验上级是否存在
        this.verifyParent(entity.getPid());

        SysResDO create = SysResConvertor.toCreate(entity);
        sysResMapper.insert(create);
    }


    @CacheEvict(value = CacheConst.USER_RES, allEntries = true)
    @Override
    public void update(SysRes entity) {
        entity.verifyLevel();
        // 校验上级是否存在
        this.verifyParent(entity.getPid());

        SysResDO update = SysResConvertor.toUpdate(entity);
        sysResMapper.updateById(update);
    }

    @CacheEvict(value = CacheConst.USER_RES, allEntries = true)
    @Override
    public void delete(Long id) {
        // 是否有角色关联
        LambdaQueryWrapper<SysRoleResDO> wrapper = Wrappers.lambdaQuery(SysRoleResDO.class)
                .eq(SysRoleResDO::getResId, id);
        int roleCount = sysRoleResMapper.selectCount(wrapper);
        Assert.isTrue(roleCount == 0, BizCode.RES_RELATED);

        SysResDO delete = SysResConvertor.toDelete(id);
        sysResMapper.updateById(delete);
    }

    @Cacheable(value = CacheConst.USER_RES, key = "#p0")
    @Override
    public List<SysRes> listByUid(Long uid) {
        List<SysResDO> sysResDOList = sysResMapper.selectByUid(uid);
        return sysResDOList.stream()
                .map(SysResConvertor::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SysRes> listByRoleId(Long roleId) {
        List<SysResDO> sysResDOList = sysResMapper.selectByRoleId(roleId);
        return sysResDOList.stream()
                .map(SysResConvertor::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * 校验上级是否存在
     *
     * @param pid 上级id
     */
    private void verifyParent(Long pid) {
        if (pid == null || pid.equals(SysRes.TOP_ID)) {
            return;
        }
        SysRes parent = this.getById(pid);
        Assert.notNull(parent, BizCode.PARENT_NOT_EXIST);
    }
}
