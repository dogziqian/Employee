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
	//虚拟mvc请求，获取请求结果
	MockMvc mockMvc;
	
	
	@Before
	public void initMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception{
		//模拟请求拿到返回结果
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(new URI("/empList")).param("pn", "1")).andReturn();
		
		//请求成功后，请求域中会有pageinfo信息
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
		
		/*System.out.println("当前页面："+pi.getPageNum()+"\t"+"总页数"+pi.getPages());
		System.out.println("总记录数："+pi.getTotal()+"\t"+"页面大小："+pi.getPageSize());
		
		System.out.println("页面需要显示的连续的页码数：");
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
