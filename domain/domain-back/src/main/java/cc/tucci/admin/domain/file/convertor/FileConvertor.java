package cc.tucci.admin.domain.file.convertor;

import cc.tucci.admin.domain.file.dataobject.FileDO;
import cc.tucci.admin.domain.file.entity.File;

/**
 * @author tucci
 */
public class FileConvertor {

    public static FileDO toCreate(File entity) {
        return new FileDO()
                .setGroupId(entity.getGroupId())
                .setPath(entity.getPath())
                .setCreateTime(System.currentTimeMillis());
    }

    public static FileDO toUpdate(File entity) {
        return new FileDO()
                .setId(entity.getId())
                .setGroupId(entity.getGroupId())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static FileDO toDelete(Long id) {
        return new FileDO()
                .setId(id)
                .setIsDeleted(true);
    }
}
