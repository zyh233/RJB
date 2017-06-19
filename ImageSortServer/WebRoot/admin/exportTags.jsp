<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>导出标签</title>
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
				<h1>导出标签</h1>
			</div>
			<form class="form-horizontal" action="${ pageContext.request.contextPath }/admin/ExportTags" method="post">
				<fieldset>
					<div class="control-group" style="float:left">
						<label class="control-label" for="index">按关键字查找</label>
						<div class="controls">
							<input type="text" class="input-xlarge" name="index" value="" placeholder="图片" />
						</div>
					</div>
					<div style="float:right;margin-right:480px">
						<input type="submit" class="btn btn-success btn-large" value="查找" style="height:30px;line-height:15px" /> 
						<input type="submit" class="btn btn-success btn-large" value="查找全部" style="height:30px;line-height:15px" />
					</div>					
				</fieldset>
			</form>

			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>图片编号</th>
						<th>图片名称</th>
						<th>图片标签</th>
						<th>完成时间</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${!empty images}">
					<c:forEach begin="0" end="${fn:length(images)}" step="1" items="${images}" var="image" varStatus="vs">
						<tr class="list-roles">
							<td>${image.id}</td>
							<td>${image.name }</td>
							<td>${image.tags }</td>
							<td>${image.date }</td>
						</tr>
					</c:forEach>
				</c:if>				
				</tbody>
			</table>
			<div id="placeholder" style="width:80%;height:300px;">
				<a  href="${ pageContext.request.contextPath }/admin/Download" class="btn btn-success btn-large"  style="height:15px;line-height:15px">导出</a>
			</div>
		  </div>
        </div>
      </div>

      <hr>
    </div>
    <script src="js/jquery.js"></script>
    <script type="text/javascript">
    	
    </script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.resize.js"></script>	
	<script src="js/bootstrap.min.js"></script>
  </body>
</html>