package com.servlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.bean.TransData;

import net.sf.json.JSONObject;

@ServerEndpoint("/chatserver/{username}")
public class ChatServer {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	private static final Set<ChatServer> connections = new CopyOnWriteArraySet<>();
	

	private String nickname;
	private Session session;


	
	/**
	 * ÓÃ»§½ÓÈë
	 * @param session ¿ÉÑ¡
	 */
	@OnOpen
	public void onOpen(Session session){
		Map<String, String> map = session.getPathParameters();
		String username = map.get("username");
		this.nickname = username;
		this.session = session;
		connections.add(this);
		TransData data = GetOnUsers();
		data.setType("客服");
		JSONObject obj = JSONObject.fromObject(data);
		for (ChatServer client : connections) {
			if ("客服".equals(client.nickname)) 
				client.session.getAsyncRemote().sendText(obj.toString());
		}
		
	}
	
	/**
	 * ½ÓÊÕµ½À´×ÔÓÃ»§µÄÏûÏ¢
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message,Session session){

		JSONObject obj = JSONObject.fromObject(message);
		obj.put("date", df.format(new Date()));
		for (ChatServer client : connections) {
			obj.put("isSelf", client.session.equals(session));
			if (client.session.equals(session) || client.nickname.equals("客服")) {
				client.session.getAsyncRemote().sendText(obj.toString());
			}
		}
//		for(Session se : room){
//			obj.put("isSelf", se.equals(session));
//			se.getAsyncRemote().sendText(obj.toString());
//		}
	}
	
	/**
	 * ÓÃ»§¶Ï¿ª
	 * @param session
	 */
	@OnClose
	public void onClose(Session session){
		connections.remove(this);
		TransData data = new TransData();
		data.setType("offline");
		data.setNickname(this.nickname);
		JSONObject obj = JSONObject.fromObject(data);
		for (ChatServer client : connections) {
			if ("客服".equals(client.nickname)) 
				client.session.getAsyncRemote().sendText(obj.toString());
		}
	}
	
	/**
	 * ÓÃ»§Á¬½ÓÒì³£
	 * @param t
	 */
	@OnError
	public void onError(Throwable t){
		
	}
	
	public TransData GetOnUsers(){
		TransData transData = new TransData();
		List<String> list = new ArrayList<>();
		for (ChatServer client : connections) {
			if (!"客服".equals(client.nickname)) 
			list.add(client.nickname);
		}
		transData.setOnusers(list);
		return transData;
	}
}

