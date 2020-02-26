package com.mybatis.spring.test;

import com.mybatis.spring.sysDict.mapper.SysDictMapper;
import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.pojo.SysDictExample;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @ClassName com.mybatis.spring.test.Test
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 11:51
 **/
public class Test extends BaseMapperTest {
	@org.junit.Test
	public void testSelectAll(){
		SqlSession sqlSession = getSqlSession();
		try {
			SysDictMapper mapper = getSqlSession().getMapper(SysDictMapper.class);
			//List<SysDict> sysDicts = mapper.selectByExample(new SysDictExample());
			/*for (SysDict sysDict : sysDicts) {
				System.out.println(sysDict);
			}*/
		}finally {
			sqlSession.close();
		}

	}
}
