package com.util;

import java.sql.Connection;
import java.sql.DriverManager;






// added 2 files in webContent -> WEB-INF -> lib -> mysql-connector-java-8.0.11.jar + protobuf-java-2.6.0.jar
// pay attention to the name of the localhost of the mySQL ( could be localhost:3306 or 127.0.0.1:3306 ) -> add it to the URL
// add to the URD -> ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC\r\n (after the DataBase) 
// test 




public class DBConnectionUtil {
	// Define the database properties
	//private static final String URL = "jdbc:mysql://localhost:3306/communication";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/communication?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC\r\n";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PASSWORD = "MySuperPassword";
	private static final String USERNAME = "root";
	private static Connection connection = null;

	public static Connection openConnection() {
		// Check the connection
		if (connection != null) {
			return connection;
		} else {
			try {
				// Load the driver
				Class.forName(DRIVER);
				// Get the connection
				connection = DriverManager.getConnection(URL, USERNAME ,PASSWORD);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return connection;
		}
	}

}
