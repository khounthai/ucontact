package com.ril.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;
@Component
public class Database {

	private Connection sqlConnection = null;
	private static boolean hsqlLoaded = false;

	public Database() throws ClassNotFoundException, SQLException {
		ensureHSQLLoading();
		ensureConnectionOpened();
	}

	private void ensureConnectionOpened() throws SQLException {
		if (sqlConnection == null || sqlConnection.isClosed()) {
			sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ucontact", "root", "");
			sqlConnection.setAutoCommit(true);
		}
	}

	private static void ensureHSQLLoading() throws ClassNotFoundException {
		synchronized (Database.class) {
			if (!hsqlLoaded) {
				Class.forName("com.mysql.jdbc.Driver");
				hsqlLoaded = true;
			}
		}
	}

	public Connection getSqlConnection() throws SQLException {
		ensureConnectionOpened();
		return sqlConnection;
	}
}