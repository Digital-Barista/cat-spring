package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class DatabaseBootstrapper implements ServletContextListener {

	private String createChangeLogTable = "CREATE TABLE changelog (change_number BIGINT NOT NULL, complete_dt TIMESTAMP NOT NULL, applied_by VARCHAR(100) NOT NULL, description VARCHAR(500) NOT NULL)";
	private String createChangeLogConstraint = "ALTER TABLE changelog ADD CONSTRAINT Pkchangelog PRIMARY KEY (change_number)";
	
	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		try
		{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:/jdbc/campaignAdminDS");
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			int rowCount=0;
			try
			{
				conn = ds.getConnection();
				conn.setAutoCommit(false);
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery("show databases");
				
				boolean exists=false;
				while(rs.next())
				{
					if("cat2".equalsIgnoreCase(rs.getString(0)))
					{
						exists=true;
						break;
					}
				}
				rs.close();
				stmt.close();
				
				if(!exists)
				{
					stmt = conn.createStatement();
					rowCount = stmt.executeUpdate("create database cat2");
					if(rowCount<0)
					{
						conn.rollback();
						return;
					}
				}
				stmt.close();
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery("show tables from cat like 'changelog'");
				exists=false;
				while(rs.next())
				{
					exists=true;
					return;
				}
				
				if(!exists)
				{
					stmt = conn.createStatement();
					rowCount = stmt.executeUpdate(createChangeLogTable);
					if(rowCount<0)
					{
						conn.rollback();
						return;
					}
					stmt.close();
					
					stmt = conn.createStatement();
					rowCount = stmt.executeUpdate(createChangeLogConstraint);
					if(rowCount<0)
					{
						conn.rollback();
						return;
					}
				}

				
			}
			catch(Exception e)
			{
				conn.rollback();
			}
			finally
			{
				try{rs.close();}catch(Exception e){}
				try{stmt.close();}catch(Exception e){}
				try{conn.close();}catch(Exception e){}
			}
		}
		catch(Exception e)
		{
			
		}
	}

}
