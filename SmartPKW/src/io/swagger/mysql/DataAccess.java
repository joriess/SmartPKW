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
	private void startDatabase() {
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 1. Get a connection to Database
			myConn = DriverManager.getConnection(url, username, password);
			//2. Create Statement
			myStmt = myConn.createStatement();
			
			//3. Execute SQL query
			myRs = myStmt.executeQuery("select * from User");
			//getRideById(2);
			//4. Process result set
			/*while (myRs.next()) {
				System.out.println(myRs.getString("userName"));
				System.out.println(myRs.getString("userId"));
			}*/
			//System.out.println("Connection steht");
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	};
	
	//M O D E L  M E T H O D S
	//--RIDE-- Methods
	
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
	
	
	public RideWithId getRideById(int rideId) {
		try {
			myRs = myStmt.executeQuery("select * from Ride where rideId = "+rideId);			
			RideWithId rideWithId = new RideWithId();
			myRs.next();
			
			rideWithId.setCarById(myRs.getInt("carById"));
			rideWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
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
			  preparedStmt.setString (2, rideWithoutId.getCreatedByUserById());
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
		      preparedStmt.setString (2, rideWithoutId.getCreatedByUserById());
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
	public List<RideWithId> searchRides(String toAddress, String fromAddress, String startDate, String endDate) {

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
			stopWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
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
			StopWithId stopWithId = new StopWithId();
			List<StopWithId> stopsWithId = new ArrayList<StopWithId>();
			
			while(myRs.next()) {	
				/*stopWithId.setAddress(myRs.getString("address"));
				stopWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
				stopWithId.setCreatedForRideById(myRs.getInt("createdForRideById"));
				//stopWithId.setEndPointForUserById(myRs.getLong("endPointForUserById"));
				stopWithId.setLatitude(myRs.getInt("latitude"));
				stopWithId.setRank(myRs.getInt("rank"));
				//stopWithId.setStartPointForUserById(myRs.getLong("startPointForUserById"));
				stopWithId.setStatus(enumSwitcherStop(myRs.getString("status")));*/
				
				stopsWithId.add(getStopById(myRs.getInt("stopId")));
			}
			return stopsWithId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}			
		
	}
	public List<StopWithId> getStopsByRideIdAndUserId(int rideId, String userId) {
		try {
			myRs = myStmt.executeQuery("SELECT * FROM Ride, User WHERE Ride.rideId = " +rideId+" AND User.userId = " + userId);
			List<StopWithId> stopsByRideIdAndUserId = new ArrayList<StopWithId>();
			
			while (myRs.next()) {
				stopsByRideIdAndUserId.add(getStopById(myRs.getInt("stopId")));
			}
			return stopsByRideIdAndUserId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	public List<StopWithId> getStopsByUserId(String userId) {
		try {
			myRs = myStmt.executeQuery("SELECT * FROM Stop WHERE createdByUserById = " + userId);
			List<StopWithId> stopsByUserId = new ArrayList<StopWithId>();
			
			while (myRs.next()) {
				stopsByUserId.add(getStopById(myRs.getInt("stopId")));
			}
			return stopsByUserId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List<StopWithId> createStops(List<StopWithoutId> stopsWithoutId) {
		try {
			//myRs = myStmt.executeQuery("SELECT * FROM Stop WHERE createdByUserById = " + userId);
			List<StopWithId> createdStops = new ArrayList<StopWithId>();
			
			while (myRs.next()) {
				createdStops.add(getStopById(myRs.getInt("stopId")));
			}
			return createdStops;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public StopWithId updateStop(int rideId, String userId, List<StopWithoutId> stopsWithoutId) {
		return null;
	}
	public void deleteStops(int rideId, String userId) {
		

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
				myRs = myStmt.executeQuery("select * from Car where carId = "+ carId);			
				CarWithId carWithId = new CarWithId();
				myRs.next();
				
				carWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
				carWithId.setDescription("description");
				carWithId.setSeats(myRs.getInt("seats"));
				if(myRs.getString("trunkspace") != null)
				{
					carWithId.setTrunkspace(enumSwitcherCar(myRs.getString("trunkspace")));
				}
				else {
					carWithId.setTrunkspace(null);
				}
				
				return carWithId;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		public List<CarWithId> getCarsByUserId(String userId) {
			//funktioniert noch nicht, keine Ausgabe
			try {
				myRs = myStmt.executeQuery("select * from Stop where createdByUserById = "+ userId);
				List<CarWithId> carsWithId = new ArrayList<CarWithId>();
				while (myRs.next()) {
					CarWithId carWithId = new CarWithId();

					carWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
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
		      String query = " insert into Car (createdByUserById, description, seats, trunkspace)"
		        + " values (?, ?, ?, ?)";

		      // create the mysql insert preparedstatement
		      try {
				PreparedStatement preparedStmt = myConn.prepareStatement(query);
				  preparedStmt.setString (1, carWithoutId.getCreatedByUserById());
				  preparedStmt.setString (2, carWithoutId.getDescription());
				  preparedStmt.setInt   (3, carWithoutId.getSeats());
				  if(carWithoutId.getTrunkspace() != null)
				  {
					  preparedStmt.setString	(4, carWithoutId.getTrunkspace().toString());
				  }
				  else {
					  preparedStmt.setString(4, null);
				  }
				  // execute the preparedstatement
				  preparedStmt.execute();
				  myRs = myStmt.executeQuery("SELECT LAST_INSERT_ID();");
				  if(myRs.next())
				  {
					  return getCarById(myRs.getInt("LAST_INSERT_ID()"));
				  }
				  return null;
				  
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
				  preparedStmt.setString (1, carWithoutId.getCreatedByUserById());
				  preparedStmt.setString (2, carWithoutId.getDescription());
				  preparedStmt.setInt   (3, carWithoutId.getSeats());
				  if(carWithoutId.getTrunkspace() != null)
				  {
					  preparedStmt.setString	(4, carWithoutId.getTrunkspace().toString());
				  }
				  else {
					  preparedStmt.setString(4, null);
				  }
				  
				  // execute the preparedstatement
				  preparedStmt.execute();
				  
				  // TODO Car wird noch nicht zurück gegeben weil aufwendig, weiß nicht ob das nötig ist.
				  return getCarById(carId);
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
				reviewWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
				reviewWithId.setCreatedForUserById(myRs.getString("createdForUserById"));
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
		public List<ReviewWithId> getReviewsByUserId(String userId) {
			//Id des Users für den die Reviews gemacht wurden
			try {
				myRs = myStmt.executeQuery("select * from Review where createdForUserById = "+ userId);
				List<ReviewWithId> reviewsWithId = new ArrayList<ReviewWithId>();
				while(myRs.next()) {
					ReviewWithId reviewWithId = new ReviewWithId();
					
					reviewWithId.setComment(myRs.getString("comment"));
					reviewWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
					reviewWithId.setCreatedForUserById(myRs.getString("createdForUserById"));
					reviewWithId.setCreatedOn(myRs.getDate("createdOn"));
					reviewWithId.setRating(myRs.getInt("rating"));
					reviewWithId.setReviewId(myRs.getInt("reviewId"));
					
					reviewsWithId.add(reviewWithId);
				}
				
				return reviewsWithId;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		public ReviewWithId createReview(ReviewWithoutId reviewWithoutId) {
			// the mysql insert statement
			// approved
		      String query = "INSERT INTO `SmartPKW`.`Review` " +  		
		      		"(`rating`," + 
		      		"`comment`," + 
		      		"`createdByUserById`," + 
		      		"`createdForUserById`," + 
		      		"`createdOn`)"
		      		+ " values (?, ?, ?, ?, ?)";

		      // create the mysql insert preparedstatement
		      try {
				PreparedStatement preparedStmt = myConn.prepareStatement(query);
				System.out.println(reviewWithoutId.getRating());
				  preparedStmt.setLong (1, reviewWithoutId.getRating());
				  preparedStmt.setString (2, reviewWithoutId.getComment());
				  preparedStmt.setString   (3, reviewWithoutId.getCreatedByUserById());
				  preparedStmt.setString	(4, reviewWithoutId.getCreatedForUserById());
				  preparedStmt.setDate(5, (Date) reviewWithoutId.getCreatedOn());
				  //createdOn .. automatisch?
				  //System.out.println(preparedStmt);
				  // execute the preparedstatement
				  preparedStmt.execute();
				  myRs = myStmt.executeQuery("SELECT LAST_INSERT_ID();");
				  if(myRs.next())
				  {
					  return getReviewById(myRs.getInt("LAST_INSERT_ID()"));
				  }
				  return null;
				  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		    
		}
		public ReviewWithId updateReview(int reviewId, ReviewWithoutId reviewWithoutId) {
			// the mysql insert statement
		     /* String query = "insert into User where cardId = "+carId+"(createdByUserById, description, seats, trunkspace)"
		        + " values (?, ?, ?, ?)";*/
			
			String query = "UPDATE `SmartPKW`.`Review` " + 
					"SET " + 
					"`rating` = ?, " + 
					"`comment` = ?, " + 
					"`createdByUserById` = ?, " + 
					"`createdForUserById` = ? " + 
					"WHERE reviewId = "+ reviewId;
			System.out.println(query);
		      // create the mysql insert preparedstatement
		      try {
		    	  PreparedStatement preparedStmt = myConn.prepareStatement(query);
				  preparedStmt.setLong (1, reviewWithoutId.getRating());
				  preparedStmt.setString (2, reviewWithoutId.getComment());
				  preparedStmt.setString   (3, reviewWithoutId.getCreatedByUserById());
				  preparedStmt.setString	(4, reviewWithoutId.getCreatedForUserById());
				  
				  // execute the preparedstatement
				  preparedStmt.execute();
				  return getReviewById(reviewId);
				  // TODO Car wird noch nicht zurück gegeben weil aufwendig, weiß nicht ob das nötig ist.
				  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	

		}
		public void deleteReview(int reviewId) {
			try {
				myStmt.executeUpdate("DELETE FROM Review WHERE reviewId = "+reviewId);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	

	
	//--User-- Methods
	public UserWithId updateUser(String userId, UserWithoutId userWithoutId)
	{
		return null;
	}

	
	//--Exists-- Methods
	public boolean carExists(int carId) {
		try {
			myRs = myStmt.executeQuery("select * from Car where carId = "+ carId);
			if(myRs.next() == true)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean reviewExists(int reviewId) {
		try {
			myRs = myStmt.executeQuery("select * from Review where reviewId = "+ reviewId);
			if(myRs.next() == true)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean userExists(String userId) {
		try {
			myRs = myStmt.executeQuery("select * from User where userId = "+ userId);
			if(myRs.next() == true)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean rideExists(int rideId)
	{
		try {
			myRs = myStmt.executeQuery("select * from Ride where rideId = "+ rideId);
			if(myRs.next() == true)
			{
				return true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean stopExists(int stopId)
	{
		try {
			myRs = myStmt.executeQuery("select * from Stop where stopId = "+ stopId);
			if(myRs.next() == true)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public UserWithId getUserById(String userId) {
		// simples getUserById
		return null;
	}

	public List<StopWithId> getAcceptedStops(int rideId) {
		// Alles Stops eines Rides zurückgeben, die Status accepted haben (evtl auf groß/kleinschreibung achten da ENUM)
		return null;
	}

	public boolean hasPrio(int rideId, int stopId) {
		//Prüfen ob bei diesem Stop User einsteigen, die bei einem anderen Stop aussteigen (der nicht der letzte Stop des Rides ist) - Wenn ja dann true sonst false
		return false;
	}

	public boolean hasPrioOver(int stopId, List<Integer> highestPrioIds) {
		//Prüfen ob bei dem Stop User einsteigen, die bei einem der Stops aus der Liste (die Liste entält StopIds) aussteigen, wenn ja dann true sonst false
		return false;
	}

	public void acceptStops(Integer rideId, String userId) {
		//Alle Stops die der User in dem Ride angelegt hat auf accepter setzten (Enum), zuerst alle Stops wo RideID passt und createdByuSerById passt -> setStatus -> dann UPDATE
		
	}

	public void declinceStops(Integer rideId, String userId) {
		//gleiche wie Accept nur das der Status auf declined gesetzt wird
		
	}

	public void addUserToStops(Integer rideId, String userId, Integer startStopId, Integer endStopId) {
		//Der User wird beim Stop mit der Id startStopId als einsteiger und endStopID als Aussteiger eingetragen
		
	}

}



