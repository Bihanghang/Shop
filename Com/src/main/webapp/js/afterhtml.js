
	var log = console.log.bind(console)
	
	$("#modallogin").click(function(){
		$.post("loginServlet.do",{
	   	 	 		password:$("#password").val(),
	   	 	 		username:$("#username").val(),
	    		},
	    		function(data){
	    			if(data =="success"){
	    				alert("nihao")
	    				window.location.href="http://127.0.0.1:8888/Chat/one.jsp"
	    		　　　　　　}
	    		});
 	 });
	
	$(".simpleCart_sum").text("$0.00")　

	
	