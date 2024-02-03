package cc.tucci.admin.domain.file.service;

import cc.tucci.admin.domain.file.entity.File;

/**
 * @author tucci
 */
public interface FileService {

    /**
     * 创建文件
     *
     * @param entity 文件
     */
    void create(File entity);

    /**
     * 修改文件
     *
     * @param entity 文件
     */
    void update(File entity);

    /**
     * 删除文件
     *
     * @param id id
     */
    void delete(Long id);
}
