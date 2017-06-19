<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="/ImageSortServer/RegisterServlet" method="post">
    	用户名:<input name="username" type="text"/><br>
    	密	码:<input name="password" type="password"/><br>
    	电	话:<input name="phone" type="text"/><br>
    	邮	箱:<input name="email" type="text"/><br>
    	<input type="submit" value="登录"/>
    	<input type="reset" value="重置">
    	
    </form>
  </body>
</html>
