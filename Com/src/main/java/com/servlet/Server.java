/**
 *  ━━━━━━神兽出没━━━━━━ 
 *　　　┏┓　　　┏┓ 
 *　　┏┛┻━━━┛┻┓ 
 *　    ┃　　　　　　　┃
 *　	  ┃　　　━　　　┃ 
 *　    ┃　┳┛　┗┳    ┃ 
 *　　┃　　　　　　　┃ 
 *　　┃　　　┻　　　┃ 
 *　　┃　　　　　　　┃ 
 *　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting 
 *　　　　┃　　　┃    神兽保佑,代码无bug 
 *　　　　┃　　　┃ 
 *　　　　┃　　　┗━━━┓ 
 *　　　　┃　　　　　　　┣┓ 
 *　　　　┃　　　　　　　┏┛ 
 *　　　　┗┓┓┏━┳┓┏┛ 
 *　　　　　┃┫┫　┃┫┫ 
 *　　　　　┗┻┛　┗┻┛ 
 * 
 * ━━━━━━感觉萌萌哒━━━━━━ 
 */
package com.servlet;


import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 类名称:Server.java 类描述:简单的聊天室 作 者:wantao 时 间:2018年03月08日 23:11:48
 */
@ServerEndpoint(value = "/chat")
public class Server {
	private static final String GUEST_PREFIX = "用户";
	/**
	 * 一个提供原子操作的Integer的类。在Java语言中，++i和i++操作并不是线程安全的，
	 * 在使用的时候，不可避免的会用到synchronized关键字。 而AtomicInteger则通过一种线程安全的加减操作接口。
	 */
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	//存放用户的websocket
	private static final Set<Server> connections = new CopyOnWriteArraySet<>();


	private final String nickname;
	private Session session;


	public Server() {
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
	}


	/**
	 * 创建连接时间调用的方法
	 * 
	 * @param session
	 */
	@OnOpen
	public void start(Session session) {
		this.session = session;
		connections.add(this);
		String message = String.format("* %s %s", nickname, "加入聊天室");
		//上线通知
		broadcast(message);
		try {
			//系统问候语
			SendHello(this.nickname);
			//返回在线用户
			onlineList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 链接关闭时调用方法
	 */
	@OnClose
	public void end() {
		connections.remove(this);
		String message = String.format("* %s %s", nickname, "退出聊天室");
		broadcast(message);
	}
	/**
	 * 传输信息过程中调用方法
	 * @param message
	 */
	@OnMessage
	public void incoming(String message) {
		// Never trust the client
		// TODO: 过滤输入的内容
		String m = String.format("* %s %s", nickname, message);
		if(m.contains("to")){
			//点对点发送
			broadcastOneToOne(m,nickname);
		}else{
			//群发
			broadcast(m);
		}
	}
	/**
	 * 发生错误是调用方法
	 * @param t
	 * @throws Throwable
	 */
	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("错误: " + t.toString());
	}
	/**
	 * 消息广播
	 * 通过connections，对所有其他用户推送信息的方法
	 * @param msg
	 */
	private static void broadcast(String msg) {
		for (Server client : connections) {
			try {
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				System.out.println("错误:向客户端发送消息失败");
				connections.remove(client);
				try {
					client.session.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				String message = String.format("* %s %s", client.nickname,"退出聊天室");
				broadcast(message);
			}
		}
	}
	/**
	 * 点对点发送消息
	 * 通过connections，对所有其他用户推送信息的方法
	 * @param msg
	 */
	private static void broadcastOneToOne(String msg, String nickName) {
		String[] arr = msg.split("to");
		for (Server client : connections) {
			try {
				if(arr[1].equals(client.nickname) || nickName.equals(client.nickname)){
					synchronized (client) {
						client.session.getAsyncRemote().sendText(arr[0]);
					}
				}
			} catch (Exception e) {
				System.out.println("错误:向客户端发送消息失败");
				connections.remove(client);
				try {
					client.session.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				String message = String.format("* %s %s", client.nickname,"退出聊天室");
				broadcast(message);
			}
		}
	}
	//系统问候语
	private static void SendHello(String nickName) throws IOException{
		String m = String.format("* %s %s", nickName, "你好");
		for (Server client : connections) {
			if(client.nickname.equals(nickName)){
				client.session.getBasicRemote().sendText(m);
			}
		}
	}
	//在线用户
	private static void onlineList() throws IOException{
		String online = "";
		for (Server client : connections) {
			if(online.equals("")){
				online = client.nickname;
			}else{
				online += ","+client.nickname;
			}
		}
		String m = String.format("* %s %s", "当前在线用户", online);
		for (Server client : connections) {
			client.session.getBasicRemote().sendText(m);
		}
	}
}