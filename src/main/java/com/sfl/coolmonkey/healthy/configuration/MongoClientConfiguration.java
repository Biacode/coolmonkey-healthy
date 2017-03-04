package com.sfl.coolmonkey.healthy.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/26/17
 * Time: 3:45 PM
 */
@Configuration
public class MongoClientConfiguration extends AbstractMongoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoClientConfiguration.class);

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private Integer port;

    @Value("${mongodb.database}")
    private String database;

    public MongoClientConfiguration() {
        LOGGER.debug("Initializing");
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }
}
