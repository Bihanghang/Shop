total = 0	
var p = function(x,y){
		$.post("cartServlet",{
	 	 		product_id:x,
	 	 		product_price:y,
		},
		function(data){
			if(data == "notlogin") {
				alert("请登陆！")
			}else{
				total = total + y
				$(".simpleCart_sum").text("$"+total.toString())　
			}
		});
	}

var Zero = function(){
	$(".simpleCart_sum").text("$"+"0.00")
}