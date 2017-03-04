package com.sfl.coolmonkey.healthy.service.endpoint.impl;

import com.sfl.coolmonkey.healthy.common.AbstractServiceUnitTest;
import com.sfl.coolmonkey.healthy.domain.endpoint.HealthCheckEndpoint;
import com.sfl.coolmonkey.healthy.repository.endpoint.HealthCheckEndpointRepository;
import com.sfl.coolmonkey.healthy.service.endpoint.HealthCheckEndpointService;
import com.sfl.coolmonkey.healthy.service.expection.ServiceRuntimeException;
import lombok.val;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 4:31 PM
 */
public class HealthCheckEndpointServiceImplTest extends AbstractServiceUnitTest {

    //region Test subject and mocks
    @TestSubject
    private final HealthCheckEndpointService healthCheckEndpointService = new HealthCheckEndpointServiceImpl();

    @Mock
    private HealthCheckEndpointRepository healthCheckEndpointRepository;
    //endregion

    //region Test methods

    //region create

    /**
     * With invalid arguments
     */
    @Test
    public void testCreate1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            healthCheckEndpointService.create(null, UUID.randomUUID().toString());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            healthCheckEndpointService.create(UUID.randomUUID().toString(), null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * When exists for name
     */
    @Test
    public void testCreate2() {
        resetAll();
        // test data
        val name = UUID.randomUUID().toString();
        val url = UUID.randomUUID().toString();
        val healthCheckEndpoint = createHealthCheckEndpoint(name, url);
        // expectations
        expect(healthCheckEndpointRepository.findByName(name)).andReturn(healthCheckEndpoint);
        replayAll();
        try {
            healthCheckEndpointService.create(name, url);
            fail();
        } catch (final ServiceRuntimeException ignore) {
        }
        verifyAll();
    }

    /**
     * When exists for url
     */
    @Test
    public void testCreate3() {
        resetAll();
        // test data
        val name = UUID.randomUUID().toString();
        val url = UUID.randomUUID().toString();
        val healthCheckEndpoint = createHealthCheckEndpoint(name, url);
        // expectations
        expect(healthCheckEndpointRepository.findByName(name)).andReturn(null);
        expect(healthCheckEndpointRepository.findByUrl(url)).andReturn(healthCheckEndpoint);
        replayAll();
        try {
            healthCheckEndpointService.create(name, url);
            fail();
        } catch (final ServiceRuntimeException ignore) {
        }
        verifyAll();
    }

    /**
     * General scenario
     */
    @Test
    public void testCreate4() {
        resetAll();
        // test data
        val name = UUID.randomUUID().toString();
        val url = UUID.randomUUID().toString();
        // expectations
        expect(healthCheckEndpointRepository.findByName(name)).andReturn(null);
        expect(healthCheckEndpointRepository.findByUrl(url)).andReturn(null);
        expect(healthCheckEndpointRepository.save(isA(HealthCheckEndpoint.class))).andAnswer(() -> (HealthCheckEndpoint) getCurrentArguments()[0]);
        replayAll();
        val result = healthCheckEndpointService.create(name, url);
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(url, result.getUrl());
        verifyAll();
    }
    //endregion

    //region checkIfExistsForName

    /**
     * With invalid arguments
     */
    @Test
    public void testCheckIfExistsForName1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            healthCheckEndpointService.checkIfExistsForName(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * When exists
     */
    @Test
    public void testCheckIfExistsForName2() {
        resetAll();
        // test data
        val name = UUID.randomUUID().toString();
        val healthCheckEndpoint = createHealthCheckEndpoint(name, UUID.randomUUID().toString());
        // expectations
        expect(healthCheckEndpointRepository.findByName(name)).andReturn(healthCheckEndpoint);
        replayAll();
        assertTrue(healthCheckEndpointService.checkIfExistsForName(name));
        verifyAll();
    }

    /**
     * When not exists
     */
    @Test
    public void testCheckIfExistsForName3() {
        resetAll();
        // test data
        val name = UUID.randomUUID().toString();
        createHealthCheckEndpoint(name, UUID.randomUUID().toString());
        // expectations
        expect(healthCheckEndpointRepository.findByName(name)).andReturn(null);
        replayAll();
        assertFalse(healthCheckEndpointService.checkIfExistsForName(name));
        verifyAll();
    }
    //endregion

    //region checkIfExistsForUrl

    /**
     * With invalid arguments
     */
    @Test
    public void testCheckIfExistsForUrl1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            healthCheckEndpointService.checkIfExistsForUrl(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * When exists
     */
    @Test
    public void testCheckIfExistsForUrl2() {
        resetAll();
        // test data
        val url = UUID.randomUUID().toString();
        val healthCheckEndpoint = createHealthCheckEndpoint(UUID.randomUUID().toString(), url);
        // expectations
        expect(healthCheckEndpointRepository.findByUrl(url)).andReturn(healthCheckEndpoint);
        replayAll();
        assertTrue(healthCheckEndpointService.checkIfExistsForUrl(url));
        verifyAll();
    }

    /**
     * When not exists
     */
    @Test
    public void testCheckIfExistsForUrl3() {
        resetAll();
        // test data
        val url = UUID.randomUUID().toString();
        createHealthCheckEndpoint(UUID.randomUUID().toString(), url);
        // expectations
        expect(healthCheckEndpointRepository.findByUrl(url)).andReturn(null);
        replayAll();
        assertFalse(healthCheckEndpointService.checkIfExistsForUrl(url));
        verifyAll();
    }
    //endregion

    //region getAll
    @Test
    public void testGetAll() {
        resetAll();
        // test data
        val healthCheckEndpoints = Collections.singletonList(
                createHealthCheckEndpoint(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );
        // expectations
        expect(healthCheckEndpointRepository.findAll()).andReturn(healthCheckEndpoints);
        replayAll();
        val result = healthCheckEndpointService.getAll();
        assertNotNull(result);
        assertEquals(healthCheckEndpoints, result);
        verifyAll();
    }
    //endregion

    //endregion

    //region Utility methods
    private HealthCheckEndpoint createHealthCheckEndpoint(final String name, final String url) {
        return new HealthCheckEndpoint(name, url);
    }
    //endregion
}