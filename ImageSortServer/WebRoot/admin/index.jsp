<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>图片分类后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Admin panel developed with the Bootstrap from Twitter.">
    <meta name="author" content="travis">
    <link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/site.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">   
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
          <div class="well hero-unit">
            <h1>欢迎您, 管理员</h1>
            <p>这个系统是图片分发管理系统，在这里，您可以查看用户，管理员，修改用户信息，查看图片分发数据和图片标签情况 </p>
            <p><a class="btn btn-success btn-large" href="${ pageContext.request.contextPath }/admin/QueryUsers">管理用户 &raquo;</a></p>
          </div>
          <div class="row-fluid">
            <div class="span3" >
              <h3>用户总数</h3>
              <p><a href="${ pageContext.request.contextPath }/admin/QueryUsers" class="badge badge-inverse">${userCount}</a></p>
            </div>
            <div class="span3">
              <h3>图片总数</h3>
              <p><a href="${ pageContext.request.contextPath }/admin/QueryImages" class="badge badge-inverse">${imageCount}</a></p>
            </div>
            <div class="span3">
              <h3>管理员数量</h3>
			  <p><a href="${ pageContext.request.contextPath }/admin/QueryAdmins" class="badge badge-inverse">${adminCount}</a></p>
            </div>
            <div class="span3">
              <h3>累计上线人数</h3>
			  <p><a href="#" class="badge badge-inverse">${count}</a></p>
            </div>
          </div>
		  <br />
		  <div class="row-fluid">
			<div class="page-header">
				<h1><small>数目统计</small></h1>
				<div id="main" style="height:400px;width:1000px"></div>
			</div>
			<p >
				<a href="${ pageContext.request.contextPath }/admin/InitIndex" style="display:block;margin-left:450px">
					刷新数据 &raquo;
				</a>
			</p>
		  </div>
        </div>
      </div>

      <hr>
    </div>
	 <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	  <script type="text/javascript">
	  	var userCount = '<%=session.getAttribute("userCount")%>';
	  	var imageCount = '<%=session.getAttribute("imageCount")%>';
	  	var adminCount = '<%=session.getAttribute("adminCount")%>';
	  	var count = '<%=session.getAttribute("count")%>';
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
                    tooltip: {
                        show: true
                    },
                    
                    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},				            
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
                    
                    xAxis : [
                        {
                            type : 'category',
                            data : ["用户数量","图片数量","管理员人数","在线人数"]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"数目",
                            "type":"bar",
                            "data":[userCount, imageCount, adminCount, count]
                        }
                    ]
                };
        
                myChart.setOption(option); 
            }
        );
    </script>
    <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
  </body>
</html>