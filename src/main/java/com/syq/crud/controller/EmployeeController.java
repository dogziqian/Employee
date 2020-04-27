package com.syq.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syq.crud.bean.Employee;
import com.syq.crud.bean.Msg;
import com.syq.crud.service.EmployeeService;

/**
 * 处理员工增删改查信息
 *
 */

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@RequestMapping("/empList")
	@ResponseBody
	public Msg getEmpListWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入pagehelper分页插件
		// 查询之前，传入页码，页面大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的查询就是分页查询
		List<Employee> empList = empService.getAll();
		// 使用pageinfo包装查询结果，只需要将pageinfo交给页面
		// pageinfo中封装了详细的分页信息,可以传入连续显示的页数
		PageInfo pageInfo = new PageInfo(empList, 5);

		return Msg.success().add("pageInfo", pageInfo);
	}

	/**
	 * 获取页面表单提交的信息并插入数据库
	 * 
	 * @param e
	 * @return
	 */
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	@ResponseBody
	public Msg addEmployee(@Valid Employee e, BindingResult result) {

		// 判断前端提交的校验结果是否有错，没有错误再插入数据库
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();

			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFileds", map);
		} else {
			int count = empService.saveEmp(e);

			if (count != 0) {
				return Msg.success();
			} else {
				return Msg.fail();
			}
		}
	}

	@RequestMapping(value = "/checkEmp")
	@ResponseBody
	public Msg checkEmp(@RequestParam(value = "empName") String empName) {
		String reg = "(^[a-zA-Z0-9_-]{5,16}$)|(^[\u2E80-\u9FFF]{2,5})";

		if (!empName.matches(reg)) {
			return Msg.fail();
		}

		boolean flag = empService.checkUser(empName);

		if (flag) {
			return Msg.success();
		} else {
			return Msg.fail();
		}
	}

	@RequestMapping(value = "/getEmp/{empId}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmpByName(@PathVariable("empId") Integer empId) {
		Employee e = empService.getUserByName(empId);
		if (e != null) {
			return Msg.success().add("empInfo", e);
		} else {
			return Msg.fail();
		}
	}

	@RequestMapping(value = "/updEmp/{empId}", method = RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmpInfo(@Valid Employee e, BindingResult result) {

		// 判断前端提交的校验结果是否有错，没有错误再插入数据库
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();

			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFileds", map);
		} else {
			//e.setEmpId(empId);
			
			int count = empService.updateEmp(e);

			if (count != 0) {
				return Msg.success();
			} else {
				return Msg.fail();
			}
		}
	}
	
	/**
	 * 单个删除和批量删除
	 * @param empIds
	 * @return
	 */
	@RequestMapping(value = "/delEmp/{empIds}", method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("empIds") String empIds){
		if(empIds.contains(",")){
			String[] emp_ids = empIds.split(",");
			List<Integer> emp_list = new ArrayList<Integer>();
			//封装需要的员工ID列表
			for (String str : emp_ids) {
				emp_list.add(Integer.parseInt(str));
			}
			//批量删除
			empService.batchDelete(emp_list);
		}else{
			//单个删除
			Integer empId = Integer.parseInt(empIds);
			int count = empService.delteById(empId);
		}
		return Msg.success();
	}

	/**
	 * 查询员工数据，分页查询 param pn:页号
	 * 
	 * @return 页面名称
	 */
	// @RequestMapping("/empList")
	public String getEmpList(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 引入pagehelper分页插件
		// 查询之前，传入页码，页面大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的查询就是分页查询
		List<Employee> empList = empService.getAll();
		// 使用pageinfo包装查询结果，只需要将pageinfo交给页面
		// pageinfo中封装了详细的分页信息,可以传入连续显示的页数
		PageInfo pageInfo = new PageInfo(empList, 5);
		model.addAttribute("pageInfo", pageInfo);
		return "empList";
	}
}
