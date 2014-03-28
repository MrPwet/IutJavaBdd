package com.IutJavaBdd.tools;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class Singleton {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/*public static final String JDBC_URL = "jdbc:mariadb://localhost:3306/IutJavaBdd";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "";*/
	public static final DataSource DS = new BasicDataSource();
	private static boolean done = false;
	
	public static void init(String url, String userConf, String passConf) {
		if(!done) {
			BasicDataSource ds = (BasicDataSource) DS;
			ds.setDriverClassName(JDBC_DRIVER);
			ds.setUsername(userConf);
			ds.setPassword(passConf);
			ds.setUrl("jdbc:mysql://" + url);
			ds.setDefaultAutoCommit(false);
			ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			done = true;
		}
		
	}
	
	/*static {
		BasicDataSource ds = (BasicDataSource) DS;
		ds.setDriverClassName(JDBC_DRIVER);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		ds.setUrl(JDBC_URL);
		ds.setDefaultAutoCommit(false);
		ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	}*/
}
