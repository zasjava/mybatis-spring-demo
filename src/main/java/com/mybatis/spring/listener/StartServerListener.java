package com.mybatis.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *  监听ServletContext对象
 *
 */
@WebListener("startServer")
public class StartServerListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartServerListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("服务器start----------------------------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("服务器stop----------------------------------");
    }
}
