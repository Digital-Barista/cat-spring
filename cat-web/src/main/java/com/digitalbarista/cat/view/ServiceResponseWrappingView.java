package com.digitalbarista.cat.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import com.digitalbarista.cat.util.SerializableList;

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
    
    ServiceResponse resp = new ServiceResponse();
    
    List<BindingResult> erroredBindingResults = new ArrayList<BindingResult>();
    
    boolean hasBindingErrors=false;
    
    for(String key : new HashSet<String>((Set<String>)paramMap.keySet()))
    {
      if(key.equals("error"))
      {
        resp.setError((ServiceResponseError)paramMap.remove("error"));
        continue;
      }
      
      if(BindingResult.class.isAssignableFrom(paramMap.get(key).getClass()))
      {
        BindingResult result = (BindingResult)paramMap.remove(key);
        if(result.hasErrors())
        {
          hasBindingErrors=true;
          erroredBindingResults.add(result);
        }
        continue;
      }
      
      if(resp.getResponse()!=null)
      {
        throw new IllegalArgumentException("ServiceResponseWrappingView doesn't know how to deal with multiple objects in the view!  Make sure you use it for service methods ONLY!");
      }
      
      resp.setResponse(paramMap.get(key));
      paramMap.put("response", resp);
    }
        
    if(hasBindingErrors)
    {
      resp.setError(new ServiceResponseError());
      resp.getError().setCode(100); //Binding Errors
      resp.getError().setErrorKey("binding.error");
      resp.getError().setErrorMessage("There were errors binding the submitted object to the expected object!");
      resp.setResponse(new SerializableList<BindingResult>(erroredBindingResults));
    }

    if(resp.getResponse()==null)
      paramMap.put("response", resp);
    
    viewDelegate.render(paramMap, request, response);
  }

  public String toString() {
    return viewDelegate.toString();
  }

  
}
