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
 * ����Ա����ɾ�Ĳ���Ϣ
 *
 */

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@RequestMapping("/empList")
	@ResponseBody
	public Msg getEmpListWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// ����pagehelper��ҳ���
		// ��ѯ֮ǰ������ҳ�룬ҳ���С
		PageHelper.startPage(pn, 5);
		// startPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
		List<Employee> empList = empService.getAll();
		// ʹ��pageinfo��װ��ѯ�����ֻ��Ҫ��pageinfo����ҳ��
		// pageinfo�з�װ����ϸ�ķ�ҳ��Ϣ,���Դ���������ʾ��ҳ��
		PageInfo pageInfo = new PageInfo(empList, 5);

		return Msg.success().add("pageInfo", pageInfo);
	}

	/**
	 * ��ȡҳ����ύ����Ϣ���������ݿ�
	 * 
	 * @param e
	 * @return
	 */
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	@ResponseBody
	public Msg addEmployee(@Valid Employee e, BindingResult result) {

		// �ж�ǰ���ύ��У�����Ƿ��д�û�д����ٲ������ݿ�
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

		// �ж�ǰ���ύ��У�����Ƿ��д�û�д����ٲ������ݿ�
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
	 * ����ɾ��������ɾ��
	 * @param empIds
	 * @return
	 */
	@RequestMapping(value = "/delEmp/{empIds}", method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("empIds") String empIds){
		if(empIds.contains(",")){
			String[] emp_ids = empIds.split(",");
			List<Integer> emp_list = new ArrayList<Integer>();
			//��װ��Ҫ��Ա��ID�б�
			for (String str : emp_ids) {
				emp_list.add(Integer.parseInt(str));
			}
			//����ɾ��
			empService.batchDelete(emp_list);
		}else{
			//����ɾ��
			Integer empId = Integer.parseInt(empIds);
			int count = empService.delteById(empId);
		}
		return Msg.success();
	}

	/**
	 * ��ѯԱ�����ݣ���ҳ��ѯ param pn:ҳ��
	 * 
	 * @return ҳ������
	 */
	// @RequestMapping("/empList")
	public String getEmpList(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ����pagehelper��ҳ���
		// ��ѯ֮ǰ������ҳ�룬ҳ���С
		PageHelper.startPage(pn, 5);
		// startPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
		List<Employee> empList = empService.getAll();
		// ʹ��pageinfo��װ��ѯ�����ֻ��Ҫ��pageinfo����ҳ��
		// pageinfo�з�װ����ϸ�ķ�ҳ��Ϣ,���Դ���������ʾ��ҳ��
		PageInfo pageInfo = new PageInfo(empList, 5);
		model.addAttribute("pageInfo", pageInfo);
		return "empList";
	}
}
