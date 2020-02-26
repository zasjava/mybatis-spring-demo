package com.mybatis.spring.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;

/**
 * @ClassName BaseMapperTest
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-10 19:03
 **/
public class BaseMapperTest {
	private static SqlSessionFactory sqlSessionFactor;
	@BeforeClass
	public static void init(){
		try {
			Reader resourceAsReader = Resources.getResourceAsReader("mybatis_config.xml");
			sqlSessionFactor = new SqlSessionFactoryBuilder().build(resourceAsReader);
			resourceAsReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public SqlSession getSqlSession(){
		return sqlSessionFactor.openSession(true);
	}
}
