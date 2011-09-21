package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.SQLException;

public interface ChangelogCreateStrategy {

	public void createChangelog(String dbName, Connection conn) throws SQLException;
	
}
