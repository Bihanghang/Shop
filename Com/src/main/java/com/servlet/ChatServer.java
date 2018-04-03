package com.servlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.Null;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.bean.PushMess;
import com.bean.TransData;
import com.dao.MessageDao;
import com.dao.UserDao;

import net.sf.json.JSONObject;

@ServerEndpoint("/chatserver/{username}")
public class ChatServer {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Set<ChatServer> connections = new CopyOnWriteArraySet<>();
	

	private String nickname; //设置客服名称可变，方便与不同客户间的对话
	private Session session; 
	private String KeFuName; //固定客服名称，方便传递信息


	
	/**
	 * ÓÃ»§½ÓÈë
	 * @param session ¿ÉÑ¡
	 */
	@OnOpen
	public void onOpen(Session session){
		//获取传递的参数
		Map<String, String> map = session.getPathParameters();
		String username = map.get("username");
		this.KeFuName = username;
		this.nickname = username;
		this.session = session;
		connections.add(this);
		TransToKefu();//向客服端发送在线人数以及客服端向所有人发送的信息
	}
	
	/**
	 * ½ÓÊÕµ½À´×ÔÓÃ»§µÄÏûÏ¢
	 * @param message
	 * @param session
	 */
	//获取客户端传过来的消息
	@OnMessage
	public void onMessage(String message,Session session){
		JSONObject obj = JSONObject.fromObject(message);
		//如果这个session名字是"客服"并且消息是从客服端发出
		if( "客服".equals(this.nickname) && "客服user".equals(obj.get("type"))){
			SaveAllMess(obj);//保存客服端向所有用户发送的信息
		} else if ("changename".equals(obj.get("type"))) {
			String nickname = (String) obj.get("nickname");
			if (!nickname.equals(this.nickname)) {
				this.nickname = nickname;
				SelectUserMess(obj,session);//查询点击用户的所有会话存入Json
			}
		}else {
			SaveMess(obj);
		}
		
	}
	
	/**
	 * ÓÃ»§¶Ï¿ª
	 * @param session
	 */
	@OnClose
	public void onClose(Session session){
		connections.remove(this);
		TransOff();//如果某个用户下线通知客服
	}
	
	/**
	 * ÓÃ»§Á¬½ÓÒì³£
	 * @param t
	 */
	@OnError
	public void onError(Throwable t){
		
	}
	
	
	//储存用户与客服的聊天记录
	public void SaveMess(JSONObject obj) {
		obj.put("date", df.format(new Date()));
		for (ChatServer client : connections) {
			obj.put("isSelf", client.session.equals(session));
			if (client.nickname.equals(this.nickname)) {
				if ("user".equals(obj.get("type")) && client.session.equals(this.session)) {
					System.out.println("userGet!");
					System.out.println(obj.toString());
					PushMess pushMess = new PushMess();
					pushMess.setUser(obj.getString("nickname"));
					pushMess.setTo_user("客服");
					pushMess.setSelf(obj.getBoolean("isSelf"));
					pushMess.setTo_date(obj.getString("date"));
					pushMess.setTo_mess(obj.getString("content")); 
					MessageDao dao = new MessageDao();
					dao.PushMessage(pushMess);
				}else if( "客服user".equals(obj.get("type")) && client.session.equals(this.session)){
					System.out.println("客服UserGet!");
					System.out.println(obj.toString());
					PushMess pushMess = new PushMess();
					pushMess.setUser(obj.getString("nickname"));
					pushMess.setTo_user(this.nickname);
					pushMess.setSelf(obj.getBoolean("isSelf"));
					pushMess.setTo_date(obj.getString("date"));
					pushMess.setTo_mess(obj.getString("content"));
					System.out.println(pushMess);
					MessageDao dao = new MessageDao();
					dao.PushMessage(pushMess);
				}
				client.session.getAsyncRemote().sendText(obj.toString());
			}
		}
	}
	//查询与点击用户的所有会话存入Json
	public void SelectUserMess(JSONObject obj,Session session) {
		MessageDao dao = new MessageDao();
		List<PushMess> list1 = new ArrayList<>();
		list1 = dao.GetUser(nickname);
		List<PushMess> list2 = new ArrayList<>();
		list2 = dao.GetTo_User(nickname);
		List<PushMess> User_to客服List = new ArrayList<>();
		User_to客服List.addAll(list1);
		User_to客服List.addAll(list2);
		Collections.sort(User_to客服List,new Comparator<PushMess>(){
            public int compare(PushMess arg0, PushMess arg1) {
                return arg0.getTo_date().compareTo(arg1.getTo_date());
            }
        });
		//返回选中用户的对话信息
		obj.put("usermessage", User_to客服List);
		session.getAsyncRemote().sendText(obj.toString());
	}
	//保存客服端向所有用户发送的信息
	public void SaveAllMess(JSONObject obj){
		obj.put("date", df.format(new Date()));
		obj.put("isSelf","true");
		UserDao Userdao = new UserDao();
		MessageDao Messdao = new MessageDao();
		List<String> AllUserNames = Userdao.GetAllUsersName();
		for(String name : AllUserNames){
			PushMess pushMess = new PushMess();
			pushMess.setUser("客服");
			pushMess.setTo_user(name);
			pushMess.setSelf(obj.getBoolean("isSelf"));
			pushMess.setTo_date(obj.getString("date"));
			pushMess.setTo_mess(obj.getString("content"));
			pushMess.setMesstype("ToAll");
			Messdao.PushMessage(pushMess);
		}
		for (ChatServer client : connections) {
			client.session.getAsyncRemote().sendText(obj.toString());
		}
	}
	
	//如果某个用户下线通知客服
	public void TransOff(){
		TransData data = new TransData();
		data.setType("offline");
		data.setNickname(this.nickname);
		JSONObject obj = JSONObject.fromObject(data);
		for (ChatServer client : connections) {
			if ("客服".equals(client.KeFuName)) 
				client.session.getAsyncRemote().sendText(obj.toString());
		}
	}
	//向客服端发送在线人数以及客服端向所有人发送的信息
	public void TransToKefu(){
		TransData data = GetOnUsers();
		System.out.println(data);
		data.setType("客服");
		JSONObject obj = JSONObject.fromObject(data);
		MessageDao dao = new MessageDao();
		obj.put("UserKefu", dao.GetUserKefu("客服","Tom","ToAll"));
		obj.put("客服Type", this.KeFuName);
		for (ChatServer client : connections) {
			if ("客服".equals(client.KeFuName)) {
				client.session.getAsyncRemote().sendText(obj.toString());
			}
		}
	}
	
	
	//返回所有在线用户，虽然客服端与用户端名字相同但是只改了名称，没有重启，所以不冲突
	public TransData GetOnUsers(){
		TransData transData = new TransData();
		List<String> list = new ArrayList<>();
		for (ChatServer client : connections) {
			if (!"客服".equals(client.nickname) && !"客服".equals(client.KeFuName)) 
			list.add(client.nickname);
		}
		transData.setOnusers(list);
		return transData;
	}
}

