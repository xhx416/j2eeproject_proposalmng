package com.tcs.ilp.fqpms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tcs.ilp.fqpms.exception.FQException;

public class DatabaseUtil {

	/**
	 * Its a static method which creates the connection
	 * 
	 * @return
	 * @throws FQException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws FQException {
		Connection con = null;
		try {
			Class.forName(DatabaseConstants.DRIVER_NAME);
			con = DriverManager.getConnection(DatabaseConstants.URL,
					DatabaseConstants.USERNAME, DatabaseConstants.PASSWORD);
		} catch (Exception e) {
			throw new FQException(e.getMessage());
		}
		return con;
	}

	/**
	 * close the connection
	 * 
	 * @param con
	 * @throws Exception
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) throws FQException {

		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				throw new FQException(e.getMessage());
			}

	}

	/**
	 * close the statements
	 * 
	 * @param ps
	 * @throws FQException
	 * @throws SQLException
	 */
	public static void closeStatement(PreparedStatement ps) throws FQException {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new FQException(e.getMessage());
			}
		}
	}

}
