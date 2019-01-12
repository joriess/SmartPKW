package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UserApiService;
import io.swagger.api.factories.UserApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.CarWithId;
import io.swagger.model.CarWithoutId;
import io.swagger.model.ReviewWithId;
import io.swagger.model.ReviewWithoutId;
import io.swagger.model.UserWithId;
import io.swagger.model.UserWithoutId;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/user")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the user API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public class UserApi  {
   private final UserApiService delegate;

   public UserApi(@Context ServletConfig servletContext) {
      UserApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UserApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UserApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UserApiServiceFactory.getUserApi();
      }

      this.delegate = delegate;
   }

    @POST
    @Path("/{userId}/car")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create car", notes = "This can only be dony by the logged in user", response = CarWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "car", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "created successfully", response = CarWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response createCar(@ApiParam(value = "The id of the user whichs cars needs to be fetched.",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "Created car object" ,required=true) CarWithoutId body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createCar(userId,body,securityContext);
    }
    @POST
    @Path("/{userId}/review")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create a new review for a user", notes = "", response = ReviewWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "review", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "review created successfully", response = ReviewWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response createReview(@ApiParam(value = "",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "" ,required=true) ReviewWithoutId body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createReview(userId,body,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create user", notes = "This can only be done by the logged in user.", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "user created successfully", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response createUser(@ApiParam(value = "Created user object" ,required=true) UserWithoutId body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createUser(body,securityContext);
    }
    @DELETE
    @Path("/{userId}/car/{carId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete a car", notes = "This can only be dony by the logged in user", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "car", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "car deleted", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "car or user not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response deleteCar(@ApiParam(value = "The name of the user whichs cars needs to be fetched.",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "The id of the car that needs to be deleted",required=true) @PathParam("carId") Integer carId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteCar(userId,carId,securityContext);
    }
    @DELETE
    @Path("/{userId}/review/{reviewId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete a certain review for a user", notes = "This can only be done by the user that created the review.", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "review", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "review deleted", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "review not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response deleteReview(@ApiParam(value = "The id of the user whichs review need to be updated",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "The id of the review that needs to be updated",required=true) @PathParam("reviewId") Integer reviewId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteReview(userId,reviewId,securityContext);
    }
    @DELETE
    @Path("/{userId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "user not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response deleteUser(@ApiParam(value = "The name that needs to be deleted",required=true) @PathParam("userId") Long userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteUser(userId,securityContext);
    }
    @GET
    @Path("/{userId}/car/{carId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets a car by it's carId and a userId", notes = "", response = CarWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "car", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = CarWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "car not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response getCarByUserIdAndCarId(@ApiParam(value = "The id of the user whichs cars need to be fetched.",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "The id of the car that needs to be fetched",required=true) @PathParam("carId") Integer carId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getCarByUserIdAndCarId(userId,carId,securityContext);
    }
    @GET
    @Path("/{userId}/car")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get all cars of a certain user", notes = "", response = CarWithId.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "car", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = CarWithId.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "car not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response getCarsByUserId(@ApiParam(value = "The name of the user whichs cars needs to be fetched.",required=true) @PathParam("userId") String userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getCarsByUserId(userId,securityContext);
    }
    @GET
    @Path("/{userId}/review/{reviewId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get a certain review for a user", notes = "", response = ReviewWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "review", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = ReviewWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "review not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response getReviewByUserIdAndReviewId(@ApiParam(value = "The id of the user whichs review need to be fetched.",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "The id of the review that needs to be fetched",required=true) @PathParam("reviewId") Integer reviewId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getReviewByUserIdAndReviewId(userId,reviewId,securityContext);
    }
    @GET
    @Path("/{userId}/review")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get all reviews for a user", notes = "", response = ReviewWithId.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "review", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = ReviewWithId.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "review not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response getReviewsByUserId(@ApiParam(value = "The id of the user whichs review need to be fetched.",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "The id of the review that needs to be fetched",required=true) @PathParam("reviewId") Integer reviewId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getReviewsByUserId(userId,reviewId,securityContext);
    }
    @GET
    @Path("/{userId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get user by user name", notes = "", response = UserWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = UserWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "user not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response getUserByName(@ApiParam(value = "The id that needs to be fetched",required=true) @PathParam("userId") Long userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getUserByName(userId,securityContext);
    }
    @GET
    @Path("/login")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs user into the system", notes = "", response = UserWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = UserWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "user not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response loginUser(@ApiParam(value = "The user name for login",required=true) @QueryParam("userId") Long userId
,@ApiParam(value = "The password for login in clear text",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.loginUser(userId,password,securityContext);
    }
    @GET
    @Path("/logout")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs out current logged in user session", notes = "", response = UserWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = UserWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "user not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response logoutUser(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.logoutUser(securityContext);
    }
    @PUT
    @Path("/{userId}/car/{carId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update a car", notes = "This can only be dony by the logged in user", response = CarWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "car", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "car updated", response = CarWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "car not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response updateCar(@ApiParam(value = "The name of the user whichs cars needs to be fetched.",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "id that needs to be updated",required=true) @PathParam("carId") Integer carId
,@ApiParam(value = "Updated car object" ,required=true) CarWithoutId body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateCar(userId,carId,body,securityContext);
    }
    @PUT
    @Path("/{userId}/review/{reviewId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update a certain review for a user", notes = "This can only be done by the user that created the review.", response = ReviewWithId.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "review", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "review updated", response = ReviewWithId.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "review not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response updateReview(@ApiParam(value = "The id of the user whichs review need to be updated",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "The id of the review that needs to be updated",required=true) @PathParam("reviewId") Integer reviewId
,@ApiParam(value = "Updated review object" ,required=true) ReviewWithoutId body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateReview(userId,reviewId,body,securityContext);
    }
    @PUT
    @Path("/{userId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updated user", notes = "This can only be done by the logged in user.", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "accessCode", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "read:profile", description = "allows reading profile")
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "user updated", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "unauthorized, because you are not logged in", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "user not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "wrong format (only JSON is allowed)", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "edit conflict between request- and server-version", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 413, message = "request entity too long", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "service or depending services unavailable", response = Void.class) })
    public Response updateUser(@ApiParam(value = "name that need to be updated",required=true) @PathParam("userId") Long userId
,@ApiParam(value = "Updated user object" ,required=true) UserWithId body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateUser(userId,body,securityContext);
    }
}
