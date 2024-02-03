package cc.tucci.admin.domain.file.service.impl;

import cc.tucci.admin.domain.file.convertor.FileConvertor;
import cc.tucci.admin.domain.file.dataobject.FileDO;
import cc.tucci.admin.domain.file.entity.File;
import cc.tucci.admin.domain.file.mapper.FileMapper;
import cc.tucci.admin.domain.file.service.FileService;
import org.springframework.stereotype.Service;

/**
* @author tucci
*/
@Service
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public void create(File entity) {
        FileDO create = FileConvertor.toCreate(entity);
        fileMapper.insert(create);
    }

    @Override
    public void update(File entity) {
        FileDO update = FileConvertor.toUpdate(entity);
        fileMapper.updateById(update);
    }

    @Override
    public void delete(Long id) {
        FileDO delete = FileConvertor.toDelete(id);
        fileMapper.updateById(delete);
    }
}




