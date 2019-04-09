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
   			
   			function check_register() {
   				
   				var account = $("#account").val();			
   				console.log("account:"+account);
   				
   				var re = new RegExp("^[A-Za-z]{4,30}$");
   				console.log("test:"+re.test(account));
   				
   				if(re.test(account)) {
   					$("#register").submit();
   				}
   				else {
   					alert("帳號格式錯誤");
   				}
   				
   			}
   			
   	  </script>
            
   </head>
   
   <body>
      
   	<form id="register" action="register" method="get">
   		帳號 : <input id="account" type="text" name="account"  /> <br/>
  		密碼 : <input id="password" type="password" name="password"  /> <br/>
  		暱稱 : <input id="name" type="text" name="name"   /> <br/>
   		<input type="button" onclick="check_register()" value="註冊"/>
	</form>
	
	SQL Debug Info:${requestScope.sql} <br/>
	
		 
   </body>
      
   
    
</html>