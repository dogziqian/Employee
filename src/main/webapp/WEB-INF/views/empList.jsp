<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employee Information</title>
    <!-- web路径：
    	1、不以/开始的为相对路径，以当前路径为基准查找资源，比较容易出问题
    	2、以/开始的相对路径，找资源，以服务器路径为标准，需要加上项目名称
     -->
    <link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH }/static/js/jquery-3.5.0.min.js"></script>
    <script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
  </head>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 显示标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM_CRUD</h1>
			</div>
		</div>
		<!-- 操作按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>ID</th>
						<th>EmpName</th>
						<th>Gender</th>
						<th>Email</th>
						<th>deptName</th>
						<th>Operation</th>
					</tr>
					<tr>
						<c:forEach items="${pageInfo.list }" var="emp">
							<tr>
								<td>${emp.empId }</td>
								<td>${emp.empName}</td>
								<td>${emp.gender=="M"?"男":"女" }</td>
								<td>${emp.email }</td>
								<td>${emp.department.deptName}</td>
								<td>
									<button class="btn btn-primary btn-sm">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										编辑
									</button>
									<button class="btn btn-danger btn-sm">
										<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										删除
									</button>
								</td>
							</tr>
						</c:forEach>
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">
				当前第 ${pageInfo.pageNum} 页,共 ${pageInfo.pages } 页,共  ${pageInfo.total} 条记录
			</div>
			<!-- 分页组件 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	<li><a href="${APP_PATH}/empList?pn=1">首页</a></li>
				  	<!-- 判断是否是第一页，如果不是显示上翻按钮 -->
				  	<c:if test="${pageInfo.hasPreviousPage}">
				  		<li>
				      		<a href="${APP_PATH}/empList?pn=${pageInfo.pageNum-1}" aria-label="Previous">
				        	<span aria-hidden="true">&laquo;</span>
				      		</a>
				    	</li>
				  	</c:if>
				    <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
				    	<!-- 当前页高亮显示 -->
				    	<c:if test="${page_Num == pageInfo.pageNum}">
				    		<li class="active"><a href="#">${page_Num}</a></li>
				    	</c:if>
				    	<c:if test="${page_Num != pageInfo.pageNum}">
				    		<li><a href="${APP_PATH}/empList?pn=${page_Num}">${page_Num}</a></li>
				    	</c:if>
				    </c:forEach>
				    <!-- 判断是否是最一页，如果不是显示下翻页按钮 -->
				    <c:if test="${pageInfo.hasNextPage}">
				    	<li>
				      <a href="${APP_PATH}/empList?pn=${pageInfo.pageNum+1}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				    </c:if>
				    
				    <li><a href="${APP_PATH}/empList?pn=${pageInfo.pages}">尾页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>