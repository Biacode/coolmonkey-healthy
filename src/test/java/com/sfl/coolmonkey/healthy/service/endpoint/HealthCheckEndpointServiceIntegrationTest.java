package com.sfl.coolmonkey.healthy.service.endpoint;

import com.sfl.coolmonkey.healthy.common.AbstractServiceIntegrationTest;
import com.sfl.coolmonkey.healthy.domain.endpoint.HealthCheckEndpoint;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 4:51 PM
 */
@Component
public class HealthCheckEndpointServiceIntegrationTest extends AbstractServiceIntegrationTest {

    //region Test subject and mocks
    @Autowired
    private HealthCheckEndpointService healthCheckEndpointService;
    //endregion

    //region Test methods

    @Test
    public void testCreate() {
        // given
        val name = "Address MS";
        val url = "http://addressms:8081";
        // when
        val result = healthCheckEndpointService.create(name, url);
        // then
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(url, result.getUrl());
    }

    @Test
    public void testCheckIfExistsForName() {
        // given
        val name = UUID.randomUUID().toString();
        persistHealthCheckEndpoint(name, UUID.randomUUID().toString());
        // when
        // then
        assertTrue(healthCheckEndpointService.checkIfExistsForName(name));
    }

    @Test
    public void testCheckIfExistsForUrl() {
        // given
        val url = UUID.randomUUID().toString();
        persistHealthCheckEndpoint(UUID.randomUUID().toString(), url);
        // when
        // then
        assertTrue(healthCheckEndpointService.checkIfExistsForUrl(url));
    }

    @Test
    public void testGetAll() {
        // given
        val healthCheckEndpoints = Collections.singletonList(
                persistHealthCheckEndpoint(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );
        // when
        val result = healthCheckEndpointService.getAll();
        // then
        assertNotNull(result);
        assertEquals(healthCheckEndpoints, result);
    }
    //endregion

    //region Utility methods
    private HealthCheckEndpoint persistHealthCheckEndpoint(final String name, final String url) {
        return healthCheckEndpointService.create(name, url);
    }
    //endregion

}
