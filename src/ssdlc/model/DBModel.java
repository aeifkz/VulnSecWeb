package ssdlc.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBModel {
	
	private String account = "root";
	private String password = "root";
	
	public Connection getConnection() throws Exception {		
		Class.forName("com.mysql.cj.jdbc.Driver");			
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/simple?"+"user="+account+"&password="+password+"&serverTimezone=CST");		
		return conn;
	}

}
