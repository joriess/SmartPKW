package io.swagger.api.factories;

import io.swagger.api.RideApiService;
import io.swagger.api.impl.RideApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-05T15:11:31.517Z")
public class RideApiServiceFactory {
    private final static RideApiService service = new RideApiServiceImpl();

    public static RideApiService getRideApi() {
        return service;
    }
}
