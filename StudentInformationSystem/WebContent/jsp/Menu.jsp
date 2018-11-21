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
<title>菜单页面</title>
<script src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	alert("1111111");
	$(document).ready(function(){
		$("#but1").click(function(){
			$("#menu_right").html("你好啊");
			$.get("UserinformationControl?flag=show",function(date){
				$("#menu_right").html("我很好");
				if(date!=""){
					$("#menu_right").html("123");
					
				}else{
					$("#menu_right").html("真不幸");
				}
			});
		});
	})
// 	$(document).ready(function(){
// 		alert("22222222");
// 		$("#but1").click(function(){
// 			$.post("UserinformationControl?flag=show",function(date){				
				
// 				if(date!=""){				
// 					$("#menu_right").html("你好啊！");
// 				}
// 			});
// 		});
// 		$("#2").click(function(){
// 			$.post("SubjectinformationControl?flag=show",function(date){
				
// 			});
// 		});
// 		$("#3").click(function(){
// 			$.post("UserkeyControl?flag=show",function(date){
				
// 			});
// 		});		
</script>
</head>
<body marginheight="0px" marginwidth="0px">
	<div>
		<span style="position: absolute;">你好，${sessionScope.userinformation.use_name}(${sessionScope.userkey.key_name})</span><img alt="页眉" src="image/页眉.jpg" style="width: 100%;height: 50px">
	</div>	
	<div id="menu_left">
		<button id="but1" style="background: transparent;width: 70px">基本信息</button>
		<button id="2" style="background: transparent;width: 70px">查看成绩</button>
		<button id="3" style="background: transparent;width: 70px">修改密码</button>
		<button id="4" style="background: transparent;width: 70px">注 销</button><hr>
	</div>
	<div id="menu_right">
		你丫的
		
		<c:import url='Userinformation.jsp'/>
		
	</div>
	
</body>

</html>