package com.bean;

import java.util.List;

public class TransData {
	private String type;
	private List<String> onusers;
	private List<PushMess> kefumess;
	private String nickname;   //返回的下线用户
	
	public TransData() {
		
	}

	public TransData(String type, List<String> onusers, List<PushMess> kefumess, String nickname) {
		super();
		this.type = type;
		this.onusers = onusers;
		this.kefumess = kefumess;
		this.nickname = nickname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getOnusers() {
		return onusers;
	}

	public void setOnusers(List<String> onusers) {
		this.onusers = onusers;
	}

	public List<PushMess> getKefumess() {
		return kefumess;
	}

	public void setKefumess(List<PushMess> kefumess) {
		this.kefumess = kefumess;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "TransData [type=" + type + ", onusers=" + onusers + ", kefumess=" + kefumess + ", nickname=" + nickname
				+ "]";
	}
}
