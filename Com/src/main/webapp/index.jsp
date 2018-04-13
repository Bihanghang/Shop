<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="/Com/"> 
<title>Home</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--jQuery(necessary for Bootstrap's JavaScript plugins)-->
<script src="js/jquery-1.11.0.min.js"></script>
<!--Custom-Theme-files-->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<link rel="stylesheet" href="css/styleCe.css" type="text/css" />
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


$(document).ready(function(){



	$(".side ul li").hover(function(){

		$(this).find(".sidebox").stop().animate({"width":"124px"},200).css({"opacity":"1","filter":"Alpha(opacity=100)","background":"#ae1c1c"})	

	},function(){

		$(this).find(".sidebox").stop().animate({"width":"54px"},200).css({"opacity":"0.8","filter":"Alpha(opacity=80)","background":"#000"})	

	});

	setInterval(function(){
		$.post("checkoffline",
				function(data){
					if(data == "noMess"){
						console.log("没有消息")
					}else{
						console.log("data",data);
						$("#kefuimg").attr("src","img/side_icon05.png");
						$("#kefuNum").text(data+"条未读");
					}
				});
		},3000)

	});



//回到顶部

function goTop(){

	$('html,body').animate({'scrollTop':0},600);

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


var Client = function(){
	$.post("clientServlet",
function(data){
	if(data == "logined"){
		window.location.href="chatclient.jsp";
	}else{
		alert("请登陆");
	}
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
	<!--banner-starts-->
	<div class="bnr" id="home">
		<div  id="top" class="callbacks_container">
			<ul class="rslides" id="slider4">
			    <li>
					<div class="banner">
					</div>
				</li>
				<li>
					<div class="banner1">
					</div>
				</li>
				<li>
					<div class="banner2">
					</div>
				</li>
			</ul>
		</div>
		<div class="clearfix"> </div>
	</div>
	<!--banner-ends--> 
    
	<!--Slider-Starts-Here-->
				<script src="js/responsiveslides.min.js"></script>
			 <script>
			    // You can also use "$(window).load(function() {"
			    $(function () {
			      // Slideshow 4
			      $("#slider4").responsiveSlides({
			        auto: true,
			        pager: true,
			        nav: true,
			        speed: 500,
			        namespace: "callbacks",
			        before: function () {
			          $('.events').append("<li>before event fired.</li>");
			        },
			        after: function () {
			          $('.events').append("<li>after event fired.</li>");
			        }
			      });
			
			    });
			  </script>
			<!--End-slider-script-->
	<!--about-starts-->
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >手机网站模板</a></div>
	<div class="about"> 
		<div class="container">
			<div class="about-top grid-1">
				<div class="col-md-4 about-left">
					<figure class="effect-bubba">
						<img class="img-responsive" src="images/abt-1.jpg" alt=""/>
						<figcaption>
							<h2>阿丽亚陶</h2>
							<p>是大陆军方</p>	
						</figcaption>			
					</figure>
				</div>
				<div class="col-md-4 about-left">
					<figure class="effect-bubba">
						<img class="img-responsive" src="images/abt-2.jpg" alt=""/>
						<figcaption>
							<h4>Mauris erat augue</h4>
							<p>In sit amet sapien eros Integer dolore magna aliqua</p>	
						</figcaption>			
					</figure>
				</div>
				<div class="col-md-4 about-left">
					<figure class="effect-bubba">
						<img class="img-responsive" src="images/abt-3.jpg" alt=""/>
						<figcaption>
							<h4>Cras elit mauris</h4>
							<p>In sit amet sapien eros Integer dolore magna aliqua</p>	
						</figcaption>			
					</figure>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--about-end-->
	<!--product-starts-->
	<div class="product"> 
		<div class="container">
			<div class="product-top">
				<div class="product-one">
					<!-- 遍历所有商品 -->
					<c:forEach items="${sessionScope.products }" 
							var="i">
						<div class="col-md-3 product-left">
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
					<div class="clearfix"></div>
				</div>					
			</div>
		</div>
	</div>
	<!--product-end-->
	
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
	<div class="side">

	<ul>

		<li onclick='Client()'><a href="javascript:void(0);" ><div class="sidebox"><img id="kefuimg" src="img/side_icon04.png"><span id="kefuNum">客服</span></div></a></li>

		<li><a href="checkoutServlet" ><div class="sidebox"><img src="img/cc.jpg">&nbsp;&nbsp;&nbsp;&nbsp;购物车</div></a></li>

		<li><a href="javascript:goTop();" class="sidetop"><img src="img/side_icon05.png"></a></li>

	</ul>

</div>
</body>
</html>


