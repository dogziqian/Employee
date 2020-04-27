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

	<!-- 修改员工的模态框 -->
	<div class="modal fade" id="updEmpModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">修改信息</h4>
	      </div>
	      <div class="modal-body">
	        
	        <form class="form-horizontal" name="empInfo" id="empInfo">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">empName</label>
			    <div class="col-sm-10">
			     <p class="form-control-static" id="empName_upd_static"></p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Gender</label>
			    <div class="col-sm-10">
			      <label class="radio-inline">
					  <input type="radio" name="gender" id="gender1_update_input" value="M"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender2_update_input" value="F" checked="checked"> 女
					</label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" name="email" id="email_update_input" placeholder="email@163.com" onBlur=validate_add_form_Email(this)>
			      <span class="help-block"></span>
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">Department</label>
			    <div class="col-sm-6">
			    <!-- 部门提交部门id,部门信息使用Ajax从数据库中查出 -->
			      <select class="form-control" id="deptName_input" name="dId">
				  </select>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="update_emp_info">更新</button>
	      </div>
	    </div>
	  </div>
	</div>


	<!-- 新增员工的模态框 -->
	<div class="modal fade" id="addEmpModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">新增员工</h4>
	      </div>
	      <div class="modal-body">
	        <!-- 员工新增表单 -->
	        <form class="form-horizontal" name="empInfo" id="empInfo">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">empName</label>
			    <div class="col-sm-10">
			      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName" onBlur=validate_add_form_Name()>
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Gender</label>
			    <div class="col-sm-10">
			      <label class="radio-inline">
					  <input type="radio" name="gender" id="gender1_add_input" value="M"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender2_add_input" value="F" checked="checked"> 女
					</label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" name="email" id="email_add_input" placeholder="email@163.com" onBlur=validate_add_form_Email(this)>
			      <span class="help-block"></span>
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">Department</label>
			    <div class="col-sm-6">
			    <!-- 部门提交部门id,部门信息使用Ajax从数据库中查出 -->
			      <select class="form-control" id="deptName_input" name="dId">
				  </select>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="save_emp_info">保存</button>
	      </div>
	    </div>
	  </div>
	</div>

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
				<button class="btn btn-primary" data-toggle="modal" data-target="#addEmpModel" id="emp_add_btn">新增</button>
				<button class="btn btn-danger" id="delete_all_btn">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emp_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>ID</th>
							<th>EmpName</th>
							<th>Gender</th>
							<th>Email</th>
							<th>deptName</th>
							<th>Operation</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info_area"></div>
			<!-- 分页组件 -->
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>
	
	<script type="text/javascript">
		var totalRecord;
		var currPage;
		// 页面初始化完成的操作
		$(function(){
			to_page_num(1);
		});
		
		//跳转到第几页
		function to_page_num(pn){
			$.ajax({
				url:"${APP_PATH}/empList",
				data:"pn="+pn,
				type:"GET",
				success:function(result){
					//请求成功的回调函数
					//console.log(result);
					//解析并显示员工信息
					build_emp_info(result);
					//解析并分页信息
					build_page_info(result);
					build_page_nav(result);
				}
			});
		}
		
		//显示员工信息
		function build_emp_info(result){
			
			//清空表格中信息
			$("#emp_table tbody").empty();
			
			var emps = result.extend.pageInfo.list;//所有员工信息
			$.each(emps,function(index,item){
				var checkBoxTd = $("<td></td>").append("<input type='checkbox' class='check_item'/>")
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var gender = item.gender=='M'?"男":"女";
				var genderTd = $("<td></td>").append(gender);
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>").append(item.department.deptName);
				
				//按钮的添加
				var updBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				
				updBtn.attr("edit-id",item.empId);
				
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm del_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				
				delBtn.attr("edit-id",item.empId);
				var btnTd = $("<td></td>").append(updBtn).append(" ").append(delBtn);
				//append方法执行后返回的元素还是原来的元素
				$("<tr></tr>").append(checkBoxTd).append(empIdTd).append(empNameTd).append(genderTd)
				.append(emailTd).append(deptNameTd).append(btnTd).appendTo("#emp_table tbody");
			});
		}
		
		//显示分页信息
		function build_page_info(result){
			$("#page_info_area").empty();
			$("#page_info_area").append("当前第 "+result.extend.pageInfo.pageNum+" 页,共 "+result.extend.pageInfo.pages+" 页,共  "+result.extend.pageInfo.total+" 条记录");
			totalRecord = result.extend.pageInfo.total;
			currPage = result.extend.pageInfo.pageNum;
		}
		//显示分页导航信息并添加动作
		function build_page_nav(result){
			
			$("#page_nav_area").empty();
			
			var ul = $("<ul></ul>").addClass("pagination")
			
			//首页，前一个，后一个，末页
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append($("<span></span>").append("&laquo;")).attr("href","#"));
			var nextPageLi = $("<li></li>").append($("<a></a>").append($("<span></span>").append("&raquo;")).attr("href","#"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			
			//判断是否有前一页
			if(result.extend.pageInfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else{
				//为页面添加首页，上一页的点击事件
				firstPageLi.click(function(){
					to_page_num(1);
				});
				prePageLi.click(function(){
					to_page_num(result.extend.pageInfo.pageNum-1);
				});
			}
			
			ul.append(firstPageLi).append(prePageLi);//添加首页和前一页
			
			//遍历添加页码
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numLi = $("<li></li>").append($("<a></a>").attr("href","#").append(item));
				if(item == result.extend.pageInfo.pageNum){
					numLi.addClass("active");
				}
				numLi.click(function(){
					to_page_num(item);
				});
				ul.append(numLi);
			});
			
			//判断是否有后一页
			if(result.extend.pageInfo.hasNextPage == false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");		
			}else{
				lastPageLi.click(function(){
					to_page_num(result.extend.pageInfo.pages);
				});
				
				nextPageLi.click(function(){
					to_page_num(result.extend.pageInfo.pageNum+1);
				});
			}
			
			ul.append(nextPageLi).append(lastPageLi);//添加下一页和尾页
			
			//将ul添加到nav中
			var navElm = $("<nav></nav>").append(ul);
			
			navElm.appendTo("#page_nav_area");
		}
		
		//重置表单样式及内容
		function form_reset(ele){
			//重置内容
			$(ele)[0].reset();
			//重置样式
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
		}
		
		//给新增按钮绑定事件，弹出模态框时查询部门信息添加到下拉列表中
		$("#emp_add_btn").click(function(){	
			//点击添加按钮时将表单中上一次的数据清除
			form_reset("#addEmpModel form");
			//$("#addEmpModel form")[0].reset();
			getDeptInfo("#addEmpModel select");
		});
		
		//获取部门信息并添加到下拉列表中
		function getDeptInfo(ele){
			$.ajax({
				url:"${APP_PATH}/deptList",
				type:"GET",
				success:function(result){
					//请求成功的回调函数
					//console.log(result);
					//<option>1</option>
					//添加之前先清空
					$(ele).empty();
					$.each(result.extend.deptList,function(index,item){
						var deptOption = $("<option></option>").append(item.deptName).attr("value",item.deptId);
						deptOption.appendTo(ele);
					});
				}
			});
		}
		
		//校验添加员工表单数据
		function validate_add_form_Name(){
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{5,16}$)|(^[\u2E80-\u9FFF]{2,5})/;//员工名称正则表达式
			
			//用户名不通过
			if(!regName.test(empName)){
				//alert("用户名长度为5-16位英文或者2-5位中文");
				show_validate_msg("#empName_add_input","error","用户名长度为5-16位英文或者2-5位中文");				
				return false;
			}else{
				show_validate_msg("#empName_add_input","success","");
			}
			return true;
		}
		function validate_add_form_Email(ele){
			//校验email
			var email = $(ele).val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			
			if(!regEmail.test(email)){
				//alert("邮件格式错误");
				show_validate_msg(ele,"error","邮件格式错误");	
				return false;
			}else{
				show_validate_msg(ele,"success","");
			}
			
			return true;
		}
		
		//显示校验结果的信息
		function show_validate_msg(ele,status,msg){
			//首先移除原来的显示样式
			$(ele).parent().removeClass("has-error has-success");
			if("success"==status){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text("");
			}else if("error"==status){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		
		//校验当前名字是否重复
		$("#empName_add_input").change(function(){
			if(!validate_add_form_Name()){
				return false;
			}
			var empName = $("#empName_add_input").val();
			$.ajax({
				url:"${APP_PATH}/checkEmp",
				type:"GET",
				data:"empName="+empName,
				success:function(result){
					if(result.code == 100){
						//当前名字可用
						show_validate_msg("#empName_add_input","success","用户名可用");
						$("#save_emp_info").attr("ajax-va","success");
					}else{
						show_validate_msg("#empName_add_input","error","用户名不可用");
						$("#save_emp_info").attr("ajax-va","error");
					}
				}
			});
		});
		
		//保存员工信息
		$("#save_emp_info").click(function(){
			//校验表单信息
			if(!validate_add_form_Name() || !validate_add_form_Email("#email_add_input")){
				return false;
			}
			//判断用户名校验是否通过
			if($(this).attr("ajax-va")=="error"){
				return false;
			}
			//1、发送ajax请求，保存员工信息
			$.ajax({
				url:"${APP_PATH}/addEmp",
				type:"POST",
				data:$("#addEmpModel form").serialize(),
				success:function(result){
					if(result.code==100){
						//2、得到返回信息，关闭模态框
						$('#addEmpModel').modal('hide');
						//alert(result.message);
						//3、来到最后一页，显示刚添加的数据
						to_page_num(totalRecord);
					}else{
						if(undefined != result.extend.errorFileds.email){
							//显示邮箱错误信息
							show_validate_msg("#email_add_input","error",result.extend.errorFileds.email);
						}
						if(undefined != result.extend.errorFileds.empName){
							//显示员工名称错误信息
							show_validate_msg("#empName_add_input","error",result.extend.errorFileds.empName);
						}
						
					}
					
				}
			});
		});
		
		//给编辑按钮绑定事件
		//button.click()是在按钮创建之前就绑定了事件，所以绑定是事件
		//可以在创建按钮的时候绑定事件，用到on()方法
		$(document).on("click",".edit_btn",function(){
			//alert("edit");
			//查出员工信息
			getEmpInfoByName($(this).attr("edit-id"));
			//查出部门信息
			getDeptInfo("#updEmpModel select");
			//把员工ID传递给模态框
			$("#update_emp_info").attr("edit-id",$(this).attr("edit-id"));
			//弹出模态框
			$("#updEmpModel").modal({
				backdrop:"static"
			});
		});
		
		function getEmpInfoByName(empId){
			$.ajax({
				url:"${APP_PATH}/getEmp/"+empId,
				type:"GET",
				success:function(result){
					//console.log(result);
					var empData = result.extend.empInfo;
					$("#empName_upd_static").text(empData.empName);
					$("#email_update_input").val(empData.email);
					$("#updEmpModel input[name=gender]").val([empData.gender]);
					$("#updEmpModel select").val([empData.dId]);
				}
			});
		}
		
		//点击更新触发事件
		$("#update_emp_info").click(function(){
			//检查修改的邮箱是否符合规定
			if(!validate_add_form_Email("#email_update_input")){
				return false;
			}
			//检查通过之后发送请求去数据库修改
			$.ajax({
				url:"${APP_PATH}/updEmp/"+$(this).attr("edit-id"),
				type:"PUT",
				data:$("#updEmpModel form").serialize(),
				success:function(result){
					//先判断后端返回的结果是否正确
					if(result.code==200){
						if(undefined != result.extend.errorFileds.email){
							//显示邮箱错误信息
							show_validate_msg("#email_add_input","error",result.extend.errorFileds.email);
						}
					}else if(result.code == 100){
						alert("修改成功");
						//修改成功之后将当前的模态框隐藏
						$('#updEmpModel').modal('hide');
						//刷新当前页
						to_page_num(currPage);
					}
					
				}
			});
		});
		
		//给删除按钮添加事件
		$(document).on("click",".del_btn",function(){
			var empId = $(this).attr("edit-id");
			//弹出是否确认删除框
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			if(confirm("确认删除【"+empName+"】的信息吗？")){
				//发送ajax请求
				$.ajax({
					url:"${APP_PATH}/delEmp/"+empId,
					type:"DELETE",
					success:function(result){
						if(result.code == 100){
							alert("删除成功！");
						}else{
							alert("删除失败！");
						}
						to_page_num(currPage);
					}
				});
			}
		});
		
		
		//处理全选和全不选的功能
		$("#check_all").click(function(){
			//attr获取checked是undifined
			//用prop获取原生dom的属性，用attr获取自定义的属性的值
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		
		//item全部选中时全选也需要选中
		$(document).on("click",".check_item",function(){
			var flag = $(".check_item:checked").length == $(".check_item").length;
			$("#check_all").prop("checked",flag);
		});
		
		//为全部删除按钮添加事件
		$("#delete_all_btn").click(function(){
			var empNames ="";
			var empIds="";
			$.each($(".check_item:checked"),function(){
				empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
				empIds += $(this).parents("tr").find("td:eq(1)").text()+",";
			});
			//去除empNames多余的,
			empNames = empNames.substring(0,empNames.length-1);
			empIds = empIds.substring(0,empNames.length-1);
			if(confirm("确定删除【"+empNames+"】的信息吗？")){
				//发送ajax请求
				$.ajax({
					url:"${APP_PATH}/delEmp/"+empIds,
					type:"DELETE",
					success:function(result){
						alert(result.message);
						to_page_num(currPage);
					}
				});
			}
		});
		
		
	</script>
	
</body>
</html>