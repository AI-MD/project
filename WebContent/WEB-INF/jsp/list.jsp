<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
    
    
<html>
<head>

<head>
		<meta charset="utf-8"/>
		<meta name="viewport" 
			content="width=device-width, initial-scale=1"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
		<style>
		@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
		body{ font-family:"Helvetica Neue","Nanum Gothic";}
		</style>
		
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script >
window.onload=function(){
	
	
	$(document).ready(function(){
		$("#btn").on("click",function(){
			location.href="./logout.do";
		});
		$("#add").on("click",function(){
			location.href="./add.do";
		});
		$("#btn1").on("click",function(){
			location.href="./list.do";
		});
		$("#ab").on("click",function(){
			
		});
		
		$("#sear").on("click",function(){
						
			//$("#form").submit();
			//$("#sw").val() $("#fd option:selected").val()
			
		})
		
	});
};
</script>
	</head>
<body>
<div class="container">
	<h2>${UID}�� �α����Ͽ����ϴ�.</h2>
	<br/>
	
	<a class="btn btn-lg btn-primary" method="post" id="btn">�α���</a>
	<a class="btn btn-lg btn-primary" method="post" id="add">�۾���</a>
	
	
	<table  class="table table-condensed table-bordered table-hover">
		<tr bgcolor="#abcdef">
			<th>��ȣ</th>
			<th>���� (ip) (���ϸ�)</th>
			<th>�۾���</th>
			<th>�۾��ð�</th>
			<th>��ȸ��</th>
			<th>����</th>
			<th>����</th>
			<th>��õ</th>
			<th>��õ��</th>
		</tr>
		<jl:forEach var="t" items="${l}" varStatus="vs">
			<tr bgcolor="${vs.index%2!=0?'#aabbcc':'#ccbbaa'} ">
				<td>${t.no} </td>
				<td><a href="view.do?no=${t.no}&cp=${cp}">${t.title}</a> [${t.clientIp}] 
				<a href="file_down.do?no=${t.no}&ofn=${t.ofn}&fsn=${t.fsn}">${t.ofn}</a></td>
				<td>${t.userId}<img src="images/${t.photo}" width="20%"/></td>
				<td>${t.theTime}</td>
				<td>${t.count}</td>
				<td><jl:if test="${t.userId eq UID || UID eq '1234'}"><a href="board_delete.do?no=${t.no}&cp=${cp}">[����]</a></jl:if></td>
				<td><jl:if test="${t.userId eq UID || UID eq '1234' }"><a href="board_update.do?no=${t.no}&cp=${cp}">[����]</a></jl:if></td>
				<td><a href ="like.do?no=${t.no}&cp=${cp}">���ƿ�</a></td>
				<td>${t.recommand}</td>
			</tr>
		</jl:forEach>
	</table>
	
	<div style="text-align: center;">
 		 <ul class="pagination" >
 		<jl:if test="${cp != 1}">
 			<li><a href="list.do?cp=${cp-1}&field=${field}&searchword=${searchWord}">&laquo;</a></li>
		</jl:if>	
			<jl:forEach var="ts"  begin="1"  end="${np}">
					<li><a href="list.do?cp=${ts}&field=${field}&searchword=${searchWord}">${ts}</a></li>
			</jl:forEach>
		<jl:if test="${cp!=np}">
			<li><a href="list.do?cp=${cp+1}&field=${field}&searchword=${searchWord}">&raquo;</a></li>
		</jl:if>
		</ul>
	
		
	
	
	
		   	
		  <form method="post" action="list.do"  >
			<select name="field" >
					<option value="title">����</option>	
					<option value="text">����</option>
					<option value="user_Id">�۾���</option>
					<option value="reText">��۳���</option>
			</select>
			<input type="text" name="searchword" size="30"/>
			<input type="submit" value="�˻�" />
			
			<input type="button" value ="���ΰ�ħ"  id="btn1" />
		</form>
		</div>
	</div>
</body>
</html>


