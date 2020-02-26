package com.mybatis.spring.sysDict;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName com.mybatis.spring.sysDict.Generator
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-12 12:53
 **/
public class Generator {
	public static void main(String[] args)throws Exception{
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		InputStream resourceAsStream = Generator.class.getResourceAsStream("/generatorConfig.xml");
		ConfigurationParser configurationParser = new ConfigurationParser(warnings);
		Configuration configuration = configurationParser.parseConfiguration(resourceAsStream);
		resourceAsStream.close();
		DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
		myBatisGenerator.generate(null);
		for (String wanning : warnings) {
			System.out.println(wanning);
		}
	}
}
