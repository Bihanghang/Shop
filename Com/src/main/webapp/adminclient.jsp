<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Amaze后台管理系统模板HTML首页 - cssmoban</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
  <!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="assets/js/app.js"></script>
  <!-- UM相关资源 -->
<link href="assets/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar admin-header ">
  <div class="am-topbar-brand">
    <strong>Amaze</strong> <small>后台管理模板HTML</small>
  </div>
  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
      <li><a href="admin-index.html"><span class="am-icon-home"></span> 首页</a></li>
       
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 在线用户 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <!-- <li><a href="admin-gallery.html"> 相册页面<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li> -->
        </ul>
      </li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-file"></span> 下线用户 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
          <c:forEach items="${sessionScope.allusers }" 
				var="i">
		  <li id="${i }"><a>${i }</a></li>
		  </c:forEach>
        </ul>
       </li>
      <li><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li>
    </ul>
	
    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>时光静好，与君语；细水流年，与君同。—— Amaze</p>
      </div>
    </div>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-tag"></span> wiki</p>
        <p>Welcome to the Amaze wiki!</p>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">

    <div id="ChatBox" class="am-g am-g-fixed" >
      <div class="am-u-lg-12" style="height:350px;border:1px solid #999;overflow-y:scroll;">
        <ul id="chatContent" class="am-comments-list am-comments-list-flip">
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
  </div>
  <!-- content end -->

</div>

<footer>
  <hr>
  <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<script type="text/javascript">
var socket;
$(function(){


	//实例化编辑器
    var um = UM.getEditor('myEditor',{
    	initialContent:"请输入聊天信息...",
    	autoHeightEnabled:false,
    	toolbar:[
    		'| fontfamily fontsize' ,
            'link unlink | emotion image '
        ]
    });

    var nickname = "${sessionScope.username}";
	socket = new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/chatserver/"+nickname);
 
    
    socket.onmessage=function(ev){
    	var obj = eval(   '('+ev.data+')'   );
    	if(obj.type == "normal"){
    		addMessage(obj);
    	} else if(obj.type == "客服") {
    		var a = []
    	     $('#collapse-nav li a').each(function(){
    	        a.push($(this).html())                                                     
    	    })
    		for (var i = 0; i<obj.onusers.length;i++){
    			if(inorin(obj.onusers[i],a) == false)
    			$("#collapse-nav").append("<li id='"+obj.onusers[i]+"' onclick='ChangeSession("+obj.onusers[i]+")'><a>" + obj.onusers[i] +"</a></li>")
    		}
    		var b = []
   	     $('#collapse-nav1 li a').each(function(){
   	        b.push($(this).html())
   	    })
   		for (var i = 0; i<obj.onusers.length;i++){
   			if(inorin(obj.onusers[i],b) == true)
   			 $("#collapse-nav1 li[id='"+obj.onusers[i]+"']").remove(); 
   		}
    	} else if(obj.type == "offline") {
    		$("#collapse-nav li[id='"+obj.nickname+"']").remove();
    		$("#collapse-nav1").append("<li id="+obj.nickname+" onclick='ChangeSession("+obj.nickname+")'><a>"+obj.nickname+"</a></li>")
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
        			type:"normal",
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
$("#collapse-nav1 li").click(function(){
	$("#collapse-nav1 li").css("background","");
	$("#collapse-nav li").css("background","");
	$(this).css("background","#00FFFF");
	var obj = JSON.stringify({
				type:"changename",
			nickname:this.id,
	});
	socket.send(obj);
});
function ChangeSession(s){
	$("#collapse-nav1 li").css("background","");
	$("#collapse-nav li").css("background","");
	$("#"+s.id).css("background","#00FFFF");
	var obj = JSON.stringify({
			type:"changename",
		nickname:s.id,
	});
	socket.send(obj);
}
//判断元素是否属于集合
function inorin(a,b) {
	for(var i = 0; i < b.length; i++)
		if ( a == b[i])
			return true
			return false
}
//人名nickname，时间date，是否自己isSelf，内容content
function addMessage(msg){

	var box = $("#msgtmp").clone(); 	//复制一份模板，取名为box
	box.show();							//设置box状态为显示
	box.appendTo("#chatContent");		//把box追加到聊天面板中
	box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
	box.find('[ff="msgdate"]').html(msg.date); 		//在box中设置时间
	box.find('[ff="content"]').html(msg.content); 	//在box中设置内容
	box.addClass(msg.isSelf? '':'am-comment-flip');	//右侧显示
	box.addClass(msg.isSelf? 'am-comment-warning':'am-comment-success');//颜色
	box.css((msg.isSelf? 'margin-right':'margin-left'),"40%");//外边距
	
	$("#ChatBox div:eq(0)").scrollTop(999999); 	//滚动条移动至最底部
	
}


$("#collapse-nav li").click(function(){
	
	$(this).css("background","#00FFFF");
	$(this).siblings().css("background","");
	var obj = JSON.stringify({
				type:"changename",
			nickname:this.id,
	});
	socket.send(obj);
});
</script>

</body>
</html>