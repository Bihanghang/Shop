var p = function(x,y){
		$.post("cartServlet",{
	 	 		itemId:x,
	 	 		Unit_Price:y,
		},
		function(data){
			if(data == "notlogin") {
				alert("请登陆！")
			}else{
				$(".simpleCart").text("$"+data)
			}
		});
	}

var Zero = function(){
	$.post("cartdelete",
		function(data){
			if(data == "success"){
				console.log("nihao")
				$(".simpleCart").text("$"+"0")
			}
	});
}