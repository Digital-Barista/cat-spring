package com.digitalbarista.cat.view;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.data.User;
import com.digitalbarista.cat.util.SerializableList;

@XmlRootElement(name="response")
public class ServiceResponse {
  
  @XmlElement
  private ServiceResponseError error;
  
  @XmlElementRefs({
    @XmlElementRef(type=SerializableList.class),
    @XmlElementRef(type=User.class),
    @XmlElementRef(type=Client.class)
  })
  private Object respObj;

  public ServiceResponseError getError() {
    return error;
  }

  @XmlTransient
  public void setError(ServiceResponseError error) {
    this.error = error;
  }

  @XmlTransient
  public Object getResponse() {
    return respObj;
  }

  public void setResponse(Object respObj) {
    this.respObj = respObj;
  }
}
