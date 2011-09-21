package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class DatabaseBootstrapper implements ServletContextListener {

	private InitialContext ctx;
	
	public void contextDestroyed(ServletContextEvent event) {
	}

	public InitialContext getNamingContext() throws NamingException
	{
		if(this.ctx==null)
			this.ctx = new InitialContext();
		return ctx;
	}
	
	public void contextInitialized(ServletContextEvent event) 
	{
		DataSource ds = null;
		try
		{
			ds = (DataSource)getNamingContext().lookup("java:/jdbc/campaignAdminDS");
		}
		catch(NamingException e)
		{
			throw new IllegalStateException("Unable to look up the database connection!",e);
		}
		
		Connection conn = null;
		String dbName = "cat2";

		try
		{
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			boolean exists = DBType.MySQL.dbExistCheckStrat().doesDatabaseExist(dbName, conn);
			
			if(!exists)
				DBType.MySQL.dbCreateStrat().createDatabase(dbName, conn);
			
			boolean changelogExists = DBType.MySQL.changelogCheckStrat().doesChangelogExist(dbName, conn);
			
			if(!changelogExists)
				DBType.MySQL.changelogCreateStrat().createChangelog(dbName, conn);

			
		}
		catch(SQLException e)
		{
			try{conn.rollback();}catch(SQLException e2){}
			throw new IllegalStateException("Unable to verify or create the changelog.",e);
		}
		finally
		{
			try{conn.close();}catch(Exception e){}
		}
	}

}
