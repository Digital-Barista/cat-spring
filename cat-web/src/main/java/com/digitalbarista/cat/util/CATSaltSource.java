package com.digitalbarista.cat.util;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

public class CATSaltSource implements SaltSource {

	public Object getSalt(UserDetails ud) {
		return ud.getUsername()+"DBI-Rules";
	}

}
