<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Checkout</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--jQuery(necessary for Bootstrap's JavaScript plugins)-->
<script src="js/jquery-1.11.0.min.js"></script>
<!--Custom-Theme-files-->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<style type="text/css">
		.num strong{
   		 padding: 0 20px;
		}
		.blue{background:rgb(242,45,0);}
		.red{background:red;}
		
</style>
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Luxury Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--start-menu-->
<script src="js/simpleCart.min.js"> </script>
<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/memenu.js"></script>
<script>$(document).ready(function(){$(".memenu").memenu();});</script>	
<!--dropdown-->
<script src="js/jquery.easydropdown.js"></script>		
<script>
var Zero = function(){
	$.post("cartdelete",
		function(data){
			if(data == "success"){
				$(".simpleCart").html('¥0.0')
				window.location.reload()
			}
	});
	var cart = $(".close2")
	for(var i = 0; i < cart.length; i++){
			var lang = "#cart";
			lang = lang + cart[i].id;
			$(lang).fadeOut('slow', function(c){
				$(lang).remove();
			});
		}
}

var plus = function(itemid){
	    console.log(itemid)
	    $.post("plusminus",{
	    	itemid:itemid,
	    	type:"plustype",
	    },
	    		function(data){
	    			if(data == "success"){
	    				window.location.reload()
	    			}
	    	});
}

	//点击减少按钮触发事件
var minus = function(itemid){
	$.post("plusminus",{
    	itemid:itemid,
    	type:"minustype",
    },
    		function(data){
    			if(data == "success"){
    				window.location.reload()
    			}
    	});
	}

var s = function(){
	console.log($("#sea").val())
	var GoodName = $("#sea").val();
	var Discount = [];
	var Catogories = [];
	var Color = [];
	var Size = [];
            $.each($('input:checkbox:checked'),function(){
            		if( $(this).val().indexOf("Catogories") != -1 )
            			Catogories.push( $(this).val() );
            		else if( $(this).val().indexOf("Color") != -1 )
            			Color.push( $(this).val() );
            		else if( $(this).val().indexOf("Size") != -1 )
            			Size.push( $(this).val() );
            });
            $.each($('input:radio:checked'),function(){
            	 Discount.push( $(this).val() );
            });
    console.log(Discount,Catogories,Color,Size)
    var Discount_json = JSON.stringify(Discount);
    var Catogories_json = JSON.stringify(Catogories);
    var Color_json = JSON.stringify(Color);
    var Size_json = JSON.stringify(Size);
    var Pro_json = JSON.stringify(GoodName);
    $.post("searchServlet",{
    	  Pro_name:Pro_json,
    	  Discount:Discount_json,
    	Catogories:Catogories_json,
    		 Color:Color_json,
    		  Size:Size_json,
},
function(data){
	console.log("nihao")
	console.log(data)
	window.location.href="display.jsp";
});
}

var Single = function(x){
	$.post("singleServlet",{
			pro_id:x,
	},
	function(data){
		console.log(data);
		window.location.href="single.jsp";
	});
}

$(document).ready(function() { 
	$("#js").hover(function() { 
	// $("#orderedlist li:last").hover(function() { 
		console.log("nihao")
	$(this).removeClass("red"); 
	$(this).addClass("blue"); 
	}, function() { 
	$(this).removeClass("blue"); 
	$(this).addClass("red"); 
	}); 
	}); 
</script>		

</head>
<body> 
	<!--top-header-->
	<div class="top-header">
		<div class="container">
			<div class="top-header-main">
				<div class="col-md-6 top-header-left">
					<div class="drop">
						<div class="box">
							<a href="login.jsp" id="startlogin"><span>${user_name }</span></a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-6 top-header-left">
					<div class="cart box_1">
						<a href="checkoutServlet">
							<div class="total">
								<span class="simpleCart">¥${sessionScope.CartTotal }</span></div>
								<img src="images/cart-1.png" alt="" />
						</a>
						<p><a href="javascript:void(0)" onclick="Zero()" class="simpleCart_e">Empty Cart</a></p>
						<div class="clearfix"> </div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--top-header-->
	<!--bottom-header-->
	<div class="header-bottom">
		<div class="container">
			<div class="header">
				<div class="col-md-9 header-left">
				<div class="top-nav">
					<ul class="memenu skyblue"><li class="active"><a href="indexServlet">Home</a></li>
						<li class="grid"><a href="#">分类</a>
							<div class="mepanel">
								<div class="row">
									<div class="col1 me-one">
										<h4>发卡</h4>
										<ul>
											<li><a href="products.html">New Arrivals</a></li>
											<li><a href="products.html">Blazers</a></li>
											<li><a href="products.html">Swem Wear</a></li>
											<li><a href="products.html">Accessories</a></li>
											<li><a href="products.html">Handbags</a></li>
											<li><a href="products.html">T-Shirts</a></li>
											<li><a href="products.html">Watches</a></li>
											<li><a href="products.html">My Shopping Bag</a></li>
										</ul>
									</div>
									<div class="col1 me-one">
										<h4>手表</h4>
										<ul>
											<li><a href="products.html">Shoes</a></li>
											<li><a href="products.html">Watches</a></li>
											<li><a href="products.html">Brands</a></li>
											<li><a href="products.html">Coats</a></li>
											<li><a href="products.html">Accessories</a></li>
											<li><a href="products.html">Trousers</a></li>
										</ul>	
									</div>
									
								</div>
							</div>
						<li class="grid"><a href="contact.html">Contact</a>
						</li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="col-md-3 header-right"> 
				<div class="search-bar">
					<input id= "sea" type="text" value="请输入商品名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入商品名';}">
					<input type="submit" value="" onclick='s()'>
				</div>
			</div>
			<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!--bottom-header-->
	<!--start-breadcrumbs-->
	<div class="breadcrumbs">
		<div class="container">
			<div class="breadcrumbs-main">
				<ol class="breadcrumb">
					<li><a href="indexServlet">Home</a></li>
					<li class="active">Checkout</li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--start-ckeckout-->
	<div class="ckeckout">
		<div class="container">
			<div class="ckeckout-top">
			<div class="cart-items">
			 <h3>My Shopping Bag (3)</h3>
				<script>/* $(document).ready(function(c) {
					$('.close2').on('click', function(c){
						$('.cart-header').fadeOut('slow', function(c){
							$('.cart-header').remove();
						});
						});	  
					});  */
					function g(itemid) {
						
						$.post("cartdelete",{
								itemid:itemid,
								},
								function(data){
									if(data == "success"){
										var cart = $(".close2")
										for(var i = 0; i < cart.length; i++){
											if(cart[i].id == itemid) {
												var lang = "#cart";
												lang = lang + itemid;
												$(lang).fadeOut('slow', function(c){
													$(lang).remove();
												});
											}
										}
										window.location.reload()
									}
							});
					}
			   </script>
			
				
			<div class="in-check" >
				<ul class="unit">
					<li><span>Item</span></li>
					<li><span>Product Name</span></li>		
					<li><span>Unit Price</span></li>
					<li><span>Number</span></li>
					<div class="clearfix"> </div>
				</ul>
				<c:forEach items="${sessionScope.cartplusnum }" 
							var="i">
						<ul class="cart-header" id="cart${i.product.pro_id }">
							<div class="close2" onclick='g(this.id)' id ="${i.product.pro_id }"> </div>
								<li class="ring-in"><a href="single.jsp" onclick="Single(${i.product.pro_id })"><img src="images/${i.product.pro_photo }.png" class="img-responsive" alt=""></a>
								</li>
								<li><span class="name">${i.product.pro_name }</span></li>
								<li><span class="cost">¥${i.product.pro_price*i.num }</span></li>
								<li><span><div class="num"><img onclick='minus(${i.product.pro_id })' class="minus" src="img/minus.png" /><strong id="strong">${i.num }</strong><img onclick='plus(${i.product.pro_id })' class="plus" src="img/plus.png"></div></span></li>
							<div class="clearfix"> </div>
						</ul>
				</c:forEach>
				
			</div>
			<div class="in-check" >
				<ul class="unit" style="border:none";>
					<li><span></span></li>
					<li><span></span></li>		
					<li style=" text-align:center;padding:10px;"><span>合计():¥<strong style="font-size:27px;">${sessionScope.CartTotal }</strong> </span></li>
					<li id="js" style="text-align:center;padding:10px;" class="red"><span><a href="javascript:void(0)" style="color:red;cursor:pointer;font-size:24px;color:white;text-decoration:none;">结算( )</a></span></li>
					<div class="clearfix"> </div>
				</ul>
			</div>
			</div>  
		 </div>
		</div>
	</div>
	<!--end-ckeckout-->

	<!--footer-starts-->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				<div class="col-md-6 footer-left">
					
				</div>
				<div class="col-md-6 footer-right">					
					<p>Copyright &copy; 2015.Company name All rights reserved.</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--footer-end-->	
</body>
</html>