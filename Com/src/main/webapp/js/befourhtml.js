
	var log = console.log.bind(console)
	
	//利用ajax修改页面与 Websocket Session
 var ChangeSeesion = function(SessionName) {
	$.post("touser",{
	 		sessionname:SessionName,
	},
	function(data){
		console.log(data);
		var sessionname = "客服to"+data;
		change(sessionname);
	});
}


	
	