package com.digitalbarista.cat.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.data.User;

@XmlRootElement(name="list")
public class SerializableList<T> extends ArrayList<T>
{
  public SerializableList(){}
  
  public SerializableList(Collection<T> collection)
  {
    super(collection);
  }
  
  @XmlElementRefs({
    @XmlElementRef(type=User.class),
    @XmlElementRef(type=Client.class)
  })
  public List<T> getList()
  {
    return this;
  }
}
