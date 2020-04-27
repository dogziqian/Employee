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
 * ʹ��spring test���в���
 * 1������springtestģ��
 * 2��ָ��@ContextConfiguration(locations�������ļ���λ��
 * 3��ֱ��autowiredע����Ҫ�����
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
		//���벿����Ϣ
		//depMapper.insertSelective(new Department(null, "�з���"));
		//depMapper.insertSelective(new Department(null, "��Ʒ��"));
		//depMapper.insertSelective(new Department(null, "���۲�"));
		depMapper.insertSelective(new Department(null, "���²�"));
		
		//����Ա����Ϣ
		//empMapper.insert(new Employee(null, "katto","M" , "machoboi@163.com", 1));
		
		//���������λԱ����Ϣ������ʹ��ִ������������sqlsession
		
		/*EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++){
			String name = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, name, "M", name+"@163.com", 1));
		}
		
		System.out.println("finished!");*/
	}
	
	/*ɾ������*/
	@Test
	public void testDel(){
		depMapper.deleteByPrimaryKey(6);
		//empMapper.deleteByPrimaryKey(1001);
	}
	
	/*�޸Ĳ���*/
	@Test
	public void testUpdate(){
		//depMapper.updateByPrimaryKey(new Department(7,"�г���"));
		empMapper.updateByPrimaryKeySelective(new Employee(2, "lil ghost", null, "lilghost@163.com", null));
		empMapper.updateByPrimaryKeySelective(new Employee(3, "jzen", null, "jzen@163.com", null));
		
	}
	
	/*��ѯ����*/
	@Test
	public void testSel(){
		
		//����id��ѯԱ����Ϣ
		//Employee emp = empMapper.selectByPrimaryKeyWithDept(1);
		//System.out.println(emp.getEmpName());
		
		//����id��ѯ������Ϣ
		//Department dep = depMapper.selectByPrimaryKey(2);
		//System.out.println(dep.getDeptName());
		
		//��ѯ����Ա����Ϣ
		List<Employee> empList = empMapper.selectByExampleWithDept(new EmployeeExample());
		for(int i=0;i<3;i++){
			System.out.println(empList.get(i).getEmpName()+"\t"+empList.get(i).getDepartment().getDeptName());
		}
		
		//��ѯ���в�����Ϣ
		/*List<Department> depList = depMapper.selectByExample(new DepartmentExample());
		for(Department d:depList){
			System.out.println(d.getDeptId()+"\t"+d.getDeptName());	
		}*/
	}
}
