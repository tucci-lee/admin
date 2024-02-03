package cc.tucci.admin.domain.system.service;

import cc.tucci.admin.domain.system.entity.SysDept;

import java.util.List;

/**
 * @author tucci
 */
public interface SysDeptService {

    /**
     * 查询所有的部门列表
     *
     * @return List<SysDept>
     */
    List<SysDept> listAll();

    /**
     * 根据id查询部门
     *
     * @param id id
     * @return 部门信息
     */
    SysDept getById(Long id);

    /**
     * 保存部门
     *
     * @param entity 部门信息
     */
    void create(SysDept entity);

    /**
     * 修改部门
     *
     * @param entity 部门信息
     */
    void update(SysDept entity);


    /**
     * 根据id删除部门
     *
     * @param id id
     */
    void delete(Long id);
}
