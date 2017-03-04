package com.sfl.coolmonkey.healthy.configuration;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.easymock.PowerMock.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/26/17
 * Time: 3:55 PM
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MongoClientConfiguration.class)
public class MongoClientConfigurationTest {

    //region Constants
    private static final String HOST = "localhost";

    private static final Integer PORT = 27017;

    private static final String DATABASE = "healthy";
    //endregion

    //region Test subject and mocks
    private final MongoClientConfiguration mongoClientConfiguration = new MongoClientConfiguration();
    //endregion

    //region Test callbacks
    @Before
    public void before() {
        ReflectionTestUtils.setField(mongoClientConfiguration, "host", HOST);
        ReflectionTestUtils.setField(mongoClientConfiguration, "port", PORT);
        ReflectionTestUtils.setField(mongoClientConfiguration, "database", DATABASE);
    }
    //endregion

    //region Constructors
    public MongoClientConfigurationTest() {
    }
    //endregion

    //region Test methods

    @Test
    public void testGetDatabaseName() {
        // test data
        // expectations
        final String databaseName = mongoClientConfiguration.getDatabaseName();
        assertNotNull(databaseName);
        assertEquals(DATABASE, databaseName);
    }

    @Test
    public void testMongo() throws Exception {
        resetAll();
        // test data
        final MongoClient mongoClient = createMock(MongoClient.class);
        final MongoDatabase mongoDatabase = createMock(MongoDatabase.class);
        // expectations
        expectNew(MongoClient.class, HOST, PORT).andReturn(mongoClient);
        expect(mongoClient.getAddress()).andReturn(new ServerAddress(HOST)).times(2);
        expect(mongoClient.getDatabase(DATABASE)).andReturn(mongoDatabase);
        expect(mongoDatabase.getName()).andReturn(DATABASE);
        replayAll();
        final MongoClient mongo = (MongoClient) mongoClientConfiguration.mongo();
        assertNotNull(mongo);
        assertEquals(HOST, mongo.getAddress().getHost());
        assertEquals(PORT, Integer.valueOf(mongo.getAddress().getPort()));
        assertEquals(DATABASE, mongo.getDatabase(DATABASE).getName());
        verifyAll();
    }
    //endregion
}