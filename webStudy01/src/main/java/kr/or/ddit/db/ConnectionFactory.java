package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Factory Object[Method] Pattern : 필요 객체의 생성을 전담하는 객체를 별도 운영하는 구조
 */
public class ConnectionFactory {
	private static String url ;
	private static String user;
	private static String password; 
	// public class ConnectionFactory {}가 메모리를 로딩할 때 딱 한번 실행됨
	
	private static DataSource ds;
	static {
		String path = "/kr/or/ddit/db/dbinfo.properties";
		try (
			InputStream is = ConnectionFactory.class.getResourceAsStream(path); 
		)
		{
			Properties dbInfo = new Properties();
			dbInfo.load(is);
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");

			
//			Class.forName(dbInfo.getProperty("driverClassName"));
			
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(dbInfo.getProperty("driverClassName"));
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(password);
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initialSize")));
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdie"))); //setInitialSize = setMaxIdle 똑같아야함
			
			
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal"))); //6번째 요구자가 들어왔을 때 여유분 5개를 더 만들 수 있다
//			쿨링은 미리 만들어논다의 개념도 있지만 재활용을 할 수 있다
			bds.setMaxWaitMillis(Integer.parseInt(dbInfo.getProperty("maxWait"))); //11번째는에서 만약 반납된 게 없다면  200초 기다렸다가 
//			반납되면 사용하고 없다면 에러 발생
			
			ds = bds;
			
			
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}

	public static Connection getConnection() throws SQLException {
		
//		Connection conn = DriverManager.getConnection(url, user, password);
		return ds.getConnection();
	}

}
