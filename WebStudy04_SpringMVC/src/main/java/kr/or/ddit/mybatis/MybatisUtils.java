package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	private static SqlSessionFactory sqlSessionFactory; //SqlSessionFactory => 싱글톤 
	
	static { //MybatisUtils클래스가 실행될 때 처음딱한번만 실행되는 구문
		String configPath  = "kr/or/ddit/mybatis/mybatis-config.xml"; // mybatis-config.xml 읽어들이기 
		try(
			Reader reader = Resources.getResourceAsReader(configPath);
		) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
