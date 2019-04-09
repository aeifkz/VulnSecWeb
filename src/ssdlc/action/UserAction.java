package ssdlc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import ssdlc.model.DBModel;
import ssdlc.model.LogModel;

public class UserAction {
	
	private Integer id;
	private String password;
	private String name;
	private String csrf_token;
	
	static Logger log = Logger.getLogger(LoginAction.class.getName());	
	
	public String edit() {
		
		if(csrf_token==null || !csrf_token.equals(ServletActionContext.getRequest().getSession().getAttribute("csrf_token"))) {			
			log.error("沒帶CSRF token:"+csrf_token);
			return "info";
		}
		
				
		log.info(LogModel.log_sanitized("Call edit method " + id + " " + password + " " + name));

		Connection conn = null;

		try {
			
			conn = new DBModel().getConnection();			
			
			String sql = "update user set account=account ";
			
			if(password!=null && !password.isEmpty()) {
				//sql = sql + ", password='" + password + "'";
				sql = sql + ", password=? ";				
			}
			
			if(name!=null && !name.isEmpty()) {
				//sql = sql + ", name='" + name + "'";
				sql = sql + ", name=? ";
			}
			
			//sql = sql + " where id=" + id;
			sql = sql + " where id=? ";
			
			log.debug(LogModel.log_sanitized("edit sql:"+sql));
			ServletActionContext.getRequest().setAttribute("sql",sql);
						
			//Statement stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(password!=null && !password.isEmpty()) {
				stmt.setString(index,password);
				index++;
			}
			
			if(name!=null && !name.isEmpty()) {
				stmt.setString(index,name);
				index++;
			}
			
			stmt.setInt(index,id);
						
			//int rs = stmt.executeUpdate(sql);
			int rs = stmt.executeUpdate();
						
			if(rs>0) {
				
				ServletActionContext.getRequest().setAttribute("msg","修改資料成功");
				
				Map<String, Object> session = ActionContext.getContext().getSession();				
				if(name!=null && !name.isEmpty()) {
					session.put("name",name);
				}
				
			}
			else {				
				ServletActionContext.getRequest().setAttribute("msg","修改失敗");				
			}			
						
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			log.error("資料庫操作錯誤",ex);			
		}
		
		return "info";
	}
	
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCsrf_token() {
		return csrf_token;
	}
	public void setCsrf_token(String csrf_token) {
		this.csrf_token = csrf_token;
	}
	
	

}
