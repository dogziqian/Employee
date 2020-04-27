package com.syq.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syq.crud.bean.Employee;
import com.syq.crud.bean.EmployeeExample;
import com.syq.crud.bean.EmployeeExample.Criteria;
import com.syq.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper empMapper;
	/**
	 * 获取员工列表
	 * @return
	 */
	public List<Employee> getAll() {
		return empMapper.selectByExampleWithDept(null);
	}
	/**
	 * 新增员工信息
	 * @param e
	 * @return
	 */
	public int saveEmp(Employee e) {
		return empMapper.insertSelective(e);
	}
	
	/**
	 * 根据名字判断员工是否存在
	 * @param empName
	 * @return true:当前名字可用，false表示当前名字不可用
	 */
	
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = empMapper.countByExample(example);
		return count == 0;
	}
	/**
	 * 根据Id获取用户信息
	 * @param empId
	 * @return
	 */
	public Employee getUserByName(Integer empId) {
		return empMapper.selectByPrimaryKey(empId);
	}
	/**
	 * 更新用户信息
	 * @param e
	 * @return
	 */
	public int updateEmp(Employee e) {
		return empMapper.updateByPrimaryKeySelective(e);
	}
	/**
	 * 按照Id删除员工信息
	 * @param empId
	 * @return
	 */
	public int delteById(Integer empId) {
		return empMapper.deleteByPrimaryKey(empId);
	}
	/**
	 * 批量删除
	 * @param emp_id_batch
	 */
	public void batchDelete(List<Integer> emp_id_batch) {
		
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from *** where empId in();
		criteria.andEmpIdIn(emp_id_batch);
		empMapper.deleteByExample(example);
	}
	
}
