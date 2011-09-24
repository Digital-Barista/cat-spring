package com.digitalbarista.cat.bootstrap;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WrapperDriver implements Driver {

	static
	{
		WrapperDriver driver = new WrapperDriver();
		try
		{
			DriverManager.registerDriver(driver);
		}
		catch(SQLException e)
		{
			System.err.println("Unable to register WrappingDriver with the JDBC DriverManager.");
			e.printStackTrace(System.err);
		}
	}
	
	public Connection connect(String url, Properties info) throws SQLException {
		try
		{
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(url.substring(13));
			return ds.getConnection();
		}
		catch(NamingException ne)
		{
			throw new SQLException("Could not find specified datasource.",ne);
		}
	}

	public boolean acceptsURL(String url) throws SQLException {
		return url!=null && url.startsWith("jdbc:wrapped:") && url.length()>13;
	}

	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return new DriverPropertyInfo[0];
	}

	public int getMajorVersion() {
		return 1;
	}

	public int getMinorVersion() {
		return 0;
	}

	public boolean jdbcCompliant() {
		return true;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}
}
