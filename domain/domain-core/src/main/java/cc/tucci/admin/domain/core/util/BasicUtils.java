package cc.tucci.admin.domain.core.util;

import cc.tucci.admin.domain.core.dto.BasicQuery;
import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.core.exception.BizException;

/**
 * @author tucci
 */
public class BasicUtils {

    public static void verify(BasicQuery query) {
        Assert.notNull(query, BizCode.PARAMETER_ERROR);
        Assert.notEmpty(query.getAppid(), BizCode.PARAMETER_ERROR);
        Assert.notEmpty(query.getVersion(), BizCode.PARAMETER_ERROR);
        Assert.notEmpty(query.getDeviceId(), BizCode.PARAMETER_ERROR);
        Assert.notEmpty(query.getDeviceModel(), BizCode.PARAMETER_ERROR);
        Assert.notEmpty(query.getPlatform(), BizCode.PARAMETER_ERROR);

        switch (query.getPlatform()) {
            case BasicQuery.Platform.APP:
            case BasicQuery.Platform.MP_WEIXIN:
            case BasicQuery.Platform.WEB:
                return;
            default:
                throw new BizException(BizCode.PARAMETER_ERROR);
        }
    }
}
