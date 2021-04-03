package com.mybatis.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class SpringContextStopListener implements ApplicationListener<ContextClosedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextStartListener.class);
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        LOGGER.info("spring容器关闭");
    }
}
