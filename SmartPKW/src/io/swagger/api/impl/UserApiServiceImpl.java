package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.CarWithId;
import io.swagger.model.CarWithoutId;
import io.swagger.model.ReviewWithId;
import io.swagger.model.ReviewWithoutId;
import io.swagger.model.UserWithId;
import io.swagger.model.UserWithoutId;
import io.swagger.mysql.DataAccess;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public class UserApiServiceImpl extends UserApiService {
	
	DataAccess dataAccess = DataAccess.getInstance();
	
    @Override
    public Response createCar(String userId, CarWithoutId body, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This user doesnt exist")).build();
        }
    	CarWithId response = dataAccess.createCar(body);
        return Response.status(201).entity(response).build();
    }
    
    @Override
    public Response createReview(String userId, ReviewWithoutId body, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This user doesnt exist")).build();
        }
    	ReviewWithId response = dataAccess.createReview(body);
    	return Response.status(201).entity(response).build();
    }
    
    @Override
    public Response createUser(UserWithoutId body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response deleteCar(String userId, Integer carId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.carExists(carId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This car doesnt exist")).build();
        }
    	
    	dataAccess.deleteCar(carId);
        return Response.status(204).build();
    }
    
    @Override
    public Response deleteReview(String userId, Integer reviewId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.reviewExists(reviewId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This review doesnt exist")).build();
        }
        dataAccess.deleteReview(reviewId);
        return Response.status(204).build();
    }
    
    @Override
    public Response deleteUser(String userId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getCarByCarId(Integer carId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.carExists(carId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This car doesnt exist")).build();
        }
        CarWithId response = dataAccess.getCarById(carId);
        return Response.status(200).entity(response).build();
    }
    
    @Override
    public Response getCarsByUserId(String userId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This user doesnt exist")).build();
        }
        List<CarWithId> response = dataAccess.getCarsByUserId(userId);
        return Response.status(200).entity(response).build();
    }
    
    @Override
    public Response getReviewByReviewId(Integer reviewId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.reviewExists(reviewId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This review doesnt exist")).build();
        }
    	ReviewWithId response = dataAccess.getReviewById(reviewId);
    	return Response.status(200).entity(response).build();
    }
    
    @Override
    public Response getReviewsByUserId(String userId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This user doesnt exist")).build();
        }
    	List<ReviewWithId> response = dataAccess.getReviewsByUserId(userId);
    	return Response.status(200).entity(response).build();
    }
    
    @Override
    public Response getUserById(String userId, SecurityContext securityContext) throws NotFoundException {
        if(!dataAccess.userExists(userId))
        {
        	return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.OK, "This user doesnt exist")).build();
        }
        UserWithId response = dataAccess.getUserById(userId);
        return Response.status(200).entity(response).build();
    }
    
    @Override
    public Response loginUser( @NotNull String userId,  @NotNull String password, SecurityContext securityContext) throws NotFoundException {
        GoogleAPIService googleAPI = GoogleAPIService.getInstance();
        googleAPI.testConnection();
        //String body = googleAPI.route("Roonstraße 8, 95615 Marktredwitz", "Fliederstraße 14, 92637 Weiden");
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "test")).build();
    }
    @Override
    public Response logoutUser(SecurityContext securityContext) throws NotFoundException {
        GoogleAPIService googleAPI = GoogleAPIService.getInstance();
        String body = googleAPI.geocode("Roonstraße 8, 95615 Marktredwitz");
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, body)).build();
    }
    @Override
    public Response updateCar(String userId, Integer carId, CarWithoutId body, SecurityContext securityContext) throws NotFoundException {
        CarWithId response = dataAccess.updateCar(carId, body);
        if(response != null)
        {
        	return Response.status(200).entity(response).build();
        }
        return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "CarId not found")).build();
    }
    @Override
    public Response updateReview(String userId, Integer reviewId, ReviewWithoutId body, SecurityContext securityContext) throws NotFoundException {
        ReviewWithId response = dataAccess.updateReview(reviewId, body);
        if(response != null)
        {
        	return Response.status(200).entity(response).build();
        }
        return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "ReviewId not found")).build();
    }
    @Override
    public Response updateUser(String userId, UserWithoutId body, SecurityContext securityContext) throws NotFoundException {
        UserWithId response = dataAccess.updateUser(userId, body);
        if(response != null)
        {
        	return Response.status(200).entity(response).build();
        }
        return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "UserId not found")).build();
    }
}
