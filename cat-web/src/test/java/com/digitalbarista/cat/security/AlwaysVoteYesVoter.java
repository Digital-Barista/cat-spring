package com.digitalbarista.cat.security;

import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class AlwaysVoteYesVoter implements AccessDecisionVoter<MethodInvocation>{

  public boolean supports(ConfigAttribute paramConfigAttribute) {
    return true;
  }

  public boolean supports(Class<?> paramClass) {
    return true;
  }

  public int vote(Authentication paramAuthentication, MethodInvocation paramS,
      Collection<ConfigAttribute> paramCollection) {
    return AccessDecisionVoter.ACCESS_GRANTED;
  }

}
