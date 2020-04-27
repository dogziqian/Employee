package com.syq.crud.test;

import java.net.URI;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.syq.crud.bean.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	
	@Autowired
	WebApplicationContext context;
	//����mvc���󣬻�ȡ������
	MockMvc mockMvc;
	
	
	@Before
	public void initMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception{
		//ģ�������õ����ؽ��
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(new URI("/empList")).param("pn", "1")).andReturn();
		
		//����ɹ����������л���pageinfo��Ϣ
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
		
		/*System.out.println("��ǰҳ�棺"+pi.getPageNum()+"\t"+"��ҳ��"+pi.getPages());
		System.out.println("�ܼ�¼����"+pi.getTotal()+"\t"+"ҳ���С��"+pi.getPageSize());
		
		System.out.println("ҳ����Ҫ��ʾ��������ҳ������");
		int[] nums = pi.getNavigatepageNums();
		for(int i:nums){
			System.out.print(" "+i);
		}*/
		List<Employee> list = pi.getList();
		//System.out.println(list.get(1).getEmpName()+"\t"+list.get(1).getDepartment().getDeptName());
		for(Employee emp:list){
			System.out.println("name:"+emp.getEmpName()+"\t"+"depName:"+emp.getDepartment().getDeptName());
		}
		
	}
}
