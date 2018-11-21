<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
<script>
	function fun(){
		var name=document.getElementById("n").value;
		var password=document.getElementById("pw").value;
		var n=/^\S+$/;
		var pw=/^\d{6}$/;
		if(n.test(name)){
			document.getElementById("1").innerHTML="√";
			document.getElementById("1").style.color="green";
		}else{
			document.getElementById("1").innerHTML="×";
			document.getElementById("1").style.color="red";
		}
		if(pw.test(password)){
			document.getElementById("2").innerHTML="√";
			document.getElementById("2").style.color="green";
		}else{
			document.getElementById("2").innerHTML="×";
			document.getElementById("2").style.color="red";
		}
	}
</script>
</head>
<body marginheight="0px" marginwidth="0px">		
	<div style="height:600px;width: 100% ;position: absolute">		
		<table border="0" style="margin:230px auto 200px auto;text-align: center;table-layout: fixed;height: 200px;width: 400px">
			<form action="LoginControl" method="post" >
				<tr>
					<td colspan="2"><h2>欢迎登陆学信系统</h2></td>
				<tr>
					<td>用户名：
					<input id="n" name="key_name" type="text" size="10" value="${cookie.key_name.value}" style="background:transparent" onblur="fun()">
					<span id="1"></span>
					</td>
					<td>密码：
					<input id="pw" name="key_password" type="password" size="10" value="${cookie.key_password.value}" title="密码为六位数数字" style="background:transparent" onblur="fun()">
					<span id="2"></span>
					</td>
				</tr>
				<tr style="height: 50px">
					<td colspan="2">
					<input type="submit" value="登陆" style="width: 100px;background:transparent" >
					<input name="memory" type="checkbox" value="yes" checked="checked">记住密码
					</td>					
				</tr>
				<tr>
					<td><a href="Register.jsp" target="_blank">注册新用户</td><td><a href="?" target="_blank">找回密码</a></td>
				</tr>
			</form>
		</table>
		
		<c:if test="${not empty sessionScope.flag}">
					<%--${param.键名}EL表达式从url中获取参数--%>
		<p style="color: black;"><strong>${sessionScope.flag}</strong><p>			
		<p style="color: red;">${param.result}<p> 
		<c:remove var="flag" scope="session"/>
		</c:if>	
		
	</div>
	<img alt="this is a backgroundpicture" src="image/登陆背景.jpg" style="height:700px;width: 100%">
</body>


</html>