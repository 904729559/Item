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
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<th>序号</th><th>id</th><th>姓名</th><th>身份证</th><th>性别</th><th>生日</th>
		</thead>
		<c:forEach items="${al_ui}" var="ui">
		<tr>
			<td>1</td><td>${ui.use_id}</td><td>${ui.use_name}</td><td>${ui.use_idcard}</td><td>${ui.use_sex}</td><td>${ui.use_birthday}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>