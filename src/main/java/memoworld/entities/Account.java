package memoworld.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {
	@XmlElement(name = "db_id")
		private int db_id;
	@XmlElement(name = "user_id")
		private String user_id;
	@XmlElement(name = "name")
		private String name;
	@XmlElement(name = "password")
		private String password;
	
	public Account() {
	}
	public Account(String password) {
		this.password = password;
	}
	public Account(String password, String name, String user_id) {
		this.password = password;
		this.name = name;
		this.user_id = user_id;
	}
	public Account(int db_id, String password, String name, String user_id) {
		this.db_id = db_id;
		this.password = password;
		this.name = name;
		this.user_id = user_id;
	}
	//db_idの取得とセット
	public int getDb_id() {
		return db_id;
	}
	public void setDb_id(int db_id) {
		this.db_id = db_id;
	}
	//user_idの取得とセット
	public String getUser_id() {
			return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	//名前の取得とセット
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//パスワードの取得とセット
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}