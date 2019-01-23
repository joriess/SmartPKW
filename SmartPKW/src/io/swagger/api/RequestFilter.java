package io.swagger.api;

import java.io.IOException;

import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

@Provider
@PreMatching
public class RequestFilter implements ContainerRequestFilter {
     
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
       /* if(!ctx.getMediaType().equals(MediaType.APPLICATION_JSON) && ctx.getMediaType() != null)
        {
            ctx.abortWith(Response.status(Status.NOT_ACCEPTABLE).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "This server only accepts and serves JSON")).build());
        }*/
    }
}
