package com.digitalbarista.cat.bootstrap;

public enum DBType {

	MySQL(new MysqlDatabaseExistenceCheckStrategy(),
		  new MysqlDatabaseCreateStrategy(),
		  new MysqlChangelogExistenceCheckStrategy(),
		  new MysqlChangelogCreateStrategy());
	
	private DBType(DatabaseExistenceCheckStrategy dbExistCheckStrat,
				   DatabaseCreateStrategy dbCreateStrat,
				   ChangelogExistenceCheckStrategy changelogCheckStrat,
				   ChangelogCreateStrategy changelogCreateStrat)
	{
		this.dbExistCheckStrat = dbExistCheckStrat;
		this.dbCreateStrat = dbCreateStrat;
		this.changelogCheckStrat = changelogCheckStrat;
		this.changelogCreateStrat = changelogCreateStrat;
	}
	
	private DatabaseExistenceCheckStrategy dbExistCheckStrat;
	private DatabaseCreateStrategy dbCreateStrat;
	private ChangelogExistenceCheckStrategy changelogCheckStrat;
	private ChangelogCreateStrategy changelogCreateStrat;
	
	public DatabaseExistenceCheckStrategy dbExistCheckStrat()
	{
		return dbExistCheckStrat;
	}
	
	public DatabaseCreateStrategy dbCreateStrat()
	{
		return dbCreateStrat;
	}
	
	public ChangelogExistenceCheckStrategy changelogCheckStrat()
	{
		return changelogCheckStrat;
	}
	
	public ChangelogCreateStrategy changelogCreateStrat()
	{
		return changelogCreateStrat;
	}
}
