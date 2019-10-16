<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/emp/updateEmp.action">
		雇员编号:<input type="text" name="empno" value="${emp.empno}"><br/><br/>
				 <input type="hidden" name="empno" value="${emp.empno}">
		雇员姓名:<input type="text" name="ename" value="${emp.ename}"><br/><br/>
		雇员职位:<input type="text" name="job" value="${emp.job}"><br/><br/>
		上级经理:<input type="text" name="mgr" value="${emp.mgr}"><br/><br/>
		入职日期:<input type="text" name="hiredate"  value='<fmt:formatDate value="${emp.hiredate}" pattern="yyyy-MM-dd"/>'><br/><br/>
		雇员薪水:<input type="text" name="salary" value="${emp.salary}"><br/><br/>
		雇员奖金:<input type="text" name="comm" value="${emp.comm}"><br/><br/>
		所在部门:
				<select name="dept.deptno">
					<option value="10" ${emp.dept.deptno eq 10 ? "selected" : "" }>需求部</option>
					<option value="20" ${emp.dept.deptno eq 20 ? "selected" : "" }>前端部</option>
					<option value="30" ${emp.dept.deptno eq 30 ? "selected" : "" }>开发部</option>
					<option value="40" ${emp.dept.deptno eq 40 ? "selected" : "" }>测试部</option>
					<option value="50" ${emp.dept.deptno eq 50 ? "selected" : "" }>实施部</option>
				</select><br/><br/>
				<input type="submit" value="提交修改">
	</form>
</body>
</html>