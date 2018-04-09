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
</script>		
<style type="text/css">
		.num strong{
   		 padding: 0 20px;
		}
	</style>
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
					<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
					<input type="submit" value="">
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
						<ul class="cart-header" id="cart${i.product.itemid }">
							<div class="close2" onclick='g(this.id)' id ="${i.product.itemid }"> </div>
								<li class="ring-in"><a href="single.html" ><img src="images/${i.product.item }.png" class="img-responsive" alt=""></a>
								</li>
								<li><span class="name">${i.product.product_name }</span></li>
								<li><span class="cost">¥${i.product.unit_price*i.num }</span></li>
								<li><span><div class="num"><img onclick='minus(${i.product.itemid })' class="minus" src="img/minus.png" /><strong id="strong">${i.num }</strong><img onclick='plus(${i.product.itemid })' class="plus" src="img/plus.png"></div></span></li>
							<div class="clearfix"> </div>
						</ul>
				</c:forEach>
				
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