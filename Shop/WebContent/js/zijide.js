
	var log = console.log.bind(console)
	

	$("#modallogin").click(function(){
		$.post("loginServlet.do",{
	   	 	 		password:$("#password").val(),
	   	 	 		username:$("#username").val(),
	    		},
	    		function(data){
	    			if(data =="success"){
	    					log($("#username").val())
							$("#startlogin").text($("#username").val())　
	    		　　　　　　}else if(data == "fail"){
	    					alert("密码错误！")
	    		　　　　　　}else if(data == "notexit"){
	    					alert("用户不存在！")
	    		　　　　　　}
	    		});
 	 });

	