<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.owasp.encoder.Encode" %>

<html>


<head>
<title>登入頁面</title>

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
		console.log("str:"+str);
		str = str.replace(/>/gi,"&#62;");
		console.log("str:"+str);
		str = str.replace(/"/gi,"&#34;");
		console.log("str:"+str);
		str = str.replace(/'/gi,"&#39;");
		console.log("str:"+str);
		return str;
	}

	function check_login() {
		$("#login").submit();
	}
	
</script>

</head>

<body>

	<form id="login" action="login.do" method="post">
		帳號 : <input id="account" type="text" name="account" /> <br /> 
		密碼 : <input id="password" type="password" name="password" /> <br /> 
		<input type="button" onclick="check_login()" value="登入" /> 
		<input type="button" onclick="location.href='register.jsp'" value="註冊"/>
	</form>
		
	SQL Debug Info:${requestScope.sql} <br/>	

</body>


</html>