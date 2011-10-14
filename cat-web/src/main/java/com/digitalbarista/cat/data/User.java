package com.digitalbarista.cat.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;


@XmlRootElement(name="User")
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
  @XmlElement
	private String username;
	
	@NotNull @Size(max=50)
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="client_id")
	private Long clientid;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="authorities", joinColumns=@JoinColumn(name="username"))
	@Column(name="authority")
	@Fetch(FetchMode.SELECT)
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
    if(encryptedPassword==null || encryptedPassword.trim().length()==0)
      return;
		this.password = encryptedPassword;
	}
	
  public String getEncryptedPassword(){return null;}
  
  @XmlElement
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	@XmlElement(name="authority")
	@XmlElementWrapper(name="authorities")
	public Set<String> getAuthorities() {
		return authorities;
	}

	@XmlElement(name="client-id")
  public Long getClientid() {
    return clientid;
  }

  public void setClientid(Long clientid) {
    this.clientid = clientid;
  }
} 
