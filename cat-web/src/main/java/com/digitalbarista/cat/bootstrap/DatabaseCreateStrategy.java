package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseCreateStrategy {

	public void createDatabase(String dbName, Connection conn) throws SQLException;
	
}
