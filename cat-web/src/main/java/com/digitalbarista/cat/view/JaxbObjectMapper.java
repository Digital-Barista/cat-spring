package com.digitalbarista.cat.view;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JaxbObjectMapper extends ObjectMapper {

  public JaxbObjectMapper()
  {
    this.getSerializationConfig().insertAnnotationIntrospector(new JaxbAnnotationIntrospector());
    this.getDeserializationConfig().insertAnnotationIntrospector(new JaxbAnnotationIntrospector());
  }
  
}
