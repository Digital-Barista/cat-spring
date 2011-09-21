package com.digitalbarista.cat.bootstrap;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.jboss.mx.util.MBeanServerLocator;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.dbdeploy.DbDeploy;

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
		String dbName = "cat2";
		
		DataSource ds = null;
		try
		{
			ds = (DataSource)getNamingContext().lookup("java:/jdbc/campaignAdmin2DS");
			String url = null;
			if(ds.getClass().getName().equals("org.apache.commons.dbcp.BasicDataSource"))
			{
				Method m = ds.getClass().getDeclaredMethod("getUrl");
				url = (String)m.invoke(ds);
			}else if(ds.getClass().getName().equals("org.jboss.resource.adapter.jdbc.WrapperDataSource"))
			{
				Hashtable<String,String> names = new Hashtable<String,String>();
				names.put("name", "jdbc/campaignAdmin2DS");
				names.put("service", "ManagedConnectionFactory");
				Element element = (Element)MBeanServerLocator.locateJBoss().getAttribute(new ObjectName("jboss.jca",names),"ManagedConnectionFactoryProperties");
				NodeList configProps = element.getElementsByTagName("config-property");
				configLoop:
				for(int item=0; item<configProps.getLength(); item++)
				{
					Element cfgProp = (Element)configProps.item(item);
					if("XADataSourceProperties".equals(cfgProp.getAttribute("name")))
					{
						String propText = cfgProp.getTextContent();
						String[] props = propText.split("\\n");
						for(String prop : props)
						{
							if(prop.startsWith("URL="))
							{
								url=prop.substring(4);
								break configLoop;
							}
						}
					}
				}
			}
			if(url != null)
			{
				int dbNameStart = url.lastIndexOf("/")+1;
				if(dbNameStart==-1 || dbNameStart==url.length()-1)
				{
					System.out.println("Could not parse target database name.  Using 'cat2' as default.");
				}
				else
				{
					int paramsStart = url.lastIndexOf("?");
					if(paramsStart==-1)
						dbName=url.substring(dbNameStart);
					else
						dbName=url.substring(dbNameStart,paramsStart);
					System.out.println("Using database name '"+dbName+"' for creation.");
				}
			} else {
				System.out.println("Unknown datasource type. "+ds.getClass().getName()+"  Could not determine database name.  Using 'cat2' as default.");
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to determine target database name.  Using 'cat2' as default.");
			e.printStackTrace(System.err);
		}
		
		try
		{
			ds = (DataSource)getNamingContext().lookup("java:/jdbc/rawCampaignAdmin2DS");
		}
		catch(NamingException e)
		{
			throw new IllegalStateException("Unable to look up the database connection!",e);
		}
		
		Connection conn = null;

		try
		{
			conn = ds.getConnection();
			
			boolean exists = DBType.MySQL.dbExistCheckStrat().doesDatabaseExist(dbName, conn);
			
			if(!exists)
				DBType.MySQL.dbCreateStrat().createDatabase(dbName, conn);
			
			boolean changelogExists = DBType.MySQL.changelogCheckStrat().doesChangelogExist(dbName, conn);
			
			if(!changelogExists)
				DBType.MySQL.changelogCreateStrat().createChangelog(dbName, conn);
			
			conn.close();
			
			DbDeploy dep = new DbDeploy();
			dep.setDbms("mysql");
			dep.setDriver("com.digitalbarista.cat.bootstrap.WrapperDriver");
			dep.setScriptdirectory(new File(event.getServletContext().getRealPath("/WEB-INF/db-scripts")));
			dep.setUrl("jdbc:wrapped:java:/jdbc/campaignAdmin2DS");
			dep.setUserid("system");
			try
			{
				dep.go();
				System.out.println("*******************DB synced!");
			}
			catch(Exception e)
			{
				System.err.println("*******************Unable to sync DB!!!!  The application WILL NOT function properly!");
			}
			
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
