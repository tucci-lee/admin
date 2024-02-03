package cc.tucci.admin.domain.system.convertor;

import cc.tucci.admin.domain.system.dataobject.SysResDO;
import cc.tucci.admin.domain.system.entity.SysRes;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class SysResConvertor {

    public static SysRes toEntity(SysResDO db) {
        if (db == null) {
            return null;
        }
        SysRes entity = new SysRes();
        BeanUtils.copyProperties(db, entity);
        return entity;
    }

    public static SysResDO toCreate(SysRes entity) {
        return new SysResDO()
                .setName(entity.getName())
                .setType(entity.getType())
                .setUrl(entity.getUrl())
                .setPid(entity.getPid())
                .setResChar(entity.getResChar())
                .setSeq(entity.getSeq())
                .setIcon(entity.getIcon())
                .setCreateTime(System.currentTimeMillis());
    }

    public static SysResDO toUpdate(SysRes entity) {
        return new SysResDO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setUrl(entity.getUrl())
                .setPid(entity.getPid())
                .setResChar(entity.getResChar())
                .setSeq(entity.getSeq())
                .setIcon(entity.getIcon())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysResDO toDelete(Long id) {
        return new SysResDO()
                .setId(id)
                .setIsDeleted(Boolean.TRUE)
                .setUpdateTime(System.currentTimeMillis());
    }
}
