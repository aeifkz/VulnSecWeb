<%@ page import="java.security.SecureRandom" %>
<%@ page contentType = "text/html; charset=UTF-8" %>



<html>


   <head>    	  
      <title>Hello World</title>
      
      <script src="scripts/jquery-3.3.1.min.js"></script>
   	  <script src="scripts/bootstrap.js"></script>
   	  
   	  <script>
   	  
   			$( document ).ready(function() {   				
   				var msg = "${requestScope.msg}";   				
   				if( !(msg === "") ) {
   					console.log(msg);
   					$('body').append(msg);
   				}
   			});
   			
   			function check_edit() {
   				
   				/*
   				var account = $("#account").val();   				
   				console.log("account:"+account);
   				
   				var re = new RegExp("^[A-Za-z]{5,30}$");			
   				console.log("test:"+re.test(account));
   				*/
   				
   				$("#edit").submit();
   				
   				/*
   				if(re.test(account)) {
   					$("#edit").submit();
   				}
   				else {
   					alert("帳號格式錯誤");
   				}
   				*/
   				
   			}
   			
   	  </script>
            
   </head>
   
   <body>
   
   <%   
   		SecureRandom random = new SecureRandom();
   		String token = Math.abs(random.nextLong())+"";
   		Cookie cookie = new Cookie("token",token);
   		cookie.setHttpOnly(true);
   		response.addCookie(cookie);
   %>
      
   	<form id="edit" action="edit" method="get">  		
  		密碼 : <input id="password" type="password" name="password"  /> <br/>  		
  		暱稱 : <textarea id="name" name="name" rows="10" cols="50"></textarea> <br/>
  		<input type="hidden" name="token" value="<%=token%>" />
  		<input type="hidden" name="account" value="${sessionScope.account}" />
   		<input type="button" onclick="check_edit()" value="變更資料"/>
	</form>
		 
   </body>
    
</html>