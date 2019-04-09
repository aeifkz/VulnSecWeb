package ssdlc.action;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.owasp.encoder.Encode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.xml.internal.fastinfoset.Encoder;

import ssdlc.bean.UserBean;
import ssdlc.model.LogModel;

@WebServlet(name = "edit", urlPatterns = "/edit")
public class UserAction extends HttpServlet {

	private Integer id;
	private String account;
	private String password;
	private String name;
	private String token;

	static Logger log = Logger.getLogger(LoginAction.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Finish TODO Day2 實作 CSRF token 防禦措施
		token = req.getParameter("token");
				
		if(token==null) {
			log.info("有人在玩我的網站");
			resp.sendRedirect("main.jsp");
			return ;
		}
		else {
			boolean flag = false;
			Cookie [] req_cookie = req.getCookies();			
			for(Cookie cookie : req_cookie) {
				if(token.equals(cookie.getValue())) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				log.info("有人在玩我的網站的Cookie");
				resp.sendRedirect("main.jsp");
				return ;
			}
		}
		
		
		id = (req.getParameter("id")==null || req.getParameter("id").isEmpty()) ? null : Integer.parseInt(req.getParameter("id"));
		account = req.getParameter("account"); ;
		password = req.getParameter("password");
		name = req.getParameter("name");
		
		log.info("name:"+name);

		
		try {
			
			String FEATURE = null;
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						
			//Finish TODO Day2 針對XML解析函式關閉外部引入功能
			FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
			factory.setFeature(FEATURE, true);			
			FEATURE = "http://xml.org/sax/features/external-general-entities";
			factory.setFeature(FEATURE, false);			
			FEATURE = "http://xml.org/sax/features/external-parameter-entities";
			factory.setFeature(FEATURE, false);			
			FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
			factory.setFeature(FEATURE, false);
			
			factory.setXIncludeAware(false);
			factory.setExpandEntityReferences(false);
						
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//透過 DocumentBuilderFactory 解析輸入參數 
			Document doc = builder.parse(new InputSource(new StringReader(name)));
			
			NodeList nodes = doc.getElementsByTagName("name");
			for(int i=0; i<nodes.getLength(); i++) {
				name = nodes.item(i).getTextContent();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			log.info("XML parse錯誤");
		}
		
		log.info(LogModel.log_sanitized("Call edit method " + id + " " + account + " " + password + " " + name));
		
		ServletContext context = getServletContext();
	
		if(account!=null && context.getAttribute(account)!=null) {
			
			UserBean user = (UserBean)context.getAttribute(account);			
			HttpSession session = req.getSession();
			
			if (name != null && !name.isEmpty()) {
				user.setName(name);
				session.setAttribute("name", name);				
			}			
			if (password != null && !password.isEmpty()) {
				user.setPassword(password);
			}
			
			context.setAttribute(account,user);
			
			req.setAttribute("msg", "修改資料成功");
			
		}
		else {
			req.setAttribute("msg", "修改失敗");
		}
		
		resp.sendRedirect("main.jsp");

	}

	

}
