package com.mybatis.spring.docApi;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;
import io.github.yedaxia.apidocs.plugin.rap.RapSupportPlugin;

public class DocApiMain {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\idea_project\\mybatis-spring-demo"); // 项目根目录
        config.setProjectName("宜立方商城项目接口文档api"); // 项目名称
        config.setApiVersion("V1.0.0");       // 声明该API的版本
        config.setDocsPath("D:\\idea_project\\document\\api_doc"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        config.addPlugin(new MarkdownDocPlugin());
        config.addPlugin(new RapSupportPlugin());
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
