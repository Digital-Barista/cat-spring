package com.digitalbarista.cat.view;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Error")
public class ServiceResponseError {
  @XmlElement
  public Integer code=0;

  @XmlElement
  public String errorKey;

  @XmlElement
  public String errorMessage;
}
