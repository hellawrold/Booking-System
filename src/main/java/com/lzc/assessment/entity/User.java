package com.lzc.assessment.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String userName;
	private String password;
	private String role;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
	private List<BookApplication> bkappls;

	public User() {
		
	}

	public User(String user_name, List<BookApplication> user_bkappls) {
		this.userName = user_name;
		this.bkappls = user_bkappls;
	}
	
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<String> getRole() {
		return Arrays.asList(role);
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public List<BookApplication> getBkappls() {
		return bkappls;
	}

	public void setBkappls(List<BookApplication> bkappls) {
		this.bkappls = bkappls;
	}

	@Override
	public String toString() {
		return "User [user_id=" + userid + ", user_name=" + userName + "]";
	}
}
