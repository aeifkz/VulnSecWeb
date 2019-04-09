package ssdlc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import ssdlc.model.DBModel;
import ssdlc.model.LogModel;


public class RegisterAction {
	
	static Logger log = Logger.getLogger(RegisterAction.class);
	
	private String account;
	private String password; 
	private String name;	
	
	public String register() {
		
		log.info(LogModel.log_sanitized("Call register method " + account + " " + password + " " + name));
		
		if(account.length()<4 || account.length()>20) {
			ServletActionContext.getRequest().setAttribute("msg","帳號格式有問題");
			return "info";
		}
		
		Pattern pattern = Pattern.compile("^[A-Za-z]{4,20}$");
		Matcher m = pattern.matcher(account);
		
		if(!m.matches()) {
			ServletActionContext.getRequest().setAttribute("msg","帳號格式有問題");
			return "info";
		}
		
		Connection conn = null;
		
		try {
			
			conn = new DBModel().getConnection();			
			
			String sql = "insert into user (account,password,name)  values ('" + account + "','"+password+"','"+name+"') ;";
			       sql = "insert into user (account,password,name)  values (?,?,?) ;";
			log.debug(LogModel.log_sanitized("register sql:"+sql));
			
			//Statement stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,account);
			stmt.setString(2,password);
			stmt.setString(3,name);
			
			//int rs = stmt.executeUpdate(sql);
			int rs = stmt.executeUpdate();
			
						
			ServletActionContext.getRequest().setAttribute("sql",sql);
			
			if(rs>0) {
				ServletActionContext.getRequest().setAttribute("msg","註冊成功");				
			}
			else {
				if(account!=null) {				
					ServletActionContext.getRequest().setAttribute("msg","帳號"+account+"註冊失敗");
				}
				else {
					ServletActionContext.getRequest().setAttribute("msg","註冊失敗");
				}
			}			
						
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			log.error("資料庫操作錯誤",ex);			
		}
		
		return "info";
		
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
