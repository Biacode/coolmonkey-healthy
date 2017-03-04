package com.sfl.coolmonkey.healthy.repository.endpoint;

import com.sfl.coolmonkey.healthy.domain.endpoint.HealthCheckEndpoint;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 4:28 PM
 */
@Repository
public interface HealthCheckEndpointRepository extends CrudRepository<HealthCheckEndpoint, ObjectId> {
    HealthCheckEndpoint findByName(final String name);

    HealthCheckEndpoint findByUrl(final String url);
}
