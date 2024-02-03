package cc.tucci.admin.domain.file.service;

import cc.tucci.admin.domain.file.entity.FileGroup;

/**
 * @author tucci
 */
public interface FileGroupService {

    /**
     * 创建文件分组
     *
     * @param entity 分组
     */
    void create(FileGroup entity);

    /**
     * 修改文件分组
     *
     * @param entity 分组
     */
    void update(FileGroup entity);

    /**
     * 删除文件分组
     */
    void delete(Long id);

    /**
     * 根据id查询分组
     *
     * @param id id
     * @return 分组
     */
    FileGroup getById(Long id);

}
