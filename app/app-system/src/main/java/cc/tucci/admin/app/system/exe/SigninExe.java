package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.core.util.WebUtils;
import cc.tucci.admin.app.system.dto.body.SigninBody;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.captcha.entity.Captcha;
import cc.tucci.admin.domain.captcha.service.ImageCaptchaService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import cc.tucci.admin.domain.risk.constant.LimitConst;
import cc.tucci.admin.domain.risk.service.LimitService;
import cc.tucci.admin.domain.system.entity.SysSigninLog;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysAuthorizeService;
import cc.tucci.admin.domain.system.service.SysSigninLogService;
import cc.tucci.admin.domain.system.service.SysUserService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SigninExe {

    private final ImageCaptchaService imageCaptchaService;
    private final SysUserService sysUserService;
    private final SysAuthorizeService sysAuthorizeService;
    private final SysSigninLogService sysSigninLogService;
    private final LimitService limitService;

    public SigninExe(ImageCaptchaService imageCaptchaService,
                     SysUserService sysUserService,
                     SysAuthorizeService sysAuthorizeService,
                     SysSigninLogService sysSigninLogService,
                     LimitService limitService) {
        this.imageCaptchaService = imageCaptchaService;
        this.sysUserService = sysUserService;
        this.sysAuthorizeService = sysAuthorizeService;
        this.sysSigninLogService = sysSigninLogService;
        this.limitService = limitService;
    }

    /**
     * 校验验证码
     * 记录日志（无论成功失败）
     *
     * @param body 登录参数
     * @return Response
     */
    public Response execute(SigninBody body) {
        boolean status = true;
        String message = "";
        try {
            limitService.limit(LimitConst.SIGNIN + body.getUsername(), LimitConst.SIGNIN_RATE, LimitConst.SIGNIN_INTERVAL);

            imageCaptchaService.verify(Captcha.Type.SIGNIN, body.getCaptchaKey(), body.getCaptcha());
            SysUser user = sysUserService.getByUsername(body.getUsername());
            Authorize authorize = sysAuthorizeService.create(user, body.getPassword());

            return SingletonResponse.success(authorize.getToken());
        } catch (Exception e) {
            status = false;
            message = e.getMessage();
            throw e;
        } finally {
            String userAgentStr = WebUtils.getRequest().getHeader("User-Agent");
            String ip = WebUtils.getIp();
            // 获取浏览器信息（操作系统，浏览器）
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            SysSigninLog log = new SysSigninLog()
                    .setUsername(body.getUsername())
                    .setOs(os)
                    .setBrowser(browser)
                    .setStatus(status)
                    .setMessage(message)
                    .setIp(ip);
            sysSigninLogService.create(log);
        }
    }
}
