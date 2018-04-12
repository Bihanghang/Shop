<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Display</title>
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
<script type="text/javascript">
	$(function() {
	
	    var menu_ul = $('.menu_drop > li > ul'),
	           menu_a  = $('.menu_drop > li > a');
	    
	    menu_ul.hide();
	
	    menu_a.click(function(e) {
	        e.preventDefault();
	        if(!$(this).hasClass('active')) {
	            menu_a.removeClass('active');
	            menu_ul.filter(':visible').slideUp('normal');
	            $(this).addClass('active').next().stop(true,true).slideDown('normal');
	        } else {
	            $(this).removeClass('active');
	            $(this).next().stop(true,true).slideUp('normal');
	        }
	    });
	});
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
	
	var Verify = function(x,y){
			$.post("cartServlet",{
		 	 		itemId:x,
		 	 		Unit_Price:y,
			},
			function(data){
				if(data == "notlogin") {
					alert("请登陆！")
				}else{
					alert("添加成功")
				}
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
							<a href="login.jsp" id="startlogin">${user_phone == null ?'登录':user_name }</a>
						</div>
						<div class="box1" id="test">
							<a href="indexServlet"> 注册</a>						
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-6 top-header-left">
					<div class="cart box_1">
						<a href="checkoutServlet">
							 <div class="total">
								</div>
								<img src="images/cart-1.png" alt="" />
						</a>
						<p style="color:white">购物车</p>
						<div class="clearfix"> </div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--top-header-->
	
	<!--start-logo-->
	<div class="logo">
		<a href="indexServlet"><h1 id="huluwa">萨斯给</h1></a>
	</div>
	<!--start-logo-->
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
					<input id= "sea" type="text" value="请输入商品名" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入商品名';}">
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
					<li><a href="index.html">Home</a></li>
					<li class="active">Display</li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	<!--start-single-->
	<div class="single contact">
		<div class="container">
			<div class="single-main">
				<div class="col-md-9 single-main-left"  style="positon:relative;top:-27px;">
				<div class="sngl-top">
					
				</div>
				
					<div class="product-one">
						<div class="col-md-4 product-left p-left"> 
							<div class="product-main simpleCart_shelfItem">
								<a href="single.html" class="mask"><img class="img-responsive zoom-img" src="images/p-1.png" alt="" /></a>
								<div class="product-bottom">
									<h3>Smart Watches</h3>
									<p>Explore Now</p>
									<h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">$ 329</span></h4>
								</div>
								<div class="srch">
									<span>-50%</span>
								</div>
							</div>
						</div>
						<div class="col-md-4 product-left p-left"> 
							<div class="product-main simpleCart_shelfItem">
								<a href="single.html" class="mask"><img class="img-responsive zoom-img" src="images/p-2.png" alt="" /></a>
								<div class="product-bottom">
									<h3>Smart Watches</h3>
									<p>Explore Now</p>
									<h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">$ 329</span></h4>
								</div>
								<div class="srch">
									<span>-50%</span>
								</div>
							</div>
						</div>
						<!-- 遍历所有商品 -->
					<c:forEach items="${sessionScope.SearchResult }" 
							var="i">
						<div class="col-md-4 product-left p-left">
						<div class="product-main simpleCart_shelfItem">
							<a href="javascript:void(0)" class="mask" onclick="Single(${i.pro_id })"> <img class="img-responsive zoom-img" src="images/${i.pro_photo }.png" alt="" /></a>
							<div class="product-bottom">
								<h3>${i.pro_name }</h3>
								<p>${i.pro_describe }</p>
								<h4><a href="javascript:void(0)" class="item_add" onclick="Verify(${i.pro_id },${i.pro_price })"><i></i></a> <span class=" item_price">$ ${i.pro_price }</span></h4>
							</div>
							<div class="srch">
								<span>-${i.pro_discount*10 }%</span>
							</div>
						</div>
						</div>
					</c:forEach>
						<div class="col-md-4 product-left p-left"> 
							<div class="product-main simpleCart_shelfItem">
								<a href="single.html" class="mask"><img class="img-responsive zoom-img" src="images/p-3.png" alt="" /></a>
								<div class="product-bottom">
									<h3>Smart Watches</h3>
									<p>Explore Now</p>
									<h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">$ 329</span></h4>
								</div>
								<div class="srch">
									<span>-50%</span>
								</div>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
				<div class="col-md-3 single-right">
						<section  class="sky-form">
							<h4>Catogories</h4>
							<div class="row1 scroll-pane">
								<div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="checkbox" value="CatogoriesWatch"><i></i>Watch</label>
									<label class="checkbox"><input type="checkbox" name="checkbox" value="CatogoriesFaqia"><i></i>Faqia</label>
								</div>
							</div>
						</section>
						<section  class="sky-form">
							<h4>Size</h4>
							<div class="row1 row2 scroll-pane">
								<div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="checkbox" value="SizeLarge"><i></i>Large</label>
									<label class="checkbox"><input type="checkbox" name="checkbox" value="SizeMedium"><i></i>Medium</label>
									<label class="checkbox"><input type="checkbox" name="checkbox" value="SizeSmall"><i></i>Small</label>
								</div>
							</div>
						</section>
						<section class="sky-form">
							<h4>Colour</h4>
								<div class="row1 row2 scroll-pane">
								<div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="checkbox" value="ColorSilver"><i></i>Silver</label>
									<label class="checkbox"><input type="checkbox" name="checkbox" value="ColorBlack"><i></i>Black</label>
									<label class="checkbox"><input type="checkbox" name="checkbox" value="ColorDark Black"><i></i>Dark Black</label>
									<label class="checkbox"><input type="checkbox" name="checkbox" value="ColorRed"><i></i>Red</label>
								</div>
							</div>
						</section>
						<section class="sky-form">
							<h4>Discount</h4>
							<div class="row1 row2 scroll-pane">
								<div class="col col-4">
									<label class="radio"><input type="radio" name="radio" value="Discount6.0"><i></i>60 % and above</label>
									<label class="radio"><input type="radio" name="radio" value="Discount5.0"><i></i>50 % and above</label>
									<label class="radio"><input type="radio" name="radio" value="Discount4.0"><i></i>40 % and above</label>
									<label class="radio"><input type="radio" name="radio" value="Discount3.0"><i></i>30 % and above</label>
									<label class="radio"><input type="radio" name="radio" value="Discount2.0"><i></i>20 % and above</label>
									<label class="radio"><input type="radio" name="radio" value="Discount1.0"><i></i>10 % and above</label>
								</div>
							</div>						
						</section>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!--end-single-->
	
	<!--footer-starts-->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				
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