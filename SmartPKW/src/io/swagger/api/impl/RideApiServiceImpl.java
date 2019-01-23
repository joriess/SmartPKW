package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;
import io.swagger.mysql.DataAccess;

import java.util.Date;
import java.util.List;
import java.util.List;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public class RideApiServiceImpl extends RideApiService {
	
	DataAccess dataAccess = DataAccess.getInstance();
	
    @Override
    public Response createRide(RideWithoutId body, SecurityContext securityContext) throws NotFoundException {
        RideWithId response = dataAccess.createRide(body);
    	if(response != null)
    	{
            return Response.status(Status.CREATED).entity(response).build();
    	}
    	else {
    		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "An error occured")).build();
    	}
    }
    @Override
    public Response createStops(List<StopWithoutId> body, Integer rideId, String userId, SecurityContext securityContext) throws NotFoundException {
        List<StopWithId> response = dataAccess.createStops(body);
    	if(response != null)
    	{
            return Response.status(Status.CREATED).entity(response).build();
    	}
    	else {
    		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "An error occured")).build();
    	}
    }
    @Override
    public Response deleteRide(Integer rideId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.rideExists(rideId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This ride doesnt exist")).build();
        }
        dataAccess.deleteRide(rideId); //404 beachten
    	return Response.status(204).build();
    }
    @Override
    public Response deleteStops(String userId, Integer rideId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.rideExists(rideId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This ride doesnt exist")).build();
        }
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This user doesnt exist")).build();
        }
    	
    	dataAccess.deleteStops(rideId, userId);
    	return Response.status(204).build();
    }
    @Override
    public Response getRideByRideId(Integer rideId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.rideExists(rideId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This ride doesnt exist")).build();
        }
    	RideWithId response = dataAccess.getRideById(rideId);
    	return Response.status(Status.OK).entity(response).build();
    }
    @Override
    public Response getStopsByRideId(Integer rideId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.rideExists(rideId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This ride doesnt exist")).build();
        }
    	List<StopWithId> response = dataAccess.getStopsByRideId(rideId);
    	return Response.status(Status.OK).entity(response).build();
    }
    @Override
    public Response getStopsByRideIdAndUserId(Integer rideId, String userId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This user doesnt exist")).build();
        }
        if(!dataAccess.rideExists(rideId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This ride doesnt exist")).build();
        }
    	List<StopWithId> response = dataAccess.getStopsByRideIdAndUserId(rideId, userId);
    	return Response.status(Status.OK).entity(response).build();
    }
    @Override
    public Response searchRides(String fromAddress, String toAddress,  Date fromTimestamp,  Date toTimestamp, SecurityContext securityContext) throws NotFoundException {
    	//List<RideWithId> response = dataAccess.searchRides(fromAddress, toAddress, fromTimestamp, toTimestamp);
    	//return Response.status(Status.OK).entity(response).build();
    	return null;
    }
    @Override
    public Response updateRide(Integer rideId, RideWithoutId body, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.rideExists(rideId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This ride doesnt exist")).build();
        }
        RideWithId response = dataAccess.updateRide(rideId, body);
        return Response.status(Status.OK).entity(response).build();
    }
    @Override
    public Response updateStops(List<StopWithoutId> body, Integer rideId, String userId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This user doesnt exist")).build();
        }
        if(!dataAccess.rideExists(rideId))
        {
        	return Response.status(Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This ride doesnt exist")).build();
        }
        StopWithId response = dataAccess.updateStop(rideId, userId, body);
        return Response.status(Status.OK).entity(response).build();
    }
	@Override
	public Response acceptStops(Integer rideId, String userId, SecurityContext securityContext)
			throws NotFoundException
	{
		dataAccess.acceptStops(rideId, userId);
		return Response.status(Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK, "Stops accepted")).build();
	}
	@Override
	public Response declineStops(Integer rideId, String userId, SecurityContext securityContext) {
		dataAccess.declinceStops(rideId, userId);
		return Response.status(Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK, "Stops declined")).build();
	}
	@Override
	public Response addUserToStops(Integer rideId, String userId, Integer startStopId, Integer endStopId,
			SecurityContext securityContext) {
		dataAccess.addUserToStops(rideId, userId, startStopId, endStopId);
		return Response.status(Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK, "User added")).build();
	}
}
