package cc.tucci.admin.domain.risk.service;

import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.core.exception.BizException;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author tucci
 */
public class RedissonLimitService implements LimitService {

    private final RedissonClient redissonClient;

    public RedissonLimitService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void limit(String key, int rate, int rateInterval) {
        Assert.notEmpty(key, BizCode.PARAMETER_ERROR);

        rate = Math.max(rate, 1);
        rateInterval = Math.max(rateInterval, 1);

        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, rate, rateInterval, RateIntervalUnit.SECONDS);
        if (!rateLimiter.tryAcquire(1)) { // 禁止访问
            throw new BizException(BizCode.FREQUENT_REQUESTS);
        }
        rateLimiter.expire(rateInterval, TimeUnit.SECONDS);
    }
}
