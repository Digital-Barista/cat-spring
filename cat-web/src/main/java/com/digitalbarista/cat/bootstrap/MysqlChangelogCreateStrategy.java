package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlChangelogCreateStrategy implements ChangelogCreateStrategy {

	private String createChangeLogTable = "CREATE TABLE changelog (change_number BIGINT NOT NULL, complete_dt TIMESTAMP NOT NULL, applied_by VARCHAR(100) NOT NULL, description VARCHAR(500) NOT NULL)";
	private String createChangeLogConstraint = "ALTER TABLE changelog ADD CONSTRAINT Pkchangelog PRIMARY KEY (change_number)";

	public void createChangelog(String dbName, Connection conn) throws SQLException {
		Statement stmt = null;
		try
		{
			stmt = conn.createStatement();
			stmt.execute("using "+dbName);
			stmt.close();
			
			stmt = conn.createStatement();
			stmt.execute(createChangeLogTable);
			stmt.close();
			
			stmt = conn.createStatement();
			stmt.execute(createChangeLogConstraint);
			stmt.close();
		}
		finally
		{
			try{stmt.close();}catch(Exception e){}
		}
	}

}
