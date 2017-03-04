package com.sfl.coolmonkey.healthy.service.endpoint;

import com.sfl.coolmonkey.healthy.domain.endpoint.HealthCheckEndpoint;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 4:30 PM
 */
public interface HealthCheckEndpointService {
    HealthCheckEndpoint create(final String name, final String url);

    boolean checkIfExistsForName(final String name);

    boolean checkIfExistsForUrl(final String url);

    Iterable<HealthCheckEndpoint> getAll();
}
