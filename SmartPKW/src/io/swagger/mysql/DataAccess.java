package io.swagger.mysql;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import io.swagger.model.*;

public class DataAccess {
	
	private static DataAccess instance = null;
	
	//Database Variables
	private Statement myStmt;
	private ResultSet myRs;
	Connection myConn;
	
	//DateHelper
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	
	//Main
	public static void main(String[] args) {
		System.out.println("Main Class found");
		DataAccess.getInstance();
	}
	
	//Singleton Pattern
	public static DataAccess getInstance() {
		if (instance == null) {
			instance = new DataAccess();
		}
		return instance;
	}
	
	private DataAccess() {
		//Create Connection to Database
		String url = "jdbc:mysql://localhost:3306/SmartPKW";
		String username = "root";
		String password = "";
		
		try {
			// 1. Get a connection to Database
			myConn = DriverManager.getConnection(url, username, password);
			//2. Create Statement
			myStmt = myConn.createStatement();
			
			//3. Execute SQL query
			myRs = myStmt.executeQuery("select * from User");
			getRideById(2);
			//4. Process result set
			/*while (myRs.next()) {
				System.out.println(myRs.getString("userName"));
				System.out.println(myRs.getString("userId"));
			}*/
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	};
	
	//M O D E L  M E T H O D S
	//--RIDE-- Methods
	
	private RideWithId.StatusEnum enumSwitcher (String enumString) {
		switch (enumString) {
		case "PLANNING":
			return RideWithId.StatusEnum.PLANNING;
		case "RIDING":
			return RideWithId.StatusEnum.RIDING;
		case "FINISHED":
			return RideWithId.StatusEnum.FINISHED;
		}
		
		return null;
	}
	
	
	private RideWithId getRideById(int rideId) throws InterruptedException {
		try {
			myRs = myStmt.executeQuery("select * from Ride where rideId = "+rideId);			
			RideWithId rideWithId = new RideWithId();
			myRs.next();
			
			rideWithId.setCarById(myRs.getInt("carById"));
			rideWithId.setCreatedByUserById(myRs.getLong("createdByUserById"));
			rideWithId.setCreatedOn(myRs.getDate("createdOn"));
			rideWithId.setDescription(myRs.getString("description"));
			rideWithId.setRideId(myRs.getInt("rideId"));
			rideWithId.setStatus(enumSwitcher(myRs.getString("status")));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private RideWithId createRide (RideWithoutId rideWithoutId) {	
		// the mysql insert statement
	      String query = " insert into User (carById, createdByUserById, createdOn, description, status)"
	        + " values (?, ?, ?, ?, ?)";

	      // create the mysql insert preparedstatement
	      try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			  preparedStmt.setInt (1, rideWithoutId.getCarById());
			  preparedStmt.setLong (2, rideWithoutId.getCreatedByUserById());
			  preparedStmt.setDate   (3, (Date) rideWithoutId.getCreatedOn());
			  preparedStmt.setString	(4, rideWithoutId.getDescription());
			  preparedStmt.setString   (5, rideWithoutId.getStatus().toString());
			  
			  // execute the preparedstatement
			  preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private RideWithId updateRide(int rideId, RideWithoutId rideWithoutId) {
		// the mysql insert statement
	      String query = " insert into User (carById, createdByUserById, createdOn, description, status)"
	        + " values (?, ?, ?, ?, ?)";

	      // create the mysql insert preparedstatement
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
		      preparedStmt.setInt (1, rideWithoutId.getCarById());
		      preparedStmt.setLong (2, rideWithoutId.getCreatedByUserById());
		      preparedStmt.setDate   (3, (Date) rideWithoutId.getCreatedOn());
		      preparedStmt.setString	(4, rideWithoutId.getDescription());
		      preparedStmt.setString   (5, rideWithoutId.getStatus().toString());
		      
		      // execute the preparedstatement
		      preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	private void deleteRide(int rideId) {
		try {
			myStmt.executeUpdate("DELETE FROM Ride WHERE rideId = "+rideId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private List<RideWithId> searchRides(String toAddress, String fromAddress, Date startDate, Date endDate) {

		String mySQLstartDate = formatter.format(startDate);
		String mySQLendDate = formatter.format(endDate);
		
		try {
			myRs = myStmt.executeQuery("select * from Stop where address = "+toAddress+" AND time");
			// TODO List noch unvollendet
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	
	//--STOP-- Methods
	private StopWithId.StatusEnum enumSwitcherStop (String enumString) {
		switch (enumString) {
		case "ACCEPTED":
			return StopWithId.StatusEnum.ACCEPTED;
		case "DECLINED":
			return StopWithId.StatusEnum.DECLINED;
		case "PENDING":
			return StopWithId.StatusEnum.PENDING;
		}
		return null;
	}
	
	private StopWithId getStopById(int stopId) {
		try {
			myRs = myStmt.executeQuery("select * from Stop where stopId = "+stopId);			
			StopWithId stopWithId = new StopWithId();
			myRs.next();
			
			stopWithId.setAddress(myRs.getString("address"));
			stopWithId.setCreatedByUserById(myRs.getInt("createdByUserById"));
			stopWithId.setCreatedForRideById(myRs.getInt("createdForRideById"));
			stopWithId.setEndPointForUserById(myRs.getLong("endPointForUserById"));
			stopWithId.setLatitude(myRs.getInt("latitude"));
			stopWithId.setRank(myRs.getInt("rank"));
			stopWithId.setStartPointForUserById(myRs.getLong("startPointForUserById"));
			stopWithId.setStatus(enumSwitcherStop(myRs.getString("status")));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}
	private StopWithId getStopsByRideId(int rideId) {
		try {
			myRs = myStmt.executeQuery("select * from Stop where createdForRideById = "+rideId);
			StopWithId stopWithId = new StopWithId();
			myRs.next();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		
		return null;
	}
	private StopWithId getStopsByRideIdAndUserId(int rideId, int userId) {
		return null;
	}
	private StopWithId getStopsByUserId(int userId) {
		return null;
	}
	private StopWithId createStop(StopWithoutId stopWithoutId) {
		return null;
	}
	private StopWithId updateStop(int stopId, StopWithoutId stopWithoutId) {
		return null;
	}
	private void deleteStop(int stopId) {

	}
	
	
	//--CAR-- Methods
	private CarWithId getCarById(int carId) {
		return null;
	}
	private CarWithId getCarsByUserId(int userId) {
		return null;
	}
	private CarWithId createCar(CarWithoutId carWithoutId) {
		return null;
	}
	private CarWithId updateCar(int carId, CarWithoutId carWithoutId) {
		return null;
	}
	private void deleteCar(int carId) {
	}

	
	//--REVIEW-- Methods
	private ReviewWithId getReviewById(int reviewId) {
		return null;
	}
	private ReviewWithId getReviewsByUserId(int userId) {
		//Id des Users für den die Reviews gemacht wurden
		return null;
	}
	private ReviewWithId createReview(ReviewWithoutId reviewWithId) {
		return null;
	}
	private ReviewWithId updateReview(int reviewId, ReviewWithoutId reviewWithoutId) {
		return null;
	}
	private void deleteReview(int reviewId) {
		
	}

}
