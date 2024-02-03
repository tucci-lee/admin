package cc.tucci.admin.app.core.config;

import cc.tucci.admin.app.core.shiro.filter.StatelessUserFilter;
import cc.tucci.admin.app.core.shiro.realm.StatelessRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tucci
 */
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        // 过滤链
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/error/**", "anon");
        filterChainMap.put("/captcha/**", "anon");
        filterChainMap.put("/signin", "anon");
        filterChainMap.put("/**/template", "anon");
        filterChainMap.put("/druid/**", "anon");
        filterChainMap.put("/**", "user");

        // 自定义filter
        StatelessUserFilter statelessUserFilter = new StatelessUserFilter();
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("user", statelessUserFilter);

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setFilterChainDefinitionMap(filterChainMap);
        bean.setFilters(filters);
        return bean;
    }

    @Bean
    public SecurityManager securityManager(StatelessRealm statelessRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(statelessRealm);
        // 禁用记住我
        securityManager.setRememberMeManager(null);
        // 禁用session，禁用session访问druid页面会有问题
        // 执行创建session的判断DefaultWebSessionStorageEvaluator#isSessionStorageEnabled
        // 也可以通过filter关闭request.setAttribute(DefaultSubjectContext.SESSION_CREATION_ENABLED, false);
//        SubjectDAO subjectDAO = securityManager.getSubjectDAO();
//        SessionStorageEvaluator sessionStorageEvaluator = ((DefaultSubjectDAO) subjectDAO).getSessionStorageEvaluator();
//        ((DefaultSessionStorageEvaluator) sessionStorageEvaluator).setSessionStorageEnabled(false);

        return securityManager;
    }

    /**
     * 开启shiro注解，依赖spring aop
     *
     * @param securityManager securityManager
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
