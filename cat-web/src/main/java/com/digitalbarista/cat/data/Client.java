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

import org.hibernate.annotations.Proxy;

@XmlRootElement
@Entity
@Table(name="client")
@Proxy(lazy=false)
public class Client {

  public enum Type
  {
    Basic,
    Standard,
    Professional,
    Enterprise,
    Reseller;
  }
  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="client_id")
	private Long id;
	
	@Column(name="name")
	@NotNull @Size(max=64)
	private String name;
	
	@Column(name="contact_name")
	@Size(max=128)
	private String contactName;
	
	@Column(name="contact_email")
	@Size(max=128)
	private String contactEmail;
	
	@Column(name="contact_phone")
	@Size(max=32)  //@PhoneNumber -- We need to create this validator sometime.
	private String contactPhone;
	
	@Column(name="active")
	private boolean active;

	@Column(name="address1")
	@Size(max=128)
	private String address1;

	@Column(name="address2")
	@Size(max=128)
	private String address2;

	@Column(name="city")
	@Size(max=64)
	private String city;

	@Column(name="state")
	@Size(max=64)
	private String state;

	@Column(name="zip")
	@Size(max=16) //@ZipCode -- we need to define this validator at some point.
	private String zip;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	@NotNull
	private Client.Type type=Type.Basic;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  @XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

  @XmlElement
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

  @XmlElement
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

  @XmlElement
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

  @XmlElement
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

  @XmlElement
  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  @XmlElement
  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  @XmlElement
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @XmlElement
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @XmlElement
  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Client.Type getType() {
    return type;
  }

  public void setType(Client.Type type) {
    this.type = type;
  }
}
