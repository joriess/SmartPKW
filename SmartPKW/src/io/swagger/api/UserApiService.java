package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.CarWithId;
import io.swagger.model.CarWithoutId;
import io.swagger.model.ReviewWithId;
import io.swagger.model.ReviewWithoutId;
import io.swagger.model.UserWithId;
import io.swagger.model.UserWithoutId;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public abstract class UserApiService {
    public abstract Response createCar(Long userId,CarWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createReview(Long userId,ReviewWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createUser(UserWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteCar(Long userId,Integer carId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteReview(Long userId,Integer reviewId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteUser(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getCarByCarId(Integer carId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getCarsByUserId(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getReviewByReviewId(Integer reviewId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getReviewsByUserId(Long userId,Integer reviewId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUserByName(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response loginUser( @NotNull Long userId, @NotNull String password,SecurityContext securityContext) throws NotFoundException;
    public abstract Response logoutUser(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateCar(Long userId,Integer carId,CarWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateReview(Long userId,Integer reviewId,ReviewWithoutId body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateUser(Long userId,UserWithId body,SecurityContext securityContext) throws NotFoundException;
}
