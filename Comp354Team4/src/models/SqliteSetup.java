package models;

import java.sql.*;

import models.*;

public class SqliteSetup {
	//connection strings
	public static String dataBaseName = "Project.db";
	public static String connection = "jdbc:sqlite:" + SqliteSetup.dataBaseName;
	public static String sqliteClass = "org.sqlite.JDBC";
}
