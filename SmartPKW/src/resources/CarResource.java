package resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("car")
public class CarResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCar()
	{
		try {
			return mySQLDatabase.Driver.getUserName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	@POST
	public String createCar() {
		return "";
	}
}
