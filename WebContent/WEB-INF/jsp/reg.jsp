
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
						alert('����ϽǼ� �ֽ��ϴ�.');
					}else{
						alert('���̵� �ߺ��Ǿ����ϴ�.');
					}
				});
				
				
			})
			
		
	})	
		
}


function formChk(){
    if(document.joinForm1.userId.value==''){
      alert("id�� �Է��ϼ���!!");
      document.joinForm1.userId.focus();
      return;
    }else if(document.joinForm1.passwd.value==''){
      alert("password�� �Է��ϼ���!!");    
      document.joinForm1.passwd.focus();
      return;
    }else if(document.joinForm1.passwd1.value!=document.joinForm1.passwd.value){
        alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.!!");    
        document.joinForm1.passwd1.focus();
        return;
      }else if(document.joinForm1.userName.value==''){
        alert("�̸��� �Է��ϼ���!!");    
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
         <h3>ȸ������</h3>
         
      </div>
     
	<form method="POST" action="reg_add.do" id="joinForm1"  name="joinForm1" class="form-horizontal">
		 <div style="text-align: left;">
		<div class="form-group">
				<label for="userID">ID :</label>
				<input type="text" name="userId" id="userID"  placeholder="���̵� �Է��Ͻÿ�"/> <input type="button" id="btn" value="�ߺ�üũ" />
		</div>
		<div class="form-group">
				<label for="password">��ȣ :</label>
				<input type="password" name="passwd" placeholder="��й�ȣ�� �Է��Ͻÿ�" id="password"/>
				
		</div>
		<div class="form-group">
				<label for="password1">��ȣȮ�� :</label>
				<input type="password" name="passwd1"  placeholder="��й�ȣ�� �Է��Ͻÿ�" id="password1"/>
		</div>
		<div class="form-group">
				<label for="username">�̸� :</label>
				<input type="text" name="userName"id="userName" placeholder="�̸��� �Է��Ͻÿ�" />
		</div>
		
		<div class="form-group">
		<label for="pi">���� :</label>
				<select name="photo" id="pi">
					<option value="apple.png">�������</option>	
					<option value="banana.png">�ٳ�������</option>
					<option value="orange.png">����������</option>
					<option value="kiwi.png">Ű������</option>
				</select>
		</div>
		
		</div>
		<div style="text-align: center">
		<input type="button" value="����" onclick="formChk();"/>
		</div>
	</form>
	
	</div>
</div>

</body>
</html>



