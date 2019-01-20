package io.swagger.mysql;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	//Start
	public void startDatabase() {
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
	//--RIDE-- Methods ------------------------------------------------------------------------------------------------------------------------------------
	
	public RideWithId.StatusEnum enumSwitcher (String enumString) {
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
	
	
	public RideWithId getRideById(int rideId) throws InterruptedException {
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
			
			return rideWithId;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		

		
	}
	
	public RideWithId createRide (RideWithoutId rideWithoutId) {	
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
	
	public RideWithId updateRide(int rideId, RideWithoutId rideWithoutId) {
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
	public void deleteRide(int rideId) {
		try {
			myStmt.executeUpdate("DELETE FROM Ride WHERE rideId = "+rideId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<RideWithId> searchRides(String toAddress, String fromAddress, Date startDate, Date endDate) {

		String mySQLstartDate = formatter.format(startDate);
		String mySQLendDate = formatter.format(endDate);
		
		try {
			myRs = myStmt.executeQuery("select * from Stop where address = "+toAddress+" AND time");
			// TODO List noch unvollendet
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				
	}
	
	
	//--STOP-- Methods --------------------------------------------------------------------------------------------------------------------------------
	
	public StopWithId.StatusEnum enumSwitcherStop (String enumString) {
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
	
	public StopWithId getStopById(int stopId) {
		try {
			myRs = myStmt.executeQuery("select * from Stop where stopId = "+stopId);			
			StopWithId stopWithId = new StopWithId();
			myRs.next();
			
			stopWithId.setAddress(myRs.getString("address"));
			stopWithId.setCreatedByUserById(myRs.getInt("createdByUserById"));
			stopWithId.setCreatedForRideById(myRs.getInt("createdForRideById"));
			//stopWithId.setEndPointForUserById(myRs.getLong("endPointForUserById"));
			stopWithId.setLatitude(myRs.getInt("latitude"));
			stopWithId.setRank(myRs.getInt("rank"));
			//stopWithId.setStartPointForUserById(myRs.getLong("startPointForUserById"));
			stopWithId.setStatus(enumSwitcherStop(myRs.getString("status")));
			
			return stopWithId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}
	public List <StopWithId> getStopsByRideId(int rideId) {
		try {
			myRs = myStmt.executeQuery("select * from Stop where createdForRideById = "+rideId);
			List<StopWithId> stopsWithId = new ArrayList<StopWithId>();
			
			while(myRs.next()) {	
				StopWithId stopWithId = new StopWithId();
				
				stopWithId.setAddress(myRs.getString("address"));
				stopWithId.setCreatedByUserById(myRs.getInt("createdByUserById"));
				stopWithId.setCreatedForRideById(myRs.getInt("createdForRideById"));
				//stopWithId.setEndPointForUserById(myRs.getLong("endPointForUserById"));
				stopWithId.setLatitude(myRs.getInt("latitude"));
				stopWithId.setRank(myRs.getInt("rank"));
				//stopWithId.setStartPointForUserById(myRs.getLong("startPointForUserById"));
				stopWithId.setStatus(enumSwitcherStop(myRs.getString("status")));
				
				stopsWithId.add(stopWithId);
			}
			return stopsWithId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}			
		
	}
	public StopWithId getStopsByRideIdAndUserId(int rideId, int userId) {
		
		
		return null;
	}
	public StopWithId getStopsByUserId(int userId) {
		return null;
	}
	public StopWithId createStop(StopWithoutId stopWithoutId) {
		return null;
	}
	public StopWithId updateStop(int stopId, StopWithoutId stopWithoutId) {
		return null;
	}
	public void deleteStop(int stopId) {

	}
	
	
	//--CAR-- Methods ---------------------------------------------------------------------------------------------------------------------
	
	public CarWithId.TrunkspaceEnum enumSwitcherCar (String enumString) {
		switch (enumString) {
		case "LARGE":
			return CarWithId.TrunkspaceEnum.LARGE;
		case "MEDIUM":
			return CarWithId.TrunkspaceEnum.MEDIUM;
		case "SMALL":
			return CarWithId.TrunkspaceEnum.SMALL;
		}
		return null;
	}
	
	
	public CarWithId getCarById(int carId) {
		try {
			myRs = myStmt.executeQuery("select * from Stop where carId = "+ carId);			
			CarWithId carWithId = new CarWithId();
			myRs.next();
			
			carWithId.setCreatedByUserById(myRs.getLong("createdByUserById"));
			carWithId.setDescription("description");
			carWithId.setSeats(myRs.getInt("seats"));
			carWithId.setTrunkspace(enumSwitcherCar(myRs.getString("trunkspace")));
			
			return carWithId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List<CarWithId> getCarsByUserId(int userId) {
		try {
			myRs = myStmt.executeQuery("select * from Stop where createdByUserById = "+ userId);
			List<CarWithId> carsWithId = new ArrayList<CarWithId>();
			while (myRs.next()) {
				CarWithId carWithId = new CarWithId();

				carWithId.setCreatedByUserById(myRs.getLong("createdByUserById"));
				carWithId.setDescription("description");
				carWithId.setSeats(myRs.getInt("seats"));
				carWithId.setTrunkspace(enumSwitcherCar(myRs.getString("trunkspace")));
				
				carsWithId.add(carWithId);
			}
			return carsWithId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public CarWithId createCar(CarWithoutId carWithoutId) {
		// the mysql insert statement
	      String query = " insert into User (createdByUserById, description, seats, trunkspace)"
	        + " values (?, ?, ?, ?)";

	      // create the mysql insert preparedstatement
	      try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			  preparedStmt.setLong (1, carWithoutId.getCreatedByUserById());
			  preparedStmt.setString (2, carWithoutId.getDescription());
			  preparedStmt.setInt   (3, carWithoutId.getSeats());
			  preparedStmt.setString	(4, carWithoutId.getTrunkspace().toString());
			  
			  // execute the preparedstatement
			  preparedStmt.execute();
			  
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public CarWithId updateCar(int carId, CarWithoutId carWithoutId) {
		// the mysql insert statement
	     /* String query = "insert into User where cardId = "+carId+"(createdByUserById, description, seats, trunkspace)"
	        + " values (?, ?, ?, ?)";*/
		
		String query = "UPDATE Car SET createdByUserById = ?, description = ?, seats = ?, trunkspace = ? WHERE userId ="+ carId;

	      // create the mysql insert preparedstatement
	      try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			  preparedStmt.setLong (1, carWithoutId.getCreatedByUserById());
			  preparedStmt.setString (2, carWithoutId.getDescription());
			  preparedStmt.setInt   (3, carWithoutId.getSeats());
			  preparedStmt.setString	(4, carWithoutId.getTrunkspace().toString());
			  
			  // execute the preparedstatement
			  preparedStmt.execute();
			  
			  // TODO Car wird noch nicht zurück gegeben weil aufwendig, weiß nicht ob das nötig ist.
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	 }
	
	public void deleteCar(int carId) {
		try {
			myStmt.executeUpdate("DELETE FROM Car WHERE carId = "+carId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	
	
	//--REVIEW-- Methods------------------------------------------------------------------------------------------------------------------------
	public ReviewWithId getReviewById(int reviewId) {
		try {
			myRs = myStmt.executeQuery("select * from Review where reviewId = "+ reviewId);			
			ReviewWithId reviewWithId = new ReviewWithId();
			myRs.next();
			
			reviewWithId.setComment(myRs.getString("comment"));
			reviewWithId.setCreatedByUserById(myRs.getLong("createdByUserById"));
			reviewWithId.setCreatedForUserById(myRs.getLong("createdForUserById"));
			reviewWithId.setCreatedOn(myRs.getDate("createdOn"));
			reviewWithId.setRating(myRs.getInt("rating"));
			reviewWithId.setReviewId(myRs.getInt("reviewId"));
			
			return reviewWithId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ReviewWithId getReviewsByUserId(int userId) {
		//Id des Users für den die Reviews gemacht wurden
		
		
		return null;
	}
	public ReviewWithId createReview(ReviewWithoutId reviewWithId) {
		return null;
	}
	public ReviewWithId updateReview(int reviewId, ReviewWithoutId reviewWithoutId) {
		return null;
	}
	public void deleteReview(int reviewId) {
		
	}

}
