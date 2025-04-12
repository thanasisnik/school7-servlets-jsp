package gr.aueb.cf.schoolapp.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
	
	private static final BasicDataSource ds = new BasicDataSource();
	private static Connection connection;
	
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/school7dbstaging?serverTimeZone=UTC");
		ds.setUsername("user7");

		//ds.setPassword(System.getenv("PASS_USER7")); need to set env variable
		ds.setPassword("12345");

		// Το Maven automatically detects the driver, no need for the following
		// ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

		ds.setInitialSize(10);
		ds.setMinIdle(10);
		ds.setMaxIdle(15);
	}

	/**
	 * No instances of this class should be available
	 */
	private DBUtil() {

	}

	
	public static Connection getConnection() throws SQLException {
		connection = ds.getConnection();
		//System.out.println("Connection given");
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if (connection != null) connection.close();
	}	
}
