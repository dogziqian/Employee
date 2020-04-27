package com.syq.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syq.crud.bean.Department;
import com.syq.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentMapper deptMapper;	

	public List<Department> getAll() {
		return deptMapper.selectByExample(null);
	}

}
