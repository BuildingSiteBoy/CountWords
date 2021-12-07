package com.zz.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zz
 * URLPathMatchingFilter没有被声明@Bean，即没被spirng管理，所以@Aurowride无法注入
 * 使用该工具类，可以让Filter获取想要用的Service实例
 */
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
