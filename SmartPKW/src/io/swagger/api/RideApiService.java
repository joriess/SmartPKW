package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.util.List;
import io.swagger.model.RideWithId;
import io.swagger.model.RideWithoutId;
import io.swagger.model.StopWithId;
import io.swagger.model.StopWithoutId;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public abstract class RideApiService {
    public abstract Response createRide(Integer rideId,RideWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createStops(List<StopWithoutId> body,Integer rideId,Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteRide(Integer rideId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteStops(Long userId,Integer rideId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getRideByRideId(Integer rideId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getStopsByRideId(Integer rideId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getStopsByRideIdAndUserId(Integer rideId,Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response searchRides( String toAddress, String fromTimestamp, String toTimestamp,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateRide(Integer rideId,RideWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateStops(List<StopWithoutId> body,Integer rideId,Long userId,SecurityContext securityContext) throws NotFoundException;
}
