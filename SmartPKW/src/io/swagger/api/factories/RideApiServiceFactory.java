package io.swagger.api.factories;

import io.swagger.api.RideApiService;
import io.swagger.api.impl.RideApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public class RideApiServiceFactory {
    private final static RideApiService service = new RideApiServiceImpl();

    public static RideApiService getRideApi() {
        return service;
    }
}
