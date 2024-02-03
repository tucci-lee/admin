package cc.tucci.admin.domain.crontab.service;

import cc.tucci.admin.domain.crontab.entity.Crontab;

import java.util.List;

/**
 * @author tucci
 */
public interface CrontabService {
    /**
     * 根据id查询定时任务
     *
     * @param id id
     * @return Crontab
     */
    Crontab getById(Long id);

    /**
     * 查询所有的定时任务
     * @return Crontab
     */
    List<Crontab> all();

    /**
     * 添加定时任务
     *
     * @param entity 定时任务
     */
    Long create(Crontab entity);

    /**
     * 修改定时任务信息
     *
     * @param entity 定时任务信息
     */
    void update(Crontab entity);

    /**
     * 删除定时任务
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * 修改定时任务状态
     *
     * @param entity 定时任务
     */
    void updateStatus(Crontab entity);


}
