<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>【${emp.ename}】的下属员工列表</h1>
	
	<form action="${pageContext.request.contextPath}/emp/updateSubEmps.action">
		
		<table border="1px" width="80%" style="border-collapse: collapse;">
			<tr height="50px">
				<th>序号</th>
				<th>雇员编号</th>
				<th>雇员姓名</th>
				<th>职位</th>
				<th>上级经理</th>
				<th>入职日期</th>
				<th>薪水</th>
				<th>奖金</th>
				<th>部门</th>
			</tr>
			<!--
					subEmpList.get(0).getEname();
					subEmpList.get(0).setEname(ename);
			-->
			<c:forEach items="${subEmpList}" var="subEmp" varStatus="status">
				<tr height="35px">
					<td>${status.index+1}</td>
					<td>
						<input type="text" size="10" value="${subEmp.empno}" disabled="disabled">
						<input type="hidden" name="subEmpList[${status.index}].empno" value="${subEmp.empno}">
					</td>
					<td><input type="text" size="10" name="subEmpList[${status.index}].ename" value="${subEmp.ename}"></td>
					<td><input type="text" size="10" name="subEmpList[${status.index}].job" value="${subEmp.job}"></td>
					<td><input type="text" size="10" name="subEmpList[${status.index}].mgr" value="${subEmp.mgr}"></td>
					<td><input type="text" size="10" name="subEmpList[${status.index}].hiredate" value='<fmt:formatDate value="${subEmp.hiredate}" pattern="yyyy-MM-dd"/>'></td>
					<td><input type="text" size="10" name="subEmpList[${status.index}].salary" value="${subEmp.salary}"></td>
					<td><input type="text" size="10" name="subEmpList[${status.index}].comm" value="${subEmp.comm}"></td>
					<td>
						<select name="subEmpList[${status.index}].dept.deptno">
							<option value="10" ${subEmp.dept.deptno eq 10 ? "selected" : "" }>需求部</option>
							<option value="20" ${subEmp.dept.deptno eq 20 ? "selected" : "" }>前端部</option>
							<option value="30" ${subEmp.dept.deptno eq 30 ? "selected" : "" }>开发部</option>
							<option value="40" ${subEmp.dept.deptno eq 40 ? "selected" : "" }>测试部</option>
							<option value="50" ${subEmp.dept.deptno eq 50 ? "selected" : "" }>实施部</option>
						</select>
					</td>
				</tr>
			</c:forEach>
		</table><br/><br/>
		
		<input type="submit" value="批量修改">
	
	</form>
</body>
</html>