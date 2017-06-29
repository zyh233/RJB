<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>用户标签情况</title>
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
				<h1>用户标签情况 <small>用户分析...</small></h1>
			</div>
			<div id="placeholder" style="width:80%;height:200px;"></div>
			
			<div id="user2ImageNum" style="width:80%;height:500px;margin-top:100px"></div>
			
			<h3 style="margin-top:100px">用户标签表</h3>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>用户编号</th>
						<th>用户名</th>
						<th>图片编号</th>
						<th>图片名称</th>
						<th>标签</th>						
						<th></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach begin="0" end="${fn:length(user2Image)}" step="1" items="${user2Image}" var="u2i" varStatus="vs">
					<tr class="list-users">
						<td>${u2i.uid }</td>
						<td>${u2i.username }</td>
						<td>${u2i.id }</td>
						<td>${u2i.name }</td>
						<td>${u2i.tag }</td> 						
						<td>
							<div class="btn-group">
								<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${ pageContext.request.contextPath }/admin/DeleteRecord?uid=${u2i.uid}&id=${u2i.id}&flag=1"><i class="icon-trash"></i> 删除</a></li>					
								</ul>
							</div>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			
		  </div>
		  <p >
				<a href="${ pageContext.request.contextPath }/admin/UserTag" style="display:block;margin-left:410px">
					刷新数据 &raquo;
				</a>
			</p>
        </div>
      </div>

      <hr>
    </div>

    <script src="js/jquery.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.resize.js"></script>	
	<script src="js/bootstrap.min.js"></script>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script type="text/javascript">
		var userCount = '<%=session.getAttribute("userCount")%>';
		var queryUserCount = '<%=session.getAttribute("queryUserCount")%>';		
		
		 require.config({
	         paths: {
	             echarts: 'http://echarts.baidu.com/build/dist'
	         }
	     });		 
		 require(
		   [
		      'echarts',
		      'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		   ],
		  function (ec) {
		  // 基于准备好的dom，初始化echarts图表
		  	var myChart = ec.init(document.getElementById('placeholder')); 
			var option = {
			    title : {
			        text: '用户占比',
			        subtext: '来自图片分发系统',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data:['活跃用户','其他用户']
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
			    calculable : true,
			    series : [
			        {
			            name:'访问来源',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
			                {value:queryUserCount, name:'活跃用户'},
			                {value:userCount-queryUserCount, name:'其他用户'},
			            ]
			        }
			    ]
			};
			 myChart.setOption(option);
			}
		);
	
		
		require(
			[
			 'echarts',
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			],
		function user2ImageNum(et){						
			var myChart = et.init(document.getElementById('user2ImageNum'));			
			
			var json = '<%=session.getAttribute("array")%>';
			json1 = eval("(" + json + ")");		
			
			var json2 = '<%=session.getAttribute("array2")%>';
			json3 = eval("(" + json2 + ")");
			
			option = {
				    title : {
				        text: '用户标签情况',
				        subtext: '来自图片分类系统'
				    },
				    tooltip : {
				        trigger: 'axis'
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
				    calculable : true,
				    xAxis : [
				        {
				        	name : '用户id',
				            type : 'category',
				            data :json1
				            	
				            	
				            	
				        }
				    ],
				    yAxis : [
				        {
				        	name : '标记图片数量',
				            type : 'value'
				        }
				    ],
				    series : [
				        
				        {
				            name:'图片数量',
				            type:'bar',
				            data:json3,				            
				            markLine : {
				                data : [
				                    {type : 'average', name : '平均值'}
				                ]
				            }
				        }
				    ]
				};
			myChart.setOption(option);
		}
		);
	</script>
  </body>
</html>