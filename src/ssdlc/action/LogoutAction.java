package ssdlc.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="logout",urlPatterns={"/logout"})
public class LogoutAction extends HttpServlet  {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Finish TODO Day2 正確實作登出機制
		req.getSession().invalidate();
		resp.sendRedirect("index.jsp");		
	}

}
