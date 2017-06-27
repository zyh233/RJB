<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>上传成功</title>
  <style type="text/css">
	.success{width:200px;height:100px;border:1px solid transparent;margin:350px auto}
	.success img{margin-left:30px}
	.success span{float:right;margin-top:10px;margin-right:40px}
	.success a{ text-decoration:none;display:block;width:140px;height:40px;
				background-color:#00bb00;color:white;
				text-align:center;line-height:40px;border-radius:5px;margin:40px auto;}
  </style>
 </head>
 <body>
	<div class="success">
		<img src="img/success.png" alt="上传成功"/>
		<span>上传成功</span>
		<a href="${ pageContext.request.contextPath }/admin/index.jsp">返回主页</a>
	</div>
 </body>
</html>
