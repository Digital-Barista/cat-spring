package com.digitalbarista.cat.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement(name="NetworkAccount")
@Entity
@Table(name="network_account")
@Proxy(lazy=false)
public class NetworkAccount {

  @XmlType(name="NetworkAccount.Type")
  public enum Type
  {
    SMS,
    Email,
    Twitter,
    Facebook
  }
  
  @Column(name="account_id")
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Id
  private Long id;
  
  @Column(name="type")
  @Enumerated(EnumType.STRING)
  @NotNull
  private Type type;
  
  @Column(name="name")
  @NotEmpty @Size(max=16)
  private String accountName;

  @Column(name="credentials")
  @Size(max=128)
  private String credentials;
  
  @Column(name="description")
  @Size(max=128)
  private String description;

  @Column(name="client_id")
  private Long clientId;
  
  @XmlElement
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @XmlElement
  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  @XmlElement
  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  @XmlTransient
  public String getCredentials() {
    return credentials;
  }

  public void setCredentials(String credentials) {
    this.credentials = credentials;
  }

  @XmlElement
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  
  @XmlElement
  public boolean isAuthorized()
  {
    return credentials!=null;
  }

  @XmlElement
	public Long getClientId()
  {
  	return clientId;
  }

	public void setClientId(Long clientId)
  {
  	this.clientId = clientId;
  }
}
