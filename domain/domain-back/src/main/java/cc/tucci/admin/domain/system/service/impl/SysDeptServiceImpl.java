package cc.tucci.admin.domain.system.service.impl;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.system.convertor.SysDeptConvertor;
import cc.tucci.admin.domain.system.dataobject.SysDeptDO;
import cc.tucci.admin.domain.system.dataobject.SysUserDO;
import cc.tucci.admin.domain.system.entity.SysDept;
import cc.tucci.admin.domain.system.mapper.SysDeptMapper;
import cc.tucci.admin.domain.system.mapper.SysUserMapper;
import cc.tucci.admin.domain.system.service.SysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;
    private final SysUserMapper sysUserMapper;

    public SysDeptServiceImpl(SysDeptMapper sysDeptMapper,
                              SysUserMapper sysUserMapper) {
        this.sysDeptMapper = sysDeptMapper;
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public List<SysDept> listAll() {
        LambdaQueryWrapper<SysDeptDO> wrapper = Wrappers.lambdaQuery(SysDeptDO.class)
                .eq(SysDeptDO::getIsDeleted, false);
        return sysDeptMapper.selectList(wrapper).stream()
                .map(SysDeptConvertor::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SysDept getById(Long id) {
        LambdaQueryWrapper<SysDeptDO> wrapper = Wrappers.lambdaQuery(SysDeptDO.class)
                .eq(SysDeptDO::getIsDeleted, false)
                .eq(SysDeptDO::getId, id);
        SysDeptDO get = sysDeptMapper.selectOne(wrapper);
        return SysDeptConvertor.toEntity(get);
    }

    @Override
    public void create(SysDept entity) {
        // 校验上级是否存在
        this.verifyParent(entity.getPid());

        SysDeptDO create = SysDeptConvertor.toCreate(entity);
        sysDeptMapper.insert(create);
    }

    @Override
    public void update(SysDept entity) {
        entity.verifyLevel();
        // 校验上级是否存在
        this.verifyParent(entity.getPid());

        SysDeptDO update = SysDeptConvertor.toUpdate(entity);
        sysDeptMapper.updateById(update);
    }

    @Override
    public void delete(Long id) {
        // 判断是否有用户关联
        LambdaQueryWrapper<SysUserDO> countUserWrapper = Wrappers.lambdaQuery(SysUserDO.class)
                .eq(SysUserDO::getIsDeleted, false)
                .eq(SysUserDO::getDeptId, id);
        int userCount = sysUserMapper.selectCount(countUserWrapper);
        Assert.isTrue(userCount == 0, BizCode.DEPT_RELATED);

        // 判断是否有下级
        LambdaQueryWrapper<SysDeptDO> countSubWrapper = Wrappers.lambdaQuery(SysDeptDO.class)
                .eq(SysDeptDO::getIsDeleted, false)
                .eq(SysDeptDO::getPid, id);
        int subCount = sysDeptMapper.selectCount(countSubWrapper);
        Assert.isTrue(subCount == 0, BizCode.DEPT_HAS_SUB);

        SysDeptDO delete = SysDeptConvertor.toDelete(id);
        sysDeptMapper.updateById(delete);
    }

    /**
     * 校验上级是否存在
     */
    private void verifyParent(Long pid) {
        if (pid == null || pid.equals(SysDept.TOP_ID)) {
            return;
        }
        SysDept parent = this.getById(pid);
        Assert.notNull(parent, BizCode.PARENT_NOT_EXIST);
    }
}
