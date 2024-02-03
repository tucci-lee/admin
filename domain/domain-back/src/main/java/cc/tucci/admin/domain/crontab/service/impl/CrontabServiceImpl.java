package cc.tucci.admin.domain.crontab.service.impl;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.crontab.convertor.CrontabConvertor;
import cc.tucci.admin.domain.crontab.dataobject.CrontabDO;
import cc.tucci.admin.domain.crontab.entity.Crontab;
import cc.tucci.admin.domain.crontab.mapper.CrontabMapper;
import cc.tucci.admin.domain.crontab.service.CrontabService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class CrontabServiceImpl implements CrontabService {

    private final CrontabMapper crontabMapper;

    public CrontabServiceImpl(CrontabMapper crontabMapper) {
        this.crontabMapper = crontabMapper;
    }

    @Override
    public Crontab getById(Long id) {
        LambdaQueryWrapper<CrontabDO> wrapper = Wrappers.lambdaQuery(CrontabDO.class)
                .eq(CrontabDO::getIsDeleted, false)
                .eq(CrontabDO::getId, id);
        CrontabDO crontabDO = crontabMapper.selectOne(wrapper);
        return CrontabConvertor.toEntity(crontabDO);
    }

    @Override
    public List<Crontab> all() {
        LambdaQueryWrapper<CrontabDO> wrapper = Wrappers.lambdaQuery(CrontabDO.class)
                .eq(CrontabDO::getIsDeleted, false);
        List<CrontabDO> crontabDOList = crontabMapper.selectList(wrapper);
        return crontabDOList.stream()
                .map(CrontabConvertor::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long create(Crontab entity) {
        Assert.isNull(this.getByName(entity.getName()), BizCode.CRONTAB_NAME_EXIST);

        CrontabDO create = CrontabConvertor.toCreate(entity);
        crontabMapper.insert(create);

        return create.getId();
    }

    @Override
    public void update(Crontab entity) {
        CrontabDO crontab = this.getByName(entity.getName());
        Assert.isTrue(crontab == null || crontab.getId().equals(entity.getId()), BizCode.CRONTAB_NAME_EXIST);

        CrontabDO update = CrontabConvertor.toUpdate(entity);
        crontabMapper.updateById(update);
    }

    @Override
    public void delete(Long id) {
        CrontabDO delete = CrontabConvertor.toDelete(id);
        crontabMapper.updateById(delete);
    }

    @Override
    public void updateStatus(Crontab entity) {
        CrontabDO update = CrontabConvertor.toUpdateStatus(entity);
        crontabMapper.updateById(update);
    }

    /**
     * 根据名称查询定时任务
     *
     * @param name 名称
     * @return 定时任务
     */
    private CrontabDO getByName(String name) {
        LambdaQueryWrapper<CrontabDO> wrapper = Wrappers.lambdaQuery(CrontabDO.class)
                .eq(CrontabDO::getIsDeleted, false)
                .eq(CrontabDO::getName, name);
        return crontabMapper.selectOne(wrapper);
    }
}
