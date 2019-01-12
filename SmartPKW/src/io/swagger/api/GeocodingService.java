package io.swagger.api;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

@Path("test")
public class GeocodingService {
	
	private final String API_KEY = "AIzaSyAGk3rOXKAWocXRHGkqynuUVcvX2slmZT8";
	
	
	@GET
    @Produces({ "application/json" })
	public String testit() throws ApiException, InterruptedException, IOException
	{
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
			GeocodingResult[] results =  GeocodingApi.geocode(context,
			    "Roonstraße 8, 95615 Marktredwitz").await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			return gson.toJson(results[0].addressComponents);
		
	}
}
