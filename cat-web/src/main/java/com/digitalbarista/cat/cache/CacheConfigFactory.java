package com.digitalbarista.cat.cache;

import java.net.InetAddress;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;

public class CacheConfigFactory implements FactoryBean<String> {

	private Map<String,String> configMap;
	
	public String getObject() throws Exception {
		String address = InetAddress.getLocalHost().getHostAddress();
		if(!configMap.containsKey(address))
			return "/WEB-INF/cache-config/local-ehcache.xml";
		
		return "/WEB-INF/cache-config/"+configMap.get(address)+"-ehcache.xml";
	}

	public Class<?> getObjectType() {
		return String.class;
	}

	public boolean isSingleton() {
		return false;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}

}
