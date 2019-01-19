package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import java.util.List;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public class RideApiServiceImpl extends RideApiService {
    @Override
    public Response createRide(RideWithoutId body, SecurityContext securityContext) throws NotFoundException {
        RideWithId response = dataAccess.createRide(body);
        return Response.status(201).entity(response).build();
    }
    @Override
    public Response createStops(List<StopWithoutId> body, Integer rideId, Long userId, SecurityContext securityContext) throws NotFoundException {
        List<StopWithId> response = dataAccess.createStops(body);
        return Response.status(201).entity(response).build();
    }
    @Override
    public Response deleteRide(Integer rideId, SecurityContext securityContext) throws NotFoundException {
        dataAccess.deleteRide(rideId); //404 beachten
    	return Response.status(204).build();
    }
    @Override
    public Response deleteStops(Long userId, Integer rideId, SecurityContext securityContext) throws NotFoundException {
        dataAccess.deleteStop(stopId);
    	return Response.status(204).build();
    }
    @Override
    public Response getRideByRideId(Integer rideId, SecurityContext securityContext) throws NotFoundException {
    	RideWithId response = dataAccess.getRideById(rideId);
    	return Response.status(200).entity(response).build();
    }
    @Override
    public Response getStopsByRideId(Integer rideId, SecurityContext securityContext) throws NotFoundException {
    	List<StopWithId> response = dataAccess.getStopsByRideId(rideId);
    	return Response.status(200).entity(response).build();
    }
    @Override
    public Response getStopsByRideIdAndUserId(Integer rideId, Long userId, SecurityContext securityContext) throws NotFoundException {
    	List<StopWithId> response = dataAccess.getStopsByRideIdAndUserId(rideId, userId);
    	return Response.status(200).entity(response).build();
    }
    @Override
    public Response searchRides( String toAddress,  String fromTimestamp,  String toTimestamp, SecurityContext securityContext) throws NotFoundException {
    	List<RideWithId> response = dataAccess.searchRides(toAddress, fromTimestamp, toTimestamp);
    	return Response.status(200).entity(response).build();
    }
    @Override
    public Response updateRide(Integer rideId, RideWithoutId body, SecurityContext securityContext) throws NotFoundException {
        RideWithId response = dataAccess.updateRide(rideId, body);
        return Response.status(200).entity(response).build();
    }
    @Override
    public Response updateStops(List<StopWithoutId> body, Integer rideId, Long userId, SecurityContext securityContext) throws NotFoundException {
        StopWithId response = dataAccess.updateStop(userId, body);
        return Response.status(200).entity(response).build();
    }
}
