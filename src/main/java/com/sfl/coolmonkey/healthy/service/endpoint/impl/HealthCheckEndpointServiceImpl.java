package com.sfl.coolmonkey.healthy.service.endpoint.impl;

import com.sfl.coolmonkey.healthy.domain.endpoint.HealthCheckEndpoint;
import com.sfl.coolmonkey.healthy.repository.endpoint.HealthCheckEndpointRepository;
import com.sfl.coolmonkey.healthy.service.endpoint.HealthCheckEndpointService;
import com.sfl.coolmonkey.healthy.service.expection.ServiceRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 4:31 PM
 */
@Service
public class HealthCheckEndpointServiceImpl implements HealthCheckEndpointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckEndpointServiceImpl.class);

    //region Dependencies
    @Autowired
    private HealthCheckEndpointRepository healthCheckEndpointRepository;
    //endregion

    //region Constructors
    public HealthCheckEndpointServiceImpl() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Override
    public HealthCheckEndpoint create(final String name, final String url) {
        Assert.notNull(name);
        Assert.notNull(url);
        assertHealthCheckEndpointNotExistsForName(name);
        assertHealthCheckEndpointNotExistsForUrl(url);
        return healthCheckEndpointRepository.save(new HealthCheckEndpoint(name, url));
    }

    @Override
    public boolean checkIfExistsForName(final String name) {
        Assert.notNull(name);
        return healthCheckEndpointRepository.findByName(name) != null;
    }

    @Override
    public boolean checkIfExistsForUrl(final String url) {
        Assert.notNull(url);
        return healthCheckEndpointRepository.findByUrl(url) != null;
    }

    @Override
    public Iterable<HealthCheckEndpoint> getAll() {
        return healthCheckEndpointRepository.findAll();
    }
    //endregion

    //region Utility methods
    private void assertHealthCheckEndpointNotExistsForName(final String name) {
        if (checkIfExistsForName(name)) {
            LOGGER.error("The health check endpoint already exists for name - {}", name);
            throw new ServiceRuntimeException("The health check endpoint already exists for name - " + name);
        }
    }

    private void assertHealthCheckEndpointNotExistsForUrl(final String url) {
        if (checkIfExistsForUrl(url)) {
            LOGGER.error("The health check endpoint already exists for url - {}", url);
            throw new ServiceRuntimeException("The health check endpoint already exists for url - " + url);
        }
    }
    //endregion
}
