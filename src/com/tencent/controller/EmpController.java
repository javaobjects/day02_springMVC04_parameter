package com.tencent.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * <p>Title: getEmps</p>  
	 * <p>
	 *	Description: 
	 *  1.默认支持五种类型：HttpServletRequest、HttpServletResponse、HttpSession、Model、ModelMap
	 * </p> 
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/get")
	public String getEmps(HttpServletRequest request,HttpServletResponse response,HttpSession session, Model model, ModelMap modelMap)
	{
		//request
		String ename = request.getParameter("ename");
		System.out.println("request: " + ename);
		
		//response
		System.out.println("response: 国家：" + response.getLocale().getCountry() + "_" + response.getLocale().getLanguage());
		
		//session
		System.out.println("session:" + session.getId());
		
		//1.调用service方法，查询所有雇员信息
		List<Emp> empList = empService.selectEmps();
		
		//2.保存到作用域，相当于request.setAttribute("empList", empList);
		model.addAttribute("empList", empList);
		
		modelMap.addAttribute("title", "雇员信息列表");
		
		//3.指定跳转的路径，相当于request.getRequestDispatcher("/empQuery.jsp").forward(request,response);
		return "empQuery";// 前缀 / + empQuery + 后缀.jsp = /empQuery.jsp
	}
	
	
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
	 * 2.1 简单类型：
	 * 		① 支持整型、字符串、单精度/双精度、布尔型
	 * 
	 * 		② 建议所有的基本数据类型使用包装类，例如使用Integer、Float、Double、Boolean类型，允许接收null值
	 * 
	 * 		③ 形式参数的参数名称必须与实际参数（请求发送时的参数）的参数名称保持大小写一致
	 * 
	 * 			<a href="${pageContext.request.contextPath}/emp/getEmpByEmpno.action?empno=${status.index}">编辑</a>中的实际参数empno的名称 
	 * 
	 *           必须与
	 *           
	 *          public String getEmpByEmpno(Model model,Integer empno)中的Integer empno的形式参数的名称保持大小写
	 *          
	 *          一致
	 *          
	 *       ④ 当形式参数与实际参数（请求参数名）名称不一致时，可使用@RequestParam统一参数信息：
	 *       	value：表示实际参数名称
	 *          defaultValue:参数为空时的默认值
	 *          required：表示参数是否必带
	 */
	@RequestMapping("/getEmpByEmpno2")
	public String getEmpByempIndex2(Model model,@RequestParam(value="index",defaultValue="0",required=true)Integer empIndex)
	{
		
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
	 * <p>Title: updateEmp</p>  
	 * <p>
	 *	Description: 
	 * 3. 简单pojo类型：
	 * 		将表单域的name属性值与pojo对象的属性名称保持大小写一致，即：
	 * 		    <input type="text" name="ename" value="${emp.ename}">中的name属性值ename
	 * 			与
	 * 			public String updateEmp(HttpServletRequest request,Emp emp)方法中emp对象的setEname后的ename名称保持
	 * 			大小写一致
	 * 
	 * 4. 复杂（包装）pojo类型：dept.deptno  dept.dname
	 * 
	 * 5. 自定义参数：需自定义转换器
	 * </p> 
	 * @param request
	 * @param emp
	 * @return
	 */
	@RequestMapping("/updateEmp")
	public String updateEmp(HttpServletRequest request,Emp emp)
	{
		System.out.println("修改用户的信息：" + emp);
		
		return "success";
	}
	
	/**
	 * 
	 * <p>Title: deleteEmps</p>  
	 * <p>
	 *	Description: 
	 *  6.数组类型
	 *  	1. 实际参数的名称与形式参数名称保持大小写一致
	 *  	<input type="checkbox" name="empnos" value="${emp.empno}">中的name属性值empnos
	 *  与
	 *  public String deleteEmps(Integer[] empnos) 中的empnos保持大小写一致
	 * </p> 
	 * @param empnos
	 * @return
	 */
	@RequestMapping("/deleteEmps")
	public String deleteEmps(Integer[] empnos) {
		System.out.println("删除用户的empno:" + Arrays.toString(empnos));
		
		return "success";
	}
	
	/**
	 * 
	 * <p>Title: getSubEmpByEmpno</p>  
	 * <p>
	 *	Description: 
	 *  7. List集合
	 * </p> 
	 * @param model
	 * @param empno
	 * @return
	 */
	@RequestMapping("/getSubEmpByempIndex")
	public String getSubEmpByempIndex(Model model,Integer empIndex) {
		System.out.println("查看当前用户的empIndex:" + empIndex);
		
		//当前员工的信息
		Emp emp = empService.selectEmpByempIndex(empIndex);
		System.out.println("查看当前用户的empno： " + empIndex + "得到的员工信息:" + emp);
		
		
		//当前员工的下属信息
		List<Emp> subEmpList = emp.getSubEmpList();
		
		//保存数据
		model.addAttribute("emp",emp);
		model.addAttribute("subEmpList",subEmpList);
		
		return "subEmpQuery";
	}
	@RequestMapping("/updateSubEmps")
	public String updateSubEmps(Emp emp) {
		List<Emp> subEmpList = emp.getSubEmpList();
		for(Emp subEmp : subEmpList) {
			System.out.println(subEmp);
		}
		return "success";
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
	

}
