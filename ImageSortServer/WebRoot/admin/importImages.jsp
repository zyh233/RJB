<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>导入图片</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Admin panel developed with the Bootstrap from Twitter.">
    <meta name="author" content="travis">

    <link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/site.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <style type="text/css">
    html, body {
        height: 100%;
    }
    </style>
  </head>
  <body>
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">后台管理系统</a>
          <div class="btn-group pull-right">
			<a class="btn" href="my-profile.jsp"><i class="icon-user"></i> 管理员</a>
            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li class="divider"></li>
              <li><a href="#">注销</a></li>
            </ul>
          </div>
          <div class="nav-collapse">
            <ul class="nav">
			<li><a href="index.jsp">主页</a></li>
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">用户 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="new-user.jsp">添加用户</a></li>
					<li class="divider"></li>
					<li><a href="users.jsp">管理用户</a></li>
				</ul>
			  </li>
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">管理员 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="new-role.jsp">添加管理员</a></li>
					<li class="divider"></li>
					<li><a href="${ pageContext.request.contextPath }/admin/QueryAdmins">管理管理员</a></li>
				</ul>
			  </li>
			  <li><a href="stats.jsp">状态</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header"><i class="icon-wrench"></i> 管理</li>
              <li><a href="${ pageContext.request.contextPath }/admin/QueryUsers">用户</a></li>
              <li><a href="${ pageContext.request.contextPath }/admin/QueryAdmins">管理员</a></li>
              <li><a href="${ pageContext.request.contextPath }/admin/QueryImages">图片</a></li>
              <li class="nav-header"><i class="icon-signal"></i> 数据</li>
              <li><a href="${ pageContext.request.contextPath }/admin/ImageTag">图片分发情况</a></li>
              <li><a href="${ pageContext.request.contextPath }/admin/UserTag">用户标签情况</a></li>
			  <li><a href="exportTags.jsp">导出标签</a></li>
              <li><a href="importImages.jsp">导入图片</a></li>
              <li class="nav-header"><i class="icon-user"></i> 个人信息</li>
			  <li><a href="#">注销</a></li> 
            </ul>
          </div>
        </div>
        <div class="span9">
		  <div class="row-fluid">
			<div class="page-header">
				<h1>导入图片</h1>
			</div>

			<form name="xx" action="${ pageContext.request.contextPath }/admin/AddImage" method="post" enctype="multipart/form-data">
				<table id="tb" border="1" class="table table-striped table-bordered table-condensed">
					<tr>
						<td>File</td>
						<td>
							<input type="file" class="file" name="file" multiple="multiple" >	
						</td>
						<td>
							<button class="btn" onclick="_del(this);">删除</button>
						</td>
					</tr>
				</table>
				<br/>
				<input type="button" class="btn" onclick="_submit();" value="上传">
				<input onclick="_add();" class="btn" type="button" value="增加">
		   </form>
			<div id="placeholder" style="width:80%;height:300px;"></div>
		  </div>
        </div>
      </div>

      <hr>
    </div>
		<script type="text/javascript">
	   function _add(){
		 var tb = document.getElementById("tb");
		 //写入一行
		 var tr = tb.insertRow();
		 //写入列
		 var td = tr.insertCell();
		  //写入数据
		 td.innerHTML="File：";
		 //再声明一个新的td
		 var td2 = tr.insertCell();
		 //写入一个input
		 td2.innerHTML='<input type="file" name="file" multiple="multiple"/>';

		 var td3= tr.insertCell();
		 td3.innerHTML='<button class="btn" onclick="_del(this);">删除</button>';
	   }
	   function _del(btn){
		 var tr = btn.parentNode.parentNode;
		 //alert(tr.tagName);
		 //获取tr在table中的下标
		 var index = tr.rowIndex;
		 //删除
		 var tb = document.getElementById("tb");
		 tb.deleteRow(index);
	   }
	   function _submit(){
		 //遍历所的有文件
		 var files = document.getElementsByName("file");
		 if(files.length==0){
		   alert("没有可以上传的文件");
		   return false;
		 }
		 for(var i=0;i<files.length;i++){
		   if(files[i].value==""){
			 alert("第"+(i+1)+"个文件不能为空");
			 return false;
		   }
		 }
		document.forms['xx'].submit();
	   }
	 </script>
    <script src="js/jquery.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.resize.js"></script>	
	<script src="js/bootstrap.min.js"></script>
  </body>
</html>

