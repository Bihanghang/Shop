package shop.bean;

public class User {
	private String user_name;
	private String user_paswprd;
	private String user_sex;
	private String user_addr;
	private String user_phone;
	private String user_birth;
	private int user_jifen;
	private String user_touxiang;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_paswprd() {
		return user_paswprd;
	}
	public void setUser_paswprd(String user_paswprd) {
		this.user_paswprd = user_paswprd;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public int getUser_jifen() {
		return user_jifen;
	}
	public void setUser_jifen(int user_jifen) {
		this.user_jifen = user_jifen;
	}
	public String getUser_touxiang() {
		return user_touxiang;
	}
	public void setUser_touxiang(String user_touxiang) {
		this.user_touxiang = user_touxiang;
	}
	@Override
	public String toString() {
		return "User [user_name=" + user_name + ", user_paswprd=" + user_paswprd + ", user_sex=" + user_sex
				+ ", user_addr=" + user_addr + ", user_phone=" + user_phone + ", user_birth=" + user_birth
				+ ", user_jifen=" + user_jifen + ", user_touxiang=" + user_touxiang + "]";
	}
	
	
}
