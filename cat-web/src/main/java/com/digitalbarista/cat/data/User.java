package com.digitalbarista.cat.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("User")
@Entity
@Table(name="users")
@Proxy(lazy=false)
public class User {

	public enum Authority
	{
		ROLE_ADMIN,
		ROLE_USER,
		ROLE_API_USER;
	}
	
	@Id
	@NotNull @Size(max=50)
	@Column(name="username")
	private String username;
	
	@NotNull @Size(max=50)
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@ElementCollection
	@CollectionTable(name="authorities", joinColumns=@JoinColumn(name="username"))
	@Column(name="authority")
	private Set<String> authorities=new HashSet<String>();
	
	private User(){}
	
	public User(String username,String encodedPassword)
	{
		this.username=username;
		this.password=encodedPassword;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setEncryptedPassword(String encryptedPassword)
	{
		this.password = encryptedPassword;
	}
	
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}
} 
