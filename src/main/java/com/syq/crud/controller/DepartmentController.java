package com.syq.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syq.crud.bean.Department;
import com.syq.crud.bean.Msg;
import com.syq.crud.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService deptService;
	
	@RequestMapping("/deptList")
	@ResponseBody
	public Msg getDeptList(){
		List<Department> list = deptService.getAll();
		
		return Msg.success().add("deptList", list);
	}
	
}
