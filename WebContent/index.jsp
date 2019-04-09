<%@ page contentType="text/html; charset=UTF-8"%>

<html>


<head>
<title>登入頁面</title>

<script src="scripts/jquery-3.3.1.min.js"></script>
<script src="scripts/bootstrap.js"></script>

<script>

	$(document).ready(function() {
		//Finish TODO Day2 針對訊息內容作對應的消毒
		var msg = "${requestScope.msg}";
		if (!(msg === "")) {			
			alert(msg);
			
			console.log("msg:" + msg);
			console.log(xss_Sanitize(msg));
			
			//Finish TODO Day2 針對 msg 內容作 HTML 消毒
			$('body').append(xss_Sanitize(msg));
		}
	});

	function check_login() {
		$("#login").submit();
	}
	
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

	<form id="login" action="login" method="post">
		帳號 : <input id="account" type="text" name="account" /> <br /> 
		密碼 : <input id="password" type="password" name="password" /> <br />
		類型(1:HTML, 2:JS) : <input id="type" type="text" name="type" /> <br /> 
		<input type="button" onclick="check_login()" value="登入" /> 
		<input type="button" onclick="location.href='register.jsp'" value="註冊"/>
	</form>
		
	SQL Debug Info:${requestScope.sql} <br/>	

</body>


</html>