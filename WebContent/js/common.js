		function ajax_GET(url,fp){
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
							var rt=xhr.responseText;
							fp(rt);
					}else{
						alert(xhr.status);
					}
				}
			};
			xhr.open("GET",url,true);
			xhr.send(null);
		}