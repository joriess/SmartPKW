package io.swagger.mysql;

import java.sql.*;
import java.text.DateFormat;
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
	java.text.SimpleDateFormat sdf = 
	new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	//--RIDE-- Methods -------------------------------------------------------------------------------------------------------------
	
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
			RideWithId rideWithId = new RideWithId();
			myRs = myStmt.executeQuery("select * from Ride where rideId = "+rideId);			
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
		String query = "INSERT INTO Ride "
	      		+ "(carById, createdByUserById, createdOn, description, status) "
	      		+ "VALUES (?, ?, ?, ?, ?)";
	      try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			  preparedStmt.setInt (1, rideWithoutId.getCarById());
			  preparedStmt.setString (2, rideWithoutId.getCreatedByUserById());
			  preparedStmt.setDate   (3, (Date) rideWithoutId.getCreatedOn());
			  preparedStmt.setString	(4, rideWithoutId.getDescription());
			  preparedStmt.setString   (5, rideWithoutId.getStatus().toString());
			  
			  // execute the preparedstatement
			  preparedStmt.execute();
			  myRs = myStmt.executeQuery("SELECT LAST_INSERT_ID()");
			  myRs.next();
			  return getRideById(myRs.getInt("LAST_INSERT_ID()"));  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public RideWithId updateRide(int rideId, RideWithoutId rideWithoutId) {
		// the mysql insert statement
	      String query = "UPDATE Ride SET carById = ?, createdByUserById = ?, createdOn = ?, description = ?, status = ? stopWithIdStart = ?, stopWithIdDestination = ? WHERE rideId = "+ rideId;

	      // create the mysql insert preparedstatement
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
		      preparedStmt.setInt (1, rideWithoutId.getCarById());
		      preparedStmt.setString (2, rideWithoutId.getCreatedByUserById());
		      preparedStmt.setDate   (3, (Date) rideWithoutId.getCreatedOn());
		      preparedStmt.setString	(4, rideWithoutId.getDescription());
		      preparedStmt.setString   (5, rideWithoutId.getStatus().toString());
		      
		      // execute the preparedstatementt
		      //preparedStmt.execute();
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
		List<Integer>designatedRideIds = new ArrayList<Integer>();
		List<RideWithId> compatibleRidesWithId = new ArrayList<RideWithId>();
		String mySQLstartDate = formatter.format(startDate);
		String mySQLendDate = formatter.format(endDate);

		
		try {
			//get the RideId for the startpoint
			String query = "SELECT * FROM Stop"
					+" WHERE address = "+fromAddress
					+" AND timeFrameStart = "+ mySQLstartDate
					+" AND timeFrameEnd = "+mySQLendDate;
			
			while(myRs.next()) {
				//die RideIds, die diesen Stop abfährt
				designatedRideIds.add(myRs.getInt("rideId"));
			}
			
			//seek the the endpoint with the rideId
			for(int i=0; i< designatedRideIds.size(); i++) {
				query = "SELECT * FROM Stop"
						+" WHERE address = "+ toAddress
						+" AND createdForRideById = "+ designatedRideIds.get(i);	
				myRs = myStmt.executeQuery(query);
				
				while (myRs.next()) {
					compatibleRidesWithId.add(getRideById(myRs.getInt("rideId")));
				}
			}
			return compatibleRidesWithId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//--STOP-- Methods ---------------------------------------------------------------------------------------------------------------------
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
			
			stopWithId.setStopId(stopId);
			stopWithId.setAddress(myRs.getString("address"));
			stopWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
			stopWithId.setCreatedForRideById(myRs.getInt("createdForRideById"));
			stopWithId.setTimeFrameStart(myRs.getDate("timeFrameStart"));
			stopWithId.setTimeFrameEnd(myRs.getDate("timeFrameEnd"));
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
			for(StopWithoutId stop:stopsWithoutId) {
				String query = "INSERT INTO `SmartPKW`.`Stop`" + 
						"(`rank`," + 
						"`createdByUserById`," + 
						"`address`," + 
						"`latitude`," + 
						"`longitude`," + 
						"`timeFrameStart`," + 
						"`timeFrameEnd`," + 
						"`status`, " + 
						"`createdForRideById`) " + 
						"VALUES " + 
						"(?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				System.out.println("DF.format Date: "+df.format(stop.getTimeFrameEnd()));
				System.out.println("SQL.Date: "+new java.sql.Date (stop.getTimeFrameStart().getTime()));
				
				PreparedStatement preparedStmt = myConn.prepareStatement(query);			
				preparedStmt.setInt(1, stop.getRank());
				preparedStmt.setString(2, stop.getCreatedByUserById());
				preparedStmt.setString(3, stop.getAddress());
				preparedStmt.setInt(4, stop.getLatitude());
				preparedStmt.setInt(5, stop.getLongitude());
				preparedStmt.setDate(6,  new java.sql.Date(stop.getTimeFrameStart().getTime()));
				preparedStmt.setDate(7, new java.sql.Date(stop.getTimeFrameEnd().getTime()));
				preparedStmt.setString(8, stop.getStatus().toString());
				preparedStmt.setInt(9, stop.getCreatedForRideById());
				

				preparedStmt.execute();
				
				myRs = myStmt.executeQuery("SELECT LAST_INSERT_ID()");
				myRs.next();
				int lastInsertId = myRs.getInt("LAST_INSERT_ID()");
				createdStops.add(getStopById(lastInsertId));
				
				if (!stop.getStartPointForUserById().isEmpty()) {
					for (int i=0; i<stop.getStartPointForUserById().size();i++) {
						query = "INSERT INTO SmartPKW.StartPointForUser (userId, stopId) " + "VALUES (?, ?)";
						preparedStmt = myConn.prepareStatement(query);
						preparedStmt.setString(1, stop.getStartPointForUserById().get(i));
						preparedStmt.setInt(2, lastInsertId);
						preparedStmt.execute();
						System.out.println("TAG: if , getStartPointForUserById()"); 
					}
				}
				
				if (!stop.getEndPointForUserById().isEmpty()) {
					for (int i = 0; i < stop.getEndPointForUserById().size(); i++) {
						query = "INSERT INTO SmartPKW.EndPointForUser (userId, stopId) " + "VALUES (?, ?)";
						preparedStmt = myConn.prepareStatement(query);
						preparedStmt.setString(1, stop.getEndPointForUserById().get(i));
						preparedStmt.setInt(2, lastInsertId);
						preparedStmt.execute();
					}
				}
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
		//noch nicht implementiert
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
			default:
				return CarWithId.TrunkspaceEnum.MEDIUM;
			}
		}
		
		
		public CarWithId getCarById(int carId) {
			try {
				myRs = myStmt.executeQuery("SELECT * FROM Car WHERE carId = "+ carId);			
				CarWithId carWithId = new CarWithId();
				myRs.next();
				
				carWithId.setCarId(carId);
				carWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
				carWithId.setDescription(myRs.getString("description"));
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
				List<CarWithId> carsWithId = new ArrayList<CarWithId>();
				myRs = myStmt.executeQuery("SELECT * FROM Car "
						+ "WHERE createdByUserById = "+ userId);

				while (myRs.next()) {
					CarWithId carWithId = new CarWithId();
					carsWithId.add(getCarById(myRs.getInt("carId")));
					System.out.println("in while Schleife: "+carWithId);
				}
				System.out.println(carsWithId);
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
			String query = "UPDATE Car SET "
						+ "createdByUserById = ?, "
						+ "description = ?, "
						+ "seats = ?, "
						+ "trunkspace = ? "
						+ "WHERE carId ="+ carId;

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
				
				reviewWithId.setReviewId(reviewId);
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
					
					reviewWithId = getReviewById(myRs.getInt("reviewId"));
					/*reviewWithId.setComment(myRs.getString("comment"));
					reviewWithId.setCreatedByUserById(myRs.getString("createdByUserById"));
					reviewWithId.setCreatedForUserById(myRs.getString("createdForUserById"));
					reviewWithId.setCreatedOn(myRs.getDate("createdOn"));
					reviewWithId.setRating(myRs.getInt("rating"));
					reviewWithId.setReviewId(myRs.getInt("reviewId"));*/
					
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
		      		"`createdForUserById`)"
		      		+ " values (?, ?, ?, ?)";

		      // create the mysql insert preparedstatement
		      try {
				PreparedStatement preparedStmt = myConn.prepareStatement(query);
				System.out.println(reviewWithoutId.getRating());
				  preparedStmt.setLong (1, reviewWithoutId.getRating());
				  preparedStmt.setString (2, reviewWithoutId.getComment());
				  preparedStmt.setString   (3, reviewWithoutId.getCreatedByUserById());
				  preparedStmt.setString	(4, reviewWithoutId.getCreatedForUserById());
				  //preparedStmt.setDate(5, new java.sql.Date(reviewWithoutId.getCreatedOn().getTime()));
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

	

	
	//--USER-- Methods ------------------------------------------------------------------------------------------------------------------------------------------------
	/*public UserWithId createUser (UserWithoutId userWithoutId) {
		String query = "INSERT INTO `SmartPKW`.`User` " +  		
	      		"(`userName`," + 
	      		"`accessToken`," + 
	      		"`refreshToken`," + 
	      		" values (?, ?, ?, ?, ?)";

	      // create the mysql insert preparedstatement
	      try {
	    	  PreparedStatement preparedStmt = myConn.prepareStatement(query);
			  preparedStmt.setString (1, userWithoutId.get);
			  preparedStmt.setString (2, reviewWithoutId.getComment());
			  preparedStmt.setString   (3, reviewWithoutId.getCreatedByUserById());
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
		
	}*/
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
			// TODO Auto-generated catch block
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
	
	 // EXTRA FUNKTIONEN --------------------------------------------------------------------------------------------------------------------------------------------
	public void createUser(String userId, String userName, String accessToken) {
		try {
			String query = "INSERT INTO User (userId, userName, accessToken) VALUES (?, ?, ?)";
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			preparedStmt.setString(1, userId);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, accessToken);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public UserWithId getUserById(String userId) {
		try {
			String query = "SELECT * FROM User WHERE userId = "+userId;
			myRs = myStmt.executeQuery(query);
			
			UserWithId userWithId = new UserWithId();
			
			userWithId.setUserId(userId);
			userWithId.setDisplayName(myRs.getString("userName"));
			return userWithId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<StopWithId> getAcceptedStops(int rideId) {
		try {
			String query = "SELECT * FROM Stop WHERE createdForRideById = "+rideId+" AND status = 'ACCEPTED'";
			myRs = myStmt.executeQuery(query);
			List<StopWithId> stopsWithId = new ArrayList<StopWithId>();
			
			while(myRs.next()) {
				StopWithId stopWithId = new StopWithId();
				
				stopWithId = getStopById(myRs.getInt("stopId"));
				stopsWithId.add(stopWithId);
			}
			
			
			// Alles Stops eines Rides zurückgeben, die Status accepted haben (evtl auf groß/kleinschreibung achten da ENUM)
			return stopsWithId;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean hasPrio(int rideId, int stopId) {
		//Prüfen ob bei diesem Stop User einsteigen, die bei einem anderen Stop aussteigen  - Wenn ja dann true sonst false
		
		
		
		return false;
	}

	public boolean hasPrioOver(int stopId, List<Integer> highestPrioIds) {
		//Prüfen ob bei dem Stop User einsteigen, die bei einem der Stops aus der Liste (die Liste entält StopIds) aussteigen, wenn ja dann true sonst false
		try {
			//checken welche User bei dieser stopId einsteigen
			String query = "SELECT * FROM StartPointForUser WHERE stopId = "+ stopId;
			List<String> usersToGetIn = new ArrayList<String>();
			List<String> usersToGetOut = new ArrayList<String>();
			List<Integer> rideIds = new ArrayList<Integer>();
			myRs = myStmt.executeQuery(query);
				//diese user in array stopfen
			while(myRs.next()) {
				usersToGetIn.add(myRs.getString("userId"));
			}
			
			//schauen welche stopIds (aus liste) mit EndPointForUser verknüpft sind und dessen UserIds in usersToGetOut speichern
			for(int i = 0; i<highestPrioIds.size() ;i++) {
				query = "SELECT * FROM EndPointForUser WHERE stopId = "+ highestPrioIds.get(i);
				myRs = myStmt.executeQuery(query);
				while (myRs.next() ) {
					for (int j =0;j<usersToGetIn.size();j++) {
						if (myRs.getString("userId") == usersToGetIn.get(j)) {
							return true;
						}
					}
					
					
				}
				
			}
			
			
			/*for (String userToGetIn:usersToGetIn) {
				for(int highestPrioId:highestPrioIds) {
					if(Integer.parseInt(userToGetIn) == highestPrioId) {
						return true;
					}
				}
			}*/
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
	}

	public void acceptStops(Integer rideId, String userId) {
		//Alle Stops die der User in dem Ride angelegt hat auf accepter setzten (Enum), zuerst alle Stops wo RideID passt und createdByuSerById passt -> setStatus -> dann UPDATE
		try {
			String query = "UPDATE SmartPKW.Stop SET status = 'ACCEPTED' WHERE createdForRideById = "+rideId+"createdByUserById = "+userId;
			myStmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void declinceStops(Integer rideId, String userId) {
		//Alle Stops die der User in dem Ride angelegt hat auf accepter setzten (Enum), zuerst alle Stops wo RideID passt und createdByuSerById passt -> setStatus -> dann UPDATE
				try {
					String query = "UPDATE SmartPKW.Stop SET status = 'DECLINED' WHERE createdForRideById = "+rideId+"createdByUserById = "+userId;
					myStmt.executeUpdate(query);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		//gleiche wie Accept nur das der Status auf declined gesetzt wird
	}

	public void addUserToStops(Integer rideId, String userId, Integer startStopId, Integer endStopId) {
		//Josua: Der User wird beim Stop mit der Id startStopId als einsteiger und endStopID als Aussteiger eingetragen
		//Meinst du hier die Tables "StartPointForUser" & "EndPointForUser"? Wenn ja, dann müsste ich die RideId zusätzlich eintragen, 
		//denn die existiert in den Tables noch nicht!
		
		try {
			//startpoint insert
			String query = "INSERT INTO StartPointForUser (userId, stopId) VALUES (?, ?)";
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			preparedStmt.setString(1, userId);
			preparedStmt.setInt(2, startStopId);
			preparedStmt.execute();
			
			//endpoint insert
			query = "INSERT INTO EndPointForUser (userId, stopId) VALUES (?, ?)";
			preparedStmt = myConn.prepareStatement(query);
			preparedStmt.setString(1, userId);
			preparedStmt.setInt(2, endStopId);
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRank(Integer integer, int i) {
		// TODO Auto-generated method stub
		
	}

}






