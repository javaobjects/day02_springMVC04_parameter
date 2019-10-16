package com.tencent.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tencent.pojo.Emp;
import com.tencent.service.IEmpService;

/**
 * 
* <p>Title: EmpController</p>  
* <p>
*	Description: 
*   控制器：处理器请求，响应结果
* </p> 
* @author xianxian 
* @date 2019年10月16日
 */
@Controller
@RequestMapping("/emp")//修饰类，访问路径 http://localhost:8088/day01_springMVC02_requestMapping/emp/get
public class EmpController {

	//@Autowired @Qualifier("empService") //默认根据类型匹配，通常结合@Qualifier指定引用名称使用
	@Resource(name = "empService")
	private IEmpService empService;
	
	
	/**
	 * 
	 * <p>Title: getEmpByempIndex</p>  
	 * <p>
	 *	Description: 
	 *  2.简单类型:
	 *  	1 支持整形、字符串、单精度/双单度、布尔型
	 *  
	 *  	2 建议所有的基本数据类型使用包装类，例如使用Integer、Float、Double、Boolean类型
	 *  
	 *  	3 形式参数的参数名称必须与实际参数(请求发送时的参数)的参数名称保持大小写一至
	 *  		<a href="${pageContext.request.contextPath}/emp/
	 *  		getEmpByempIndex.action?empIndex=${status.index}">编辑</a>
	 *  	  中的实际参数empIndex的名称属性与
	 *  		public String getEmpByempIndex(Model model,Integer empIndex) 
	 *  	  中的Integer empno的形式参数的名称保持大小写一致
	 * </p> 
	 * @param model
	 * @param empIndex
	 * @return
	 */
	@RequestMapping("/getEmpByempIndex")
	public String getEmpByempIndex(Model model,Integer empIndex) {
		System.out.println("修改用户的empIndex：" + empIndex);
		
		//1. 调用service方法获取当前Emp的信息
		Emp emp = empService.selectEmpByempIndex(empIndex);
		
		//2. 保存到作用域
		model.addAttribute("emp",emp);
		
		//3. 转发到修改页面
		return "empUpdate";
	}
	
	
	
	/**
	 * 
	 * <p>Title: getEmps</p>  
	 * <p>
	 *	Description: 
	 *  1. 返回ModelAndView
	 * </p> 
	 * @return
	 */
	@RequestMapping(value = "/get",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getEmps() {
		ModelAndView mav = new ModelAndView();
		
		//1. 调用service方法，查询所有雇员信息
		List<Emp> empList = empService.selectEmps();
		
		//2. 保存到作用域，相当于request.setAttribute("empList",empList);
		mav.addObject("empList",empList);
		
		//3. 指定跳转的路径，相当于request.getRequestDispatcher("/empQuery.jsp").forward(request,response);
		//mav.setViewName("/empQuery.jsp");
		mav.setViewName("empQuery");//前缀/+empQuery + 后缀.jsp = /empQuery.jsp
		
		return mav;
		
	}
	
	
	/**
	 * 
	 * <p>Title: getEmps3</p>  
	 * <p>
	 *	Description: 
	 *  2.返回String: 返回跳转页面的路径
	 * </p> 
	 * @param request
	 * @return
	 */
	@RequestMapping("/get2")
	public String getEmps2(HttpServletRequest request)
	{
		//1.调用service方法，查询所有雇员信息
		List<Emp> empList = empService.selectEmps();
		
		//2.保存到作用域，相当于request.setAttribute("empList", empList);
		request.setAttribute("empList", empList);
		
		//3.指定跳转的路径，相当于request.getRequestDispatcher("/empQuery.jsp").forward(request,response);
		return "empQuery";// 前缀 / + empQuery + 后缀.jsp = /empQuery.jsp
	}
	
	/**
	 * 
	 * <p>Title: getEmps3</p>  
	 * <p>
	 *	Description: 
	 *  2.1 返回String: 返回跳转页面的路径，使用转发forward:
	 *  
	 * </p> 
	 * @param request
	 * @return
	 */
	@RequestMapping("/get3")
	public String getEmps3(HttpServletRequest request)
	{
		//1.调用service方法，查询所有雇员信息
		List<Emp> empList = empService.selectEmps();
		
		//2.保存到作用域，相当于request.setAttribute("empList", empList);
		request.setAttribute("empList", empList);
		
		//3.指定跳转的路径，相当于request.getRequestDispatcher("/empQuery.jsp").forward(request,response);
		return "forward:/empQuery.jsp";// 不会拼接前后缀
	}
	
	
	/**
	 * 
	 * <p>Title: getEmps4</p>  
	 * <p>
	 *	Description: 
	 *  2.2 返回String: 返回跳转页面的路径，使用重定向redirect:
	 *  
	 * </p> 
	 * @param request
	 * @return
	 */
	@RequestMapping("/get4")
	public String getEmps4(HttpServletRequest request)
	{
		//1.调用service方法，查询所有雇员信息
		List<Emp> empList = empService.selectEmps();
		
		//2.保存到作用域，相当于session.setAttribute("empList", empList);
		request.getSession().setAttribute("empList", empList);
		
		//3.指定跳转的路径，相当于response.sendRedirect(request.getContextPath + "/empQuery.jsp");
		return "redirect:/empQuery.jsp";// 不会拼接前后缀
	}
	
	
	/**
	 * 
	 * <p>Title: getEmps5</p>  
	 * <p>
	 *	Description: 
	 *  3. 返回void: 无返回值
	 *  
	 * </p> 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/get5")
	public void getEmps5(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		//1.调用service方法，查询所有雇员信息
		List<Emp> empList = empService.selectEmps();
		
		//2.保存到作用域，相当于session.setAttribute("empList", empList);
		request.setAttribute("empList", empList);
		
		//3.指定跳转的路径，相当于request.getRequestDispatcher("/empQuery.jsp").forward(request,response);
		request.getRequestDispatcher("/empQuery.jsp").forward(request,response);
	}
	
	/**
	 * 
	 * <p>Title: updateEmp</p>  
	 * <p>
	 *	Description: 
	 *  3. 简单的pojo类型
	 *  	将表单域的name属性值与pojo对象的属性名称保持大小写一致，即:
	 *  	<input type="text" name="ename" value="${emp.ename}">中的name属性值ename
	 *  与public String updateEmp(Emp emp) 方法中的emp对象的setEname
	 *  后的ename名称保持大小写一致
	 *  
	 *  4. 复杂pojo类型:dept.deptno dept.dname
	 *  
	 *  5. 自定义参数：需要自定义转换器
	 * </p> 
	 * @param emp
	 * @return
	 */
	@RequestMapping("/updateEmp")
	public String updateEmp(Emp emp) {
		System.out.println("修改用户的信息:" + emp);
		return "success";
	}
}
