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


	<h1>${title}</h1>
	<form action="${pageContext.request.contextPath}/emp/deleteEmps.action">
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
				<th>操作</th>
			</tr>
			<c:forEach items="${empList}" var="emp" varStatus="status">
				<tr height="35px">
					<td>
						${status.index+1}
						<input type="checkbox" name="empnos" value="${emp.empno}">
					</td>
					<td>${emp.empno}</td>
					<td>${emp.ename}</td>
					<td>${emp.job}</td>
					<td>${emp.mgr}</td>
					<td> <fmt:formatDate value="${emp.hiredate}" pattern="yyyy-MM-dd"/></td>
					<td>${emp.salary}</td>
					<td>${emp.comm}</td>
					<td>${emp.dept.dname}</td>
					<td>
						<a href="${pageContext.request.contextPath}/emp/getEmpByempIndex.action?empIndex=${status.index}">编辑</a>
						<a href="${pageContext.request.contextPath}/emp/getEmpByempIndex2.action?empIndex=${status.index}">编辑2</a>
						<a href="${pageContext.request.contextPath}/emp/deleteEmps.action?empnos=${emp.empno}">删除</a>
						<a href="${pageContext.request.contextPath}/emp/getSubEmpByempIndex.action?empIndex=${status.index}">查看下属员工</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br/><br/><br/>
		<input type="submit" value="批量删除">
	</form>
</body>
</html>