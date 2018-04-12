<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="UTF-8">
  <title>真知谷-聊天室</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!-- UM相关资源 -->
<link href="assets/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<header class="am-topbar am-topbar-fixed-top">
	  <div class="am-container">
	    <h1 class="am-topbar-brand">
	      <a href="#">聊天室</a>
	    </h1>
	    <div class="am-collapse am-topbar-collapse" id="collapse-head">
	      <ul class="am-nav am-nav-pills am-topbar-nav">
	        <li class="am-active"><a href="#">首页</a></li>
	        <li><a href="#">项目</a></li>
	      </ul>
	
	      <div class="am-topbar-right">
	        <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm"><span class="am-icon-pencil"></span> 注册</button>
	      </div>
	
	      <div class="am-topbar-right">
	        <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"><span class="am-icon-user"></span> 登录</button>
	      </div>
	    </div>
	  </div>
	</header>
	
	<div id="main">
		<!-- 聊天内容展示区域 -->
	<div id="ChatBox" class="am-g am-g-fixed" >
	  <div class="am-u-lg-12" style="height:400px;border:1px solid #999;overflow-y:scroll;">
		<ul id="chatContent" class="am-comments-list am-comments-list-flip">
			<!-- 遍历所有商品 -->
			<c:forEach items="${sessionScope.User_to客服List }" 
				var="i">
			<c:choose>
			<c:when test="${i.user == sessionScope.user_name}">
				<li id="msgtmp2" class="am-comment">
			    <a href="">
			        <img class="am-comment-avatar" src="assets/images/other.jpg" alt=""/>
			    </a>
			    <div class="am-comment-main" >
			        <header class="am-comment-hd">
			            <div class="am-comment-meta">
			              <a ff="nickname" href="#link-to-user" class="am-comment-author">${i.user }</a>
			              <time ff="msgdate" datetime="" title="">${i.to_date }</time>
			            </div>
			        </header>
			     <div ff="content" class="am-comment-bd">${i.to_mess }</div>
			    </div>
			</li>	
			</c:when>
			<c:otherwise>
		 	<li id="msgtmp2" class="am-comment am-comment-flip">
			    <a href="">
			        <img class="am-comment-avatar" src="assets/images/other.jpg" alt=""/>
			    </a>
			    <div class="am-comment-main" >
			        <header class="am-comment-hd">
			            <div class="am-comment-meta">
			              <a ff="nickname" href="#link-to-user" class="am-comment-author">${i.user }</a>
			              <time ff="msgdate" datetime="" title="">${i.to_date }</time>
			            </div>
			        </header>
			     <div ff="content" class="am-comment-bd">${i.to_mess }</div>
			    </div>
			</li>	
			</c:otherwise>
			</c:choose>
			</c:forEach>
			<li id="msgtmp" class="am-comment" style="display:none;">
			    <a href="">
			        <img class="am-comment-avatar" src="assets/images/other.jpg" alt=""/>
			    </a>
			    <div class="am-comment-main" >
			        <header class="am-comment-hd">
			            <div class="am-comment-meta">
			              <a ff="nickname" href="#link-to-user" class="am-comment-author">某人</a>
			              <time ff="msgdate" datetime="" title="">2014-7-12 15:30</time>
			            </div>
			        </header>
			     <div ff="content" class="am-comment-bd">此处是消息内容</div>
			    </div>
			</li>
		</ul>
	  </div>
	</div>
	<!-- 聊天内容发送区域 -->
	<div id="EditBox" class="am-g am-g-fixed">
	<!--style给定宽度可以影响编辑器的最终宽度-->
	<script type="text/plain" id="myEditor" style="width:100%;height:140px;"></script>
	<button id="send" type="button" class="am-btn am-btn-primary am-btn-block">发送</button>
	</div>
  
</div>
<script type="text/javascript">

$(function(){


	//实例化编辑器
    var um = UM.getEditor('myEditor',{
    	initialContent:"Hello,World!",
    	autoHeightEnabled:false,
    	toolbar:[
    		'| fontfamily fontsize' ,
            'link unlink | emotion image '
        ]
    });
    
    
    var nickname = "${sessionScope.user_name}";
	var socket = new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/chatserver/"+nickname);
    //接收服务器的消息
    socket.onmessage=function(ev){
    	var obj = eval(   '('+ev.data+')'   );
    	//分成user与客服user是为了区分消息的方向
    	if(obj.type == "user" || obj.type == "客服user"){
    		addMessage(obj);
    	} 
    	
    }
    
    $("#send").click(function(){
    	if (!um.hasContents()) {  // 判断消息输入框是否为空
            // 消息输入框获取焦点
            um.focus();
            // 添加抖动效果
            $('.edui-container').addClass('am-animation-shake');
            setTimeout("$('.edui-container').removeClass('am-animation-shake')", 1000);
        } else {
        	//获取输入框的内容
        	var txt = um.getContent();
        	//构建一个标准格式的JSON对象
        	var obj = JSON.stringify({
	    			type:"user",
        		nickname:nickname,
	    		content:txt
	    	});
            // 发送消息
            socket.send(obj);
            // 清空消息输入框
            um.setContent('');
            // 消息输入框获取焦点
            um.focus();
        }
    
    });
    
    
    
    
    
});

//人名nickname，时间date，是否自己isSelf，内容content
function addMessage(msg){

	var box = $("#msgtmp").clone(); 	//复制一份模板，取名为box
	box.show();							//设置box状态为显示
	box.appendTo("#chatContent");		//把box追加到聊天面板中
	box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
	box.find('[ff="msgdate"]').html(msg.date); 		//在box中设置时间
	box.find('[ff="content"]').html(msg.content); 	//在box中设置内容
	box.addClass(msg.nickname == "客服" ? 'am-comment-flip':'');	//右侧显示
	box.addClass(msg.nickname == "客服" ? 'am-comment-warning':'am-comment-success');//颜色
	box.css((msg.nickname == "客服" ? 'margin-left':'margin-right'),"40%");//外边距
	
	$("#ChatBox div:eq(0)").scrollTop(999999); 	//滚动条移动至最底部
	
}


</script>

</body>
</html>
