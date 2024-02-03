package cc.tucci.admin.domain.file.convertor;

import cc.tucci.admin.domain.file.dataobject.FileGroupDO;
import cc.tucci.admin.domain.file.entity.FileGroup;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class FileGroupConvertor {


    public static FileGroup toEntity(FileGroupDO db) {
        if(db == null) {
            return null;
        }
        FileGroup entity = new FileGroup();
        BeanUtils.copyProperties(db, entity);
        return entity;
    }

    public static FileGroupDO toCreate(FileGroup entity) {
        return new FileGroupDO()
                .setName(entity.getName())
                .setSort(entity.getSort())
                .setRemarks(entity.getRemarks())
                .setCreateTime(System.currentTimeMillis());
    }

    public static FileGroupDO toUpdate(FileGroup entity) {
        return new FileGroupDO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setSort(entity.getSort())
                .setRemarks(entity.getRemarks())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static FileGroupDO toDelete(Long id) {
        return new FileGroupDO()
                .setId(id)
                .setIsDeleted(true)
                .setUpdateTime(System.currentTimeMillis());
    }

}
