<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

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
      
   	<form id="edit" action="edit.do" method="get">  		
  		密碼 : <input id="password" type="password" name="password"  /> <br/>  		
  		暱稱 : <input id="name" type="text" name="name"   /> <br/>
  		<input type="hidden" name="id" value="${sessionScope.id}" />
   		<input type="button" onclick="check_edit()" value="變更資料"/>
	</form>
		 
   </body>
      
   
    
</html>