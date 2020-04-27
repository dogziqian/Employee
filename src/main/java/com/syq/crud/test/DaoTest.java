package com.syq.crud.test;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.syq.crud.bean.Department;
import com.syq.crud.bean.DepartmentExample;
import com.syq.crud.bean.Employee;
import com.syq.crud.bean.EmployeeExample;
import com.syq.crud.dao.DepartmentMapper;
import com.syq.crud.dao.EmployeeMapper;

/**|
 * 使用spring test进行测试
 * 1、导入springtest模块
 * 2、指定@ContextConfiguration(locations的配置文件的位置
 * 3、直接autowired注入需要的组件
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class DaoTest {
	
	@Autowired
	DepartmentMapper depMapper;
	
	@Autowired
	EmployeeMapper empMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testInsert(){
		//插入部门信息
		//depMapper.insertSelective(new Department(null, "研发部"));
		//depMapper.insertSelective(new Department(null, "产品部"));
		//depMapper.insertSelective(new Department(null, "销售部"));
		depMapper.insertSelective(new Department(null, "人事部"));
		
		//插入员工信息
		//empMapper.insert(new Employee(null, "katto","M" , "machoboi@163.com", 1));
		
		//批量插入多位员工信息，可以使用执行批量操作的sqlsession
		
		/*EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++){
			String name = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, name, "M", name+"@163.com", 1));
		}
		
		System.out.println("finished!");*/
	}
	
	/*删除测试*/
	@Test
	public void testDel(){
		depMapper.deleteByPrimaryKey(6);
		//empMapper.deleteByPrimaryKey(1001);
	}
	
	/*修改测试*/
	@Test
	public void testUpdate(){
		//depMapper.updateByPrimaryKey(new Department(7,"市场部"));
		empMapper.updateByPrimaryKeySelective(new Employee(2, "lil ghost", null, "lilghost@163.com", null));
		empMapper.updateByPrimaryKeySelective(new Employee(3, "jzen", null, "jzen@163.com", null));
		
	}
	
	/*查询测试*/
	@Test
	public void testSel(){
		
		//根据id查询员工信息
		//Employee emp = empMapper.selectByPrimaryKeyWithDept(1);
		//System.out.println(emp.getEmpName());
		
		//根据id查询部门信息
		//Department dep = depMapper.selectByPrimaryKey(2);
		//System.out.println(dep.getDeptName());
		
		//查询所有员工信息
		List<Employee> empList = empMapper.selectByExampleWithDept(new EmployeeExample());
		for(int i=0;i<3;i++){
			System.out.println(empList.get(i).getEmpName()+"\t"+empList.get(i).getDepartment().getDeptName());
		}
		
		//查询所有部门信息
		/*List<Department> depList = depMapper.selectByExample(new DepartmentExample());
		for(Department d:depList){
			System.out.println(d.getDeptId()+"\t"+d.getDeptName());	
		}*/
	}
}
