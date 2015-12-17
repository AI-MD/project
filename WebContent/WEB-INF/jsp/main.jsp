<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jl"%>
<html>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/cupertino/jquery-ui.css"/>
			<style>
				body{
					background-image:url('images/back1.png');
					background-size:100%; 
					background-position:inherit;
					background-repeat:no-repeat;
					margin: 0;
				}
			    .ui-dialog {
			         font-size: 0.625em;
			         width: 400px; height:200px;
			      }
				.ui-dialog-title{
					font-size:1.6em;
				}
    
			</style>
			
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>

<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
      <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/common.js"> </script>
<script type="text/javascript">
window.onload=function(){
	var l='<jl:out value="${error}"/>';
	if(l!=''){
		alert(l);
	}
	$(document).ready(function(){
	//	$("#reg").on("click",function(){
			
		//	location.href="./reg.do";
	//	});
		
		
		$("#dlg").dialog({
            modal:true,
            autoOpen:false,
            buttons:{
               확인:function(){
						$("#frm_imsi input[name='userId']").val($("#uid").val());
						$("#frm_imsi input[name='passwd']").val($("#pwd").val());
						
						$("#frm_imsi").submit();
                  		$("#dlg").dialog('close');
               },
           	 등록:function(){
            		location.href="./reg.do";
           	 }
            }
         });
		
		$("#btn").button().on("click",function(){
			$("#dlg").dialog('open');
		});
		
		
		
		
	})
	
	
	
};
</script>
<body >
<!-- -
	<div  style="background:#abc; margin-top: 300px;"  >
		
		<form method="POST" action="login.do">
			<p align="right" style="margin-right: 30px">
			UserId &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="userId" size="12"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
			PasssWrod <input type="password" name="passwd"size="12"/>
			
			
			<input type="submit" value="LOGIN" height="100%"/>
			<input type="button" value="회원가입" id="reg" />
			</p>
		</form>
		
	</div>
 -->
 <!-- style="background: url('images/back.png');" -->
 	<div class="container">
 		<div class="jumbotron" style="text-align: center;vertical-align:middle; height: 200px; background-color:green">
        	<h2>우리들의 세계</h2>
        	<input type="button" id="btn" value="로그인"/>
         </div>
         
 		<div class="jumbotron" style="height: 600px; text-align: center;n" >
        	<img src="images/aa.jpg" style="width: 70%; "/>
         </div>
    </div>     
     
 		
 			<div id="dlg" title="로그인">
				username <input type="text" id="uid" name="userId" placeholder="아이디를 입력하시오"/>
				<br/><br/>
				password <input type="password" id="pwd" name="passwd" placeholder="비밀번호를 입력하시오"/>
     		 </div>
			
			<div id="imsi" style="display:none;">
				<form id="frm_imsi" method="post" action="login.do">
					<input type="hidden" name="userId"/>
					<input type="hidden" name="passwd"/>
				</form>
			</div>
		
</body>
</html>