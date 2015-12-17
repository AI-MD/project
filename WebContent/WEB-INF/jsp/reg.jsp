
<%@page contentType="text/html; charset=utf-8"
		pageEncoding="euc-kr" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/common.js"> </script>
<script language='javascript'>

window.onload=function(){
	var d=false;
	
	$(document).ready(function(){
			$("#btn").on("click",function(){
				
				var url="doublecheck.do?id="+$("#userID").val();
				ajax_GET(url,function(l){
					
					if(l==1){
						alert('사용하실수 있습니다.');
					}else{
						alert('아이디가 중복되었습니다.');
					}
				});
				
				
			})
			
		
	})	
		
}


function formChk(){
    if(document.joinForm1.userId.value==''){
      alert("id를 입력하세요!!");
      document.joinForm1.userId.focus();
      return;
    }else if(document.joinForm1.passwd.value==''){
      alert("password를 입력하세요!!");    
      document.joinForm1.passwd.focus();
      return;
    }else if(document.joinForm1.passwd1.value!=document.joinForm1.passwd.value){
        alert("비밀번호가 일치하지 않습니다.!!");    
        document.joinForm1.passwd1.focus();
        return;
      }else if(document.joinForm1.userName.value==''){
        alert("이름을 입력하세요!!");    
        document.joinForm1.userName.focus();
        return;
      }else{
    	document.joinForm1.submit(); 
    	}
    }
</script>
</head>
<body>
<div class="container">
	<div style="width: 700px ;text-align: center;" >
	<div class="jumbotron">
         <h3>회원가입</h3>
         
      </div>
     
	<form method="POST" action="reg_add.do" id="joinForm1"  name="joinForm1" class="form-horizontal">
		 <div style="text-align: left;">
		<div class="form-group">
				<label for="userID">ID :</label>
				<input type="text" name="userId" id="userID"  placeholder="아이디를 입력하시오"/> <input type="button" id="btn" value="중복체크" />
		</div>
		<div class="form-group">
				<label for="password">암호 :</label>
				<input type="password" name="passwd" placeholder="비밀번호를 입력하시오" id="password"/>
				
		</div>
		<div class="form-group">
				<label for="password1">암호확인 :</label>
				<input type="password" name="passwd1"  placeholder="비밀번호를 입력하시오" id="password1"/>
		</div>
		<div class="form-group">
				<label for="username">이름 :</label>
				<input type="text" name="userName"id="userName" placeholder="이름을 입력하시오" />
		</div>
		
		<div class="form-group">
		<label for="pi">사진 :</label>
				<select name="photo" id="pi">
					<option value="apple.png">사과사진</option>	
					<option value="banana.png">바나나사진</option>
					<option value="orange.png">오렌지사진</option>
					<option value="kiwi.png">키위사진</option>
				</select>
		</div>
		
		</div>
		<div style="text-align: center">
		<input type="button" value="전송" onclick="formChk();"/>
		</div>
	</form>
	
	</div>
</div>

</body>
</html>



