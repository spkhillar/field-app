package com.telenoetica.web.util;

import org.codehaus.jackson.map.ObjectMapper;

public class JqGridObjectMapper {
	
	public static JqGridFilter map(String jsonString) {
		
    	if (jsonString != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	
        	try {
				return mapper.readValue(jsonString, JqGridFilter.class);
        	} catch (Exception e) {
				throw new RuntimeException (e);
			}
    	}
    	
		return null;
	}

}
