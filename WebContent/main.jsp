<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<%@ page import="org.owasp.encoder.Encode" %>

<html>
   <head>  	  
      <title>main page</title>
      
      <script src="scripts/jquery-3.3.1.min.js"></script>
   	  <script src="scripts/bootstrap.js"></script>
   	
   	<script>  
   	  $(document).ready(function() {
   		//finish TODO Day2 針對訊息內容作對應的消毒
   		//編碼做在後端
		var msg = "${requestScope.msg}";
		if (!(msg === "")) {			
			alert(msg);
			//finish TODO Day2 針對 msg 內容作 HTML 消毒
			$('body').append(html_encode(msg));
		}
	  });
   	  
  	function html_encode(str) {		
		str = str.replace(/</gi,"&#60;");		
		str = str.replace(/>/gi,"&#62;");		
		str = str.replace(/"/gi,"&#34;");		
		str = str.replace(/'/gi,"&#39;");		
		return str;
	}
   	  
   	  
   	 </script>
            
   </head>
   
   <body>
   
	   	<form action="edit.jsp" method="get">
	   		<!-- finish TODO Day2 針對 account,name 內容作 HTML消毒  -->
	  		帳號 : <%= Encode.forHtml((String)(session.getAttribute("account"))) %><br/>
	  		姓名 : <%= Encode.forHtml((String)(session.getAttribute("name"))) %> <br/>
	   		<input type="submit" value="修改"/> 
	   		<input type ="button" onclick="location.href='loginOut.do'" value="登出"></input>
		</form>
		
		SQL Debug Info:${requestScope.sql} <br/>
		 
   </body>
    
</html>