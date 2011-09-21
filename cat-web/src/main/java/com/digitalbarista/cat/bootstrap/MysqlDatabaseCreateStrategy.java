package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDatabaseCreateStrategy implements DatabaseCreateStrategy {

	public void createDatabase(String dbName, Connection conn) throws SQLException 
	{
		Statement stmt = null;
		try
		{
			stmt = conn.createStatement();
			stmt.execute("create database "+dbName);
			stmt.close();
		}
		finally
		{
			try{stmt.close();}catch(Exception e){}
		}
	}

}
