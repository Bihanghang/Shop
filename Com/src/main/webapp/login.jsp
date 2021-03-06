<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>

    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
    <link rel="stylesheet" href="css/style.css">

    <style type="text/css">
        body{
    background: url("img/1.jpg");
    animation-name:myfirst;
    animation-duration:25s;
    /*变换时间*/
    animation-delay:2s;
    /*动画开始时间*/
    animation-iteration-count:infinite;
    /*下一周期循环播放*/
    animation-play-state:running;
    /*动画开始运行*/
    }
    @keyframes myfirst
    {
     0%   {background:url("img/1.jpg");}
      34%  {background:url("img/2.jpg");}
     67%  {background:url("img/3.jpg");}
      100% {background:url("img/1.jpg");}
    }
    .form{background: rgba(255,255,255,0.2);width:400px;margin:120px auto;}
    /*阴影*/
    .fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
    input[type="text"],input[type="password"]{padding-left:26px;}
    .checkbox{padding-left:21px;}
    </style>

</head>
<body>
    <div class="container">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" id="login_form">
                <h3 class="form-title">LOGIN</h3>
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="请输入手机号" id="phone" name="phone" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                            <i class="fa fa-lock fa-lg"></i>
                            <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" name="remember" value="1"/>记住我
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button class="btn btn-success pull-right" name="submit" id="login">登录</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>

$("#login").click(function(){
	$.post("loginServlet.do",{
   	 	 		password:$("#password").val(),
   	 	 		phone:$("#phone").val(),
    		},
    		function(data){
    			if(data =="success"){
    				 window.location.href = '/Com/indexServlet';
    		　　　　　　}else if(data == "fail"){
    					alert("密码错误！");
    		　　　　　　}else if(data == "notexit"){
    					alert("用户不存在！");
    		　　　　　     }else if(data == "kefu"){
    					 window.location.href = '/Com/kefuLogin';
    		　　　　　　}
    		});
	 });

</script>
</html>
