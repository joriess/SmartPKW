package io.swagger.api;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import io.swagger.mysql.DataAccess;

@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {
	DataAccess dataAccess = DataAccess.getInstance();
	@Override
	public void filter(ContainerRequestContext crc) {
		/*
		String authCredentials = crc.getHeaderString("Authorization");

		//prüfen ob dieser Token einem User zugeordnet ist
		if (authCredentials == null) {
			crc.abortWith(Response.status(Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "A valid token is required in the Authorization Header")).build());
		}
		
		if (!dataAccess.authTokenExists(authCredentials)) {
			crc.abortWith(Response.status(Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "A valid token is required in the Authorization Header")).build());
		}
		*/
	}
}

