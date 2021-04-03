package com.mybatis.spring.listener;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SpringContextStartListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextStartListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
      /*  if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            LOGGER.info("######初始化后台管理系统api接口######");
            DocsConfig config = new DocsConfig();
            config.setProjectPath("D:\\idea_project"); // 项目根目录
            config.setProjectName("宜立方商城项目接口文档api"); // 项目名称
            config.setApiVersion("V1.0.0");       // 声明该API的版本
            config.setDocsPath("D:\\idea_project\\document\\api_doc"); // 生成API 文档所在目录
            config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
            Docs.buildHtmlDocs(config); // 执行生成文档
        }*/
    }
}
