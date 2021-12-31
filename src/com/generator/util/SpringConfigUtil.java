package com.generator.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * Title: SpringConfigTool
 * Description: 获取spring容器Bean 
 */
@Component
public class SpringConfigUtil implements ApplicationContextAware {// extends
    private static ApplicationContext context = null;
    private static SpringConfigUtil stools = null;

    public synchronized static SpringConfigUtil init() {
        if (stools == null) {
            stools = new SpringConfigUtil();
        }
        return stools;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

}