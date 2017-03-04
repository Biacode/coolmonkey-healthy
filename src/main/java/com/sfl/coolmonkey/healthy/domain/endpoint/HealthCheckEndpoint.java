package com.sfl.coolmonkey.healthy.domain.endpoint;

import com.sfl.coolmonkey.healthy.domain.common.AbstractDomainDocument;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 4:19 PM
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Document(collection = "healthCheckEndpoint")
public class HealthCheckEndpoint extends AbstractDomainDocument {
    private static final long serialVersionUID = 6096418646166406003L;

    //region Properties
    @Field("uuid")
    @Indexed(name = "endpoint_uuid_idx", unique = true)
    private String uuid;

    @Field("name")
    @Indexed(unique = true)
    private String name;

    @Field("url")
    @Indexed(unique = true)
    private String url;
    //endregion

    //region Constructors
    public HealthCheckEndpoint() {
        setInstanceDefaultProperties();
    }

    public HealthCheckEndpoint(final String name, final String url) {
        setInstanceDefaultProperties();
        this.name = name;
        this.url = url;
    }

    public HealthCheckEndpoint(final String uuid, final String name, final String url) {
        this.uuid = uuid;
        this.name = name;
        this.url = url;
    }
    //endregion

    //region Utility methods
    private void setInstanceDefaultProperties() {
        this.uuid = UUID.randomUUID().toString();
    }
    //endregion
}
