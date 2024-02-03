package cc.tucci.admin.app.core.shiro.filter;

import com.alibaba.fastjson.JSON;
import cc.tucci.admin.app.core.shiro.token.StatelessToken;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.exception.BizCode;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tucci
 */
public class StatelessUserFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String authorization = httpRequest.getHeader("Authorization");
        if(StringUtils.isEmpty(authorization)){
            return false;
        }
        String[] authTokens = authorization.split(" ");
        if (authTokens.length < 2) {
            return false;
        }
        StatelessToken statelessToken = new StatelessToken(authTokens[1]);

        // 关闭session创建
//        request.setAttribute(DefaultSubjectContext.SESSION_CREATION_ENABLED, false);
        try {
            Subject subject = this.getSubject(request, response);
            subject.login(statelessToken);
            // StatelessRealm中已经将Authorize设置到环境中
            Authorize authorize = (Authorize) subject.getPrincipal();
            AuthorizeContextHolder.set(authorize);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setContentType("application/json;charset=UTF-8");
        BizCode bizCode = BizCode.UNAUTHENTICATED;
        String result = JSON.toJSONString(Response.fail(bizCode.getCode(), bizCode.getMessage()));
        res.getWriter().print(result);
        return false;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        super.postHandle(request, response);
        AuthorizeContextHolder.clear();
    }
}
