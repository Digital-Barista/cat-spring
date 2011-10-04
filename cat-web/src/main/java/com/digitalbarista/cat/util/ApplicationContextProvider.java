package com.digitalbarista.cat.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author Falken
 *
 *  This class is necessary because spring stupidly, and gratuitously makes any APpContextAware
 *  bean a proxy, God only knows why.  I'm gonna file a bug report, but it FUBARs all kinds of
 *  things.  This is a way to get the app context w/o that problem.
 *
 */
public class ApplicationContextProvider implements ApplicationContextAware {

  private ApplicationContext ctx;
  
  public ApplicationContext getApplicationContext()
  {
    return ctx;
  }
  
  public void setApplicationContext(ApplicationContext newCtx)
      throws BeansException {
    ctx = newCtx;
  }

}
