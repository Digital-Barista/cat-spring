package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDatabaseExistenceCheckStrategy implements
		DatabaseExistenceCheckStrategy {

	public boolean doesDatabaseExist(String dbName, Connection conn) throws SQLException 
	{
		Statement stmt=null;
		ResultSet rs=null;
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show databases like '"+dbName+"'");
			
			while(rs.next())
			{
				return true;
			}
			return false;
		}
		finally
		{
			try{rs.close();}catch(Exception ex){}
			try{stmt.close();}catch(Exception ex){}			
		}
	}

}
