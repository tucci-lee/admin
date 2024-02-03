package cc.tucci.admin.domain.risk.service;

/**
 * @author tucci
 */
public interface LimitService {

    /**
     * 限制
     *
     * @param key          key
     * @param rate         次数
     * @param rateInterval 访问间隔/秒
     */
    void limit(String key, int rate, int rateInterval);

}
