package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.CarWithId;
import io.swagger.model.CarWithoutId;
import io.swagger.model.ReviewWithId;
import io.swagger.model.ReviewWithoutId;
import io.swagger.model.RideWithId;
import io.swagger.model.UserWithId;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-05T15:11:31.517Z")
public abstract class UserApiService {
    public abstract Response createCar(String userId,CarWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createReview(Long userId,ReviewWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createUser(UserWithId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteCar(String userId,Integer carId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteReview(Long userId,Integer reviewId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteUser(String userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getCarByUserIdAndCarId(Long userId,Integer carId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getCarsByUserId(String userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getReviewByUserIdAndReviewId(Long userId,Integer reviewId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getReviewsByUserId(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUserByName(String userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response loginUser( @NotNull String userId, @NotNull String password,SecurityContext securityContext) throws NotFoundException;
    public abstract Response logoutUser(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateCar(String userId,Integer carId,CarWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateReview(Long userId,Integer reviewId,ReviewWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateUser(String userId,UserWithId body,SecurityContext securityContext) throws NotFoundException;
}
