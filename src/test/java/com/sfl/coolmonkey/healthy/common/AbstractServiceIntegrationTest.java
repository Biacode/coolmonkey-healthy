package com.sfl.coolmonkey.healthy.common;

import com.sfl.coolmonkey.healthy.domain.endpoint.HealthCheckEndpoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 3:20 PM
 */
@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractServiceIntegrationTest {

    //region Constants
    private static final Set<Class<?>> collections = new HashSet<>();

    static {
        collections.add(HealthCheckEndpoint.class);
    }
    //endregion

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void before() {
        dropCollections();
    }

    @After
    public void after() {
        dropCollections();
    }

    private void dropCollections() {
        collections.forEach(collectionClazz -> mongoTemplate.dropCollection(collectionClazz));
    }

}
