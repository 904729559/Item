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
	<title>注册页面</title>
	
	<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#password2").blur(function(){
				if($("#password2").val()!=$("#password").val()){
					$("#span_pw2").css("color","red");
					$("#span_pw2").html("密码不一致");
					$("#password2").val("");
				}else{
					$("#span_pw2").html("");
				}
			});			
		});
			
		
	</script>
</head>

<body>
	<div class="register1">
		<form action="Register" method="get">
			<fieldset>
				<legend>必填信息</legend>
				<div class="register1.1">																	
					<label for="name">用户名：</label><br/><input id="key_name" name="key_name" type="text" size="20" maxlength="20" required="required" value="${sessionScope.temp_uk.key_name}">
					<span style="color: red">${sessionScope.flag2}</span>
					<c:remove var="flag2" scope="session"/>
				</div>
				<div class="register1.1">
																			<!--pattern为正则表达式属性，不支持/^...$/格式 -->
					<label for="password">密码：</label><br/><input id="password" name="password" type="password" size="10" required="required" pattern="\d{6}" title="密码为6位数数字" value="${sessionScope.temp_uk.key_password}">
				</div>
				<div class="register1.1">
					<label for="password2">确认密码：</label><br/><input id="password2" type="password" size="10" required="required" pattern="\d{6}">
				<span id=span_pw2></span>
				</div>
				<div class="register1.1">																	
					<label for="idcard">身份证号码：</label><br/><input id="idcard" name="idcard" type="text" size="20" required="required" pattern="\d{18}||\d{17}x" value="${sessionScope.temp_ui.use_idcard}">
				</div>
				<div>
					<label for="user_name">姓名：</label><br/><input id="use_name" name="use_name" type="text" size="20" maxlength="20" required="required" value="${sessionScope.temp_ui.use_name}">
				</div>
			</fieldset>
			
			<fieldset>
				<legend>完善信息</legend>
				
				<div class="register1.1">																		
					<label>性别：</label><br/><input name="sex" type="radio" value="男">&nbsp<input name="sex" type="radio" value="女">
				</div>
				<div class="register1.1">
					<label for="birthday">生日：</label><br/><input id="birthday" name="birthday" type="date">
				</div>
				<div class="register1.1">
					<label>祖籍：</label><br/>
					<label for="province">省份：</label><select id="province " name="province">
						<option disabled="disabled">请选择</option>
						<option >湖南</option>
						<option >湖北</option>
						<option >广东</option>
					</select>
					<label for="city">市：</label><select id="city" name="city" required="required">
						<option disabled="disabled">请选择</option>
						<option >长沙</option>
						<option >武汉</option>
						<option >广州</option>
					</select>
				</div>				
			</fieldset>
			
				<div class="register1.1">
					<input type="submit" value="提交">
					<button><a href="Login.jsp">返回</a></button>
				</div>
			
		</form>
			
	</div>
	<c:remove var="temp_uk" scope="session"/>
	<c:remove var="temp_ui" scope="session"/>
</body>

</html>