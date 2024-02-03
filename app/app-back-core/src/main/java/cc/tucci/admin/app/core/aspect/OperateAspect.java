package cc.tucci.admin.app.core.aspect;

import cc.tucci.admin.domain.authorize.Authorize;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cc.tucci.admin.app.core.util.WebUtils;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
import cc.tucci.admin.domain.system.entity.SysOperateLog;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysOperateLogService;
import cc.tucci.admin.domain.system.service.SysUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 操作日志记录
 *
 * @author tucci
 */
@Aspect
@Component
public class OperateAspect {

    private final SysOperateLogService logOperateService;
    private final SysUserService sysUserService;

    public OperateAspect(SysOperateLogService logOperateService,
                         SysUserService sysUserService) {
        this.logOperateService = logOperateService;
        this.sysUserService = sysUserService;
    }

    @Pointcut("@annotation(operate)")
    public void pointcut(Operate operate) {
    }

    @Around(value = "pointcut(operate)", argNames = "pjp,operate")
    public Object around(ProceedingJoinPoint pjp, Operate operate) throws Throwable {
        Signature sig = pjp.getSignature();
        String method = pjp.getTarget().getClass().getName() + "." + sig.getName();
        String value = operate.value();
        String url = WebUtils.getRequest().getRequestURI();
        String ip = WebUtils.getIp();
        Authorize authorize = AuthorizeContextHolder.get();
        Long uid = authorize.getUid();
        SysUser user = sysUserService.getByUid(uid);

        String params = null;
        if (operate.recordParams()) {
            Object[] args = pjp.getArgs();
            params = JSON.toJSONString(args);
        }

        SysOperateLog operationLog = new SysOperateLog()
                .setUsername(user.getUsername())
                .setIp(ip)
                .setUrl(url)
                .setMethod(method)
                .setParams(params)
                .setDescription(value);
        try {
            Object result = pjp.proceed();
            create(operationLog, JSONObject.toJSONString(result), null, true);
            return result;
        } catch (Throwable e) {
            create(operationLog, null, e.getMessage(), false);
            throw e;
        }
    }

    /**
     * 添加操作日志
     */
    private void create(SysOperateLog operationLog, String result, String errMsg, boolean status) {
        operationLog.setResult(result)
                .setStatus(status)
                .setErrorMessage(errMsg);
        logOperateService.create(operationLog);
    }
}
