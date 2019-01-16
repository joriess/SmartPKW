package io.swagger.api;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GaeRequestHandler;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;

import io.swagger.mysql.Driver;

@Path("test")
public class GoogleAPIService {
	
	private static GoogleAPIService instance = null;
	private GeoApiContext context = null;
	private final String API_KEY = "AIzaSyAGk3rOXKAWocXRHGkqynuUVcvX2slmZT8";
	
	private GoogleAPIService() {}
	
	public static GoogleAPIService getInstance()
	{
		if(instance == null)
		{
			instance = new GoogleAPIService();
		}
		return instance;
	}
	
	private GeoApiContext getContext()
	{
		if(context == null)
		{
			context = new GeoApiContext.Builder()
				    .apiKey(API_KEY)
				    //.requestHandlerBuilder(GaeRequestHandler.Builder.build())
				    .build();
		}
		return context;
	}
	
	public String geocode(String address)
	{
			getContext();
			GeocodingResult[] results;
			try {
				results = GeocodingApi.geocode(context,
				    address).await();
			} catch (ApiException | InterruptedException | IOException e) {
				results = null;
				e.printStackTrace();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			context.shutdown();
			return gson.toJson(results[0].geometry);
	}
	
	public String route(String origin, String destination)
	{
		getContext();
		DirectionsResult results;
		try {
			results = DirectionsApi.getDirections(context,
			    origin, destination)
					.mode(TravelMode.DRIVING)
					.await();
		} catch (ApiException | InterruptedException | IOException e) {
			results = null;
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		context.shutdown();
		return gson.toJson(results.routes);
	}
	
	public String testConnection()
	{
		return Driver.startup();
	}
	
	
}
