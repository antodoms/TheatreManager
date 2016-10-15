package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Serializable{
	public String id;
	public String name;
	public String username;
	public String password;
	public String age;
	
	
	
	public User(String id, String name , String username, String password, String age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
	}
	
	public String getname(){
		return this.name;
	}
	
	public String getpassword(){
		return this.password;
	}
	
	public String getid(){
		return this.id;
	}
	
	public void setname(String name){
		this.name = name;
	}
	
	public void setpassword(String account){
		this.password = account;
	}
	public void setid(String id){
		this.id = id;
	}
	
	public boolean isLogin(String user, String pass){
		if(user == this.username && pass==this.password){
			return true;
		}else{
			return false;
		}
		
	}
}
