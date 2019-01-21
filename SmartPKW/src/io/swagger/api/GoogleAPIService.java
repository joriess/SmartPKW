package io.swagger.api;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GaeRequestHandler;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;

import io.swagger.model.StopWithId;
import io.swagger.mysql.DataAccess;

@Path("test")
public class GoogleAPIService {
	
	private static GoogleAPIService instance = null;
	private GeoApiContext context = null;
	private final String API_KEY = "AIzaSyAGk3rOXKAWocXRHGkqynuUVcvX2slmZT8";
	
	DataAccess dataAccess = DataAccess.getInstance();
	
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
			return gson.toJson(results[0].geometry.toString());
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
		return gson.toJson(results.routes.toString());
	}
	
	public String distance(String[] lat, String[] lng)
	{
		getContext();
		DistanceMatrix results;
		try {
			results = DistanceMatrixApi.getDistanceMatrix(context,
			    lat, lng)
					.await();
		} catch (ApiException | InterruptedException | IOException e) {
			results = null;
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		context.shutdown();
		return gson.toJson(results.rows.toString());
	}
	
	public void optimizeRide(int rideId)
	{	
		Boolean prioChanged = true; //Prüfvariable ob sich noch Prioritäten ändern (um endlossschleifen zu vermeiden wenn zwei Stops zusammen auf der höchsten Prio sind aber keine Beziehun zu einander haben)
		List<StopWithId> stops = dataAccess.getAcceptedStops(rideId);
		int size = stops.size();
		StopWithId start = stops.remove(0);
		StopWithId end = stops.remove(stops.size()-1);
		//2dimensionales Array um stopId (Spalte 1) mit prio zu verbinden
		int[][] prioMap = new int[stops.size()][2];
		
		for(int i = 0; i < stops.size(); i++)
		{
			prioMap[i][1] = stops.remove(0).getStopId();
			//prüfe ob user die bei diesem ride einsteigen, bei einem anderen aussteigen (das bedeutet, das dieser vor dem anderen sein muss)
			
			if(dataAccess.hasPrio(rideId, prioMap[i][1]))//Wenn ja erhöhe eine temporäre variable
			{
				prioMap[i][2] = prioMap[i][2] + 1;
			}
			
			
		}
		// wenn 2 stops gleichzeitig den höchsten Wert bei der prio haben
		while(isNotSorted(prioMap) && prioChanged)
		{
			//Dann nur vergleichen ob die Stops der höchsten Prio hasPrio übereinander haben, wenn nein ist die Prio Fertig, wenn ja
		}
		
	}
	//ACHTUNG Stops können zeitgleich auf der höchsten Prio sein, Lösung benötigt um in keinen Loop zu kommen
	public Boolean isNotSorted(int[][] prioMap)
	{
		int highest = 0;
		int counter = 0;
		for(int i = 0; i < prioMap.length; i++)
		{
			if(prioMap[i][2] > highest)
			{
				highest = prioMap[i][2];
			}
		}
		for(int i = 0; i < prioMap.length; i++)
		{
			if(prioMap[i][2] == highest)
			{
				counter++;
			}
		}
		if(counter > 1)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
}
