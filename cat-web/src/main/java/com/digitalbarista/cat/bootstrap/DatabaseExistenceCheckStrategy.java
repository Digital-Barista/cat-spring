package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseExistenceCheckStrategy {
	
	public boolean doesDatabaseExist(String dbName, Connection conn) throws SQLException;
	
}
