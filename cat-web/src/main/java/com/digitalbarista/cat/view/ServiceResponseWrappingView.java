package com.digitalbarista.cat.view;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class ServiceResponseWrappingView extends AbstractView {

  private AbstractView viewDelegate;
  
  public ServiceResponseWrappingView(AbstractView wrappedView)
  {
    viewDelegate = wrappedView;
  }
  
  @Override
  protected void renderMergedOutputModel(Map<String, Object> paramMap,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    throw new IllegalStateException("This method should NEVER be called, since it's being delegated elsewhere.");
  }

  public void addStaticAttribute(String name, Object value) {
    viewDelegate.addStaticAttribute(name, value);
  }

  public boolean equals(Object arg0) {
    return viewDelegate.equals(arg0);
  }

  public void setBeanName(String beanName) {
    viewDelegate.setBeanName(beanName);
  }

  public String getBeanName() {
    return viewDelegate.getBeanName();
  }

  public void setContentType(String contentType) {
    viewDelegate.setContentType(contentType);
  }

  public String getContentType() {
    return viewDelegate.getContentType();
  }

  public void setRequestContextAttribute(String requestContextAttribute) {
    viewDelegate.setRequestContextAttribute(requestContextAttribute);
  }

  public String getRequestContextAttribute() {
    return viewDelegate.getRequestContextAttribute();
  }

  public void setAttributesCSV(String propString)
      throws IllegalArgumentException {
    viewDelegate.setAttributesCSV(propString);
  }

  public Map<String, Object> getAttributesMap() {
    return viewDelegate.getAttributesMap();
  }

  public Map getStaticAttributes() {
    return viewDelegate.getStaticAttributes();
  }

  public int hashCode() {
    return viewDelegate.hashCode();
  }

  public void setAttributes(Properties attributes) {
    viewDelegate.setAttributes(attributes);
  }

  public void setAttributesMap(Map<String, ?> attributes) {
    viewDelegate.setAttributesMap(attributes);
  }

  public void render(Map paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    if(paramMap.size()!=1 || (paramMap.containsKey("error") && paramMap.size()!=2))
      throw new IllegalArgumentException("ServiceResponseWrappingView doesn't know how to deal with multiple objects in the view!  Make sure you use it for service methods ONLY!");
    
    ServiceResponse resp = new ServiceResponse();
    
    if(paramMap.containsKey("error"))
      resp.setError((ServiceResponseError)paramMap.remove("error"));
    
    String key=(String)paramMap.keySet().iterator().next();
    resp.setResponse(paramMap.remove(key));
    paramMap.put(key, resp);
    
    viewDelegate.render(paramMap, request, response);
  }

  public String toString() {
    return viewDelegate.toString();
  }

  
}
