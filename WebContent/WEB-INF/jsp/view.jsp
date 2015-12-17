<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>

<html>
<head>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script>
window.onload=function(){
		$(document).ready(function(){
			$(".a").click(function(){
	             if(confirm("���� �����Ͻðڽ��ϱ�??")){
	            	
	                $("#board_no").val('${bvo.no}');
	                $("#no").val(this.id);
	                $("#abcd").submit();
	            }
	         });
			
			$("#btn").on("click",function(){
				location.href="./list.do?cp=${cp}";
			})
		})
	}
</script>

	
	</head>
<body>
	<input type="button" value="�α׾ƿ�"  onclick="javascript:location.href('./logout.do')"/>
	<input type="button" value="��Ϻ���"  id="btn"/>
	<table >
			<tr>
				<td>��ȣ</td><td>${bvo.no}</td><td>${bvo.title}</td>
			</tr>
			<tr>
				<td>����</td><td colspan="2">${bvo.text}</td>
			</tr>	
			<tr>
				<td>���̵� </td>
				<td>${bvo.userId}</td>
				<td> [${bvo.clientIp}]  ${bvo.theTime}</td>
				
			</tr>
			<tr>
				<td>÷������ </td>
				<td> <a href="file_down.do?no=${bvo.no}&ofn=${bvo.ofn}&fsn=${bvo.fsn}">${bvo.ofn}</a> </td> 
				<td> <jl:if test="${bvo.userId eq id || UID eq '1234' }">��<a href="board_delete.do?no=${bvo.no}">[����]</a></jl:if></td> 
			</tr>
	</table>
	<br />
	��� 
	<table >
		<tr >
			<th>��ȣ</th>
			<th>�۾���</th>
			<th>����</th>
			<th>����</th>
			<th>����</th>
		</tr>
		<jl:forEach var="t" items="${rls}" varStatus="vs">
			<tr >
				<td>${t.no}</td>
				<td>${t.userId}</td>
				<td>${t.text}</td>
				<td><jl:if test="${t.userId eq id || UID eq '1234' }"><a href="#"  id="${t.no}" class="a">  [����] </a></jl:if></td>
				<td><jl:if test="${t.userId eq id || UID eq '1234' }"><a href="reply_update.do?no=${t.no}&board_no=${bvo.no}">[����]</a></jl:if></td>
			</tr>
		</jl:forEach>
	</table>
	
	<form method="POST" action ="reply_insert.do">
	<input type="text" name="text"size="50"/>
	<input type="hidden" name="userID" value="${uid}"/>
	<input type="hidden" name="board_no" value="${bvo.no}" />
	<input type="submit" value="�Է�">	
	</form>
	
	<form id="abcd" action="reply_delete.do" method="POST">
   <input type="hidden" name="board_no" value="" id="board_no"/>
   <input type="hidden" name="no" value="" id="no"/>
   </form>
	
</body>
</html>


