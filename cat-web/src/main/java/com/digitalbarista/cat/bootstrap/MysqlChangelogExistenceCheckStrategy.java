package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlChangelogExistenceCheckStrategy implements
		ChangelogExistenceCheckStrategy {

	public boolean doesChangelogExist(String dbName, Connection conn) throws SQLException 
	{
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show tables from "+dbName+" like 'changelog'");
			while(rs.next())
			{
				return true;
			}
			return false;
		}
		finally
		{
			try{rs.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
	}

}
