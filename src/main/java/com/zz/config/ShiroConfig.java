package com.zz.config;

import com.zz.common.filter.URLPathMatchingFilter;
import com.zz.common.realm.DatabaseRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zz
 */
@Configuration
public class ShiroConfig {
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroFilterFactoryBean处理拦截资源文件问题
     * 配置关系：
     *    ShiroFilterFactoryBean(setSecurityManager(setRealm()))
     *
     * @param securityManager: securityManager
     * @return shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        // 设置SecurityManager
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/nowhere");

        // 自定义过滤器设置1
        // 过滤器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 自定义过滤器
        Map<String, Filter> customisedFilter = new HashMap<>();

        // 自定过滤器设置2：命名，需要设在过滤器之前
        customisedFilter.put("url", getURLPathMatchingFilter());

        // 配置映射关系
        // 放鸡贼登录
        filterChainDefinitionMap.put("/api/authentication", "authc");
        filterChainDefinitionMap.put("/api/admin/**", "authc");

        // 自定义过滤器设置3.设置过滤路径
        filterChainDefinitionMap.put("/api/admin/**", "url");

        // 自定义过滤器设置4.启用过滤器
        bean.setFilters(customisedFilter);
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    public URLPathMatchingFilter getURLPathMatchingFilter() {
        return new URLPathMatchingFilter();
    }

    /**
     * 2. SecurityManager: 设置myRealm、rememberMe
     * @return securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getDatabaseRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 2.2 rememberMeManager
     * @return rememberMeManager
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("EVANNIGHTLY_WAOU".getBytes());
        return cookieRememberMeManager;
    }

    /**
     * 2.1 设置"rememberMe"功能：最大记住时间：30天
     * @return cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
        return simpleCookie;
    }

    /**
     * 1. realm，注入凭证匹配器
     * @return realm
     */
    @Bean
    public DatabaseRealm getDatabaseRealm() {
        DatabaseRealm realm = new DatabaseRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * 凭证匹配器：2次md5加密
     * @return matcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager securityManager
     * @return advisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
