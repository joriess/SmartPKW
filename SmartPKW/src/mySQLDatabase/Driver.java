package mySQLDatabase;

import java.sql.*;
public class Driver {

	public static Connection myConn;
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/SmartPKW";
		String username = "root";
		String password = "";
		System.out.println("Database started");
		try {
			// 1. Get a connection to Databaase
			myConn = DriverManager.getConnection(url, username, password);
			getUserName();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static String getUserName() throws Exception{
		try {
			//2. Create Statement
			Statement myStmt = myConn.createStatement();
			
			//3. Execute SQL query
			ResultSet myRs = myStmt.executeQuery("select * from user");
			
			//4. Process result set
			while (myRs.next()) {
				System.out.println(myRs.getString("name"));
				System.out.println(myRs.getString("age"));
				//return myRs.getString("name");
				//myRs.getString("age");
			}
			myStmt.executeUpdate("insert into user (userId, name, age) values (5, 'Michael', 25)");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return "";
	}

}
