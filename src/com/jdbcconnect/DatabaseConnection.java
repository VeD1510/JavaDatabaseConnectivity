package com.jdbcconnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	public static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	public static final String JDBC_URL="jdbc:mysql://localhost:3306/bikeshowroom";
	public static final String USERNAME="root";
	public static final String PASSWORD="root";
	
	static Connection conn=null;
	
	
	public static Connection getconnect()
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			System.out.println("Driver Loaded Sucessfully...");
			
			conn=DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("Connection Estabilished...");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return conn;
	}
	
	
	public static void main(String[] args) {
		
		DatabaseConnection.getconnect();
	}
	

}

