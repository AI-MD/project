<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<head>



	
	</head>
<body>
댓글수정
<form method="post" action="update2.do" >
	<table>
		<tr>
			<td>댓글번호</td>
			<td>${no} <input type="hidden" value="${no}" name="no"/><input type="hidden" value="${board_no}" name="board_no"/></td>	
		</tr>
		<tr>
			<td>내용</td>
			<td><input type="text" name="text" size="82"/></td>	
		</tr>
		
		<tr>
			<td rowspan="2"><input type="submit" value="전송" /></td>
		</tr>
	</table>
	</form>
	
	
</body>
</html>


