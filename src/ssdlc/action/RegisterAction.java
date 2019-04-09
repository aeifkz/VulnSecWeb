package ssdlc.action;

import java.io.IOException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ssdlc.bean.UserBean;
import ssdlc.model.DBModel;
import ssdlc.model.LogModel;


@WebServlet(name="register",urlPatterns="/register")
public class RegisterAction extends HttpServlet {
	
	static Logger log = Logger.getLogger(RegisterAction.class);
	
	private String account;
	private String password; 
	private String name;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		account = req.getParameter("account");
		password = req.getParameter("password");
		name = req.getParameter("name");
		
		log.info(LogModel.log_sanitized("Call register method " + account + " " + password + " " + name));
				
		//Finish TODO Day2 針對帳號做格式驗證, 規則 ^[A-Za-z]{4,20}$
		if(account.length()>=4 && account.length()<=20) {
			
			Pattern pattern = Pattern.compile("^[A-Za-z]{4,20}$");
			Matcher m = pattern.matcher(account);
			
			if(m.matches()) {				
				ServletContext context= getServletContext();
				
				if(context.getAttribute(account)==null) {		
					UserBean bean = new UserBean();
					bean.setAccount(account);
					bean.setPassword(password);
					bean.setName(name);			
					context.setAttribute(account, bean);
					req.setAttribute("msg","註冊成功");
				}
				else {
					req.setAttribute("msg","註冊帳號重複");
				}
				
			}
			else {
				req.setAttribute("msg","註冊帳號格式錯誤");
			}			
		}
		else {
			req.setAttribute("msg","註冊帳號長度錯誤");
		}
		
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		view.forward(req,resp);
		
	}
		
	
	
}
