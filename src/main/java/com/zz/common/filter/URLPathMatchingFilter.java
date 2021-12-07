package com.zz.common.filter;

import com.zz.common.utils.SpringContextUtils;
import com.zz.service.AdminPermsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author zz
 * 登录拦截
 */
@Slf4j
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    AdminPermsService adminPermsService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 放行OPTION请求
        if (HttpMethod.OPTIONS.toString().equals((httpRequest).getMethod())) {
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        if (null == adminPermsService) {
            adminPermsService = SpringContextUtils.getContext().getBean(AdminPermsService.class);
        }

        String requestUri = getPathWithinApplication(request);
        System.out.println("访问了接口：" + requestUri);
        Subject subject = SecurityUtils.getSubject();

        // 如果没有登录
        if (!subject.isAuthenticated()) {
            log.info("未登录用户尝试访问需要登录的接口");
            return false;
        }

        // 判断访问接口是否需要过滤
        boolean needInterceptor = adminPermsService.needInterceptor(requestUri);
        if (!needInterceptor) {
            //无需权限
            return true;
        } else {
            //判断当前用户是否拥有相应的权限
            boolean hasPerms = false;
            String username = subject.getPrincipal().toString();
            Set<String> permsUrls = adminPermsService.listPermsUrlsByUser(username);

            for (String url : permsUrls) {
                // 匹配前缀
                if (requestUri.startsWith(url)) {
                    hasPerms = true;
                    break;
                }
            }

            if (hasPerms) {
                log.trace("用户：" + username + "访问了接口：" + requestUri);
                return true;
            } else {
                log.warn("用户：" + username + "访问了其权限不够的接口：" + requestUri);
                return false;
            }
        }
    }
}
