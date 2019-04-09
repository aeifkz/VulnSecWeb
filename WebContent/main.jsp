<%@ page import="org.owasp.encoder.Encode" %>
<%@ page contentType = "text/html; charset=UTF-8" %>

<html>
   <head>  	  
      <title>main page</title>
      
      <script src="scripts/jquery-3.3.1.min.js"></script>
   	  <script src="scripts/bootstrap.js"></script>
   	
   	<script>  
   	  $(document).ready(function() {
   		//Finish TODO Day2 針對訊息內容作對應的消毒
		var msg = "${requestScope.msg}";
		if (!(msg === "")) {			
			alert(msg);
			//Finish TODO Day2 針對 msg 內容作 HTML 消毒
			$('body').append(xss_Sanitize(msg));
		}
	  });
   	  
   	  function xss_Sanitize(data) {
		data = data.replace(/&/g, "&#38;");
		data = data.replace(/</g, "&#60;");
		data = data.replace(/>/g, "&#62;");
		data = data.replace(/'/g, "&#39;");
		data = data.replace(/"/g, "&#34;");
		return data;
   	  }
   	  
   	 </script>
            
   </head>
   
   <body>
   
	   	<form>
	   		<!-- Finish TODO Day2 針對 account,name 內容作 HTML消毒  -->	   		
	  		帳號 : <%= Encode.forHtml((String)session.getAttribute("account"))%> <br/>
	  		姓名 : <%= Encode.forHtml((String)session.getAttribute("name")) %> <br/>	  		
	   		<input type="button" onclick="location.href='edit.jsp'" value="修改"/> 
	   		<input type="button" onclick="location.href='logout'" value="登出"></input>
		</form>
				
		SQL Debug Info:${requestScope.sql} <br/>
		 
   </body>
    
</html>