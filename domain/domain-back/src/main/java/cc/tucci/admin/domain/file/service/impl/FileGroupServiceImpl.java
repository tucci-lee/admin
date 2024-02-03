package cc.tucci.admin.domain.file.service.impl;

import cc.tucci.admin.domain.file.convertor.FileGroupConvertor;
import cc.tucci.admin.domain.file.dataobject.FileGroupDO;
import cc.tucci.admin.domain.file.entity.FileGroup;
import cc.tucci.admin.domain.file.mapper.FileGroupMapper;
import cc.tucci.admin.domain.file.service.FileGroupService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

/**
* @author tucci
*/
@Service
public class FileGroupServiceImpl implements FileGroupService {

    private final FileGroupMapper fileGroupMapper;

    public FileGroupServiceImpl(FileGroupMapper fileGroupMapper) {
        this.fileGroupMapper = fileGroupMapper;
    }

    @Override
    public void create(FileGroup entity) {
        FileGroupDO create = FileGroupConvertor.toCreate(entity);
        fileGroupMapper.insert(create);
    }

    @Override
    public void update(FileGroup entity) {
        FileGroupDO update = FileGroupConvertor.toUpdate(entity);
        fileGroupMapper.updateById(update);
    }

    @Override
    public void delete(Long id) {
        FileGroupDO delete = FileGroupConvertor.toDelete(id);
        fileGroupMapper.updateById(delete);
    }

    @Override
    public FileGroup getById(Long id) {
        LambdaQueryWrapper<FileGroupDO> wrapper = Wrappers.lambdaQuery(FileGroupDO.class)
                .eq(FileGroupDO::getIsDeleted, false)
                .eq(FileGroupDO::getId, id);
        FileGroupDO group = fileGroupMapper.selectOne(wrapper);
        return FileGroupConvertor.toEntity(group);
    }

}




