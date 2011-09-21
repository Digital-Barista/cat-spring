package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.SQLException;

public interface ChangelogExistenceCheckStrategy {
	
	public boolean doesChangelogExist(String dbName, Connection conn) throws SQLException;
	
}
