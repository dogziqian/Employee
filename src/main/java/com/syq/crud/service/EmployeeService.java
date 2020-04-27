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
	 * ��ȡԱ���б�
	 * @return
	 */
	public List<Employee> getAll() {
		return empMapper.selectByExampleWithDept(null);
	}
	/**
	 * ����Ա����Ϣ
	 * @param e
	 * @return
	 */
	public int saveEmp(Employee e) {
		return empMapper.insertSelective(e);
	}
	
	/**
	 * ���������ж�Ա���Ƿ����
	 * @param empName
	 * @return true:��ǰ���ֿ��ã�false��ʾ��ǰ���ֲ�����
	 */
	
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = empMapper.countByExample(example);
		return count == 0;
	}
	/**
	 * ����Id��ȡ�û���Ϣ
	 * @param empId
	 * @return
	 */
	public Employee getUserByName(Integer empId) {
		return empMapper.selectByPrimaryKey(empId);
	}
	/**
	 * �����û���Ϣ
	 * @param e
	 * @return
	 */
	public int updateEmp(Employee e) {
		return empMapper.updateByPrimaryKeySelective(e);
	}
	/**
	 * ����Idɾ��Ա����Ϣ
	 * @param empId
	 * @return
	 */
	public int delteById(Integer empId) {
		return empMapper.deleteByPrimaryKey(empId);
	}
	/**
	 * ����ɾ��
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
