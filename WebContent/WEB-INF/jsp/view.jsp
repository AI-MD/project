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
	             if(confirm("정말 삭제하시겠습니까??")){
	            	
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
	<input type="button" value="로그아웃"  onclick="javascript:location.href('./logout.do')"/>
	<input type="button" value="목록보기"  id="btn"/>
	<table >
			<tr>
				<td>번호</td><td>${bvo.no}</td><td>${bvo.title}</td>
			</tr>
			<tr>
				<td>내용</td><td colspan="2">${bvo.text}</td>
			</tr>	
			<tr>
				<td>아이디 </td>
				<td>${bvo.userId}</td>
				<td> [${bvo.clientIp}]  ${bvo.theTime}</td>
				
			</tr>
			<tr>
				<td>첨부파일 </td>
				<td> <a href="file_down.do?no=${bvo.no}&ofn=${bvo.ofn}&fsn=${bvo.fsn}">${bvo.ofn}</a> </td> 
				<td> <jl:if test="${bvo.userId eq id || UID eq '1234' }">글<a href="board_delete.do?no=${bvo.no}">[삭제]</a></jl:if></td> 
			</tr>
	</table>
	<br />
	댓글 
	<table >
		<tr >
			<th>번호</th>
			<th>글쓴이</th>
			<th>내용</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>
		<jl:forEach var="t" items="${rls}" varStatus="vs">
			<tr >
				<td>${t.no}</td>
				<td>${t.userId}</td>
				<td>${t.text}</td>
				<td><jl:if test="${t.userId eq id || UID eq '1234' }"><a href="#"  id="${t.no}" class="a">  [삭제] </a></jl:if></td>
				<td><jl:if test="${t.userId eq id || UID eq '1234' }"><a href="reply_update.do?no=${t.no}&board_no=${bvo.no}">[수정]</a></jl:if></td>
			</tr>
		</jl:forEach>
	</table>
	
	<form method="POST" action ="reply_insert.do">
	<input type="text" name="text"size="50"/>
	<input type="hidden" name="userID" value="${uid}"/>
	<input type="hidden" name="board_no" value="${bvo.no}" />
	<input type="submit" value="입력">	
	</form>
	
	<form id="abcd" action="reply_delete.do" method="POST">
   <input type="hidden" name="board_no" value="" id="board_no"/>
   <input type="hidden" name="no" value="" id="no"/>
   </form>
	
</body>
</html>


