package com.sfl.coolmonkey.healthy.api.rest.endpoint;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.sfl.coolmonkey.healthy.api.model.endpoint.request.CreateHealthCheckEndpointRequest;
import com.sfl.coolmonkey.healthy.api.model.endpoint.response.CreateHealthCheckEndpointResponse;
import com.sfl.coolmonkey.healthy.service.endpoint.HealthCheckEndpointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/24/17
 * Time: 6:53 PM
 */
@RestController
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        path = "endpoint"
)
public class HealthCheckEndpointResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckEndpointResource.class);

    //region Dependencies
    @Autowired
    private HealthCheckEndpointService healthCheckEndpointService;
    //endregion

    //region Constructors
    public HealthCheckEndpointResource() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @RequestMapping(method = RequestMethod.POST)
    @JacksonFeatures(serializationDisable = SerializationFeature.FAIL_ON_EMPTY_BEANS)
    public Response create(final CreateHealthCheckEndpointRequest request) {
        Assert.notNull(request);
        healthCheckEndpointService.create(request.getName(), request.getUrl());
        return Response.ok(new CreateHealthCheckEndpointResponse()).build();
    }
    //endregion

}
