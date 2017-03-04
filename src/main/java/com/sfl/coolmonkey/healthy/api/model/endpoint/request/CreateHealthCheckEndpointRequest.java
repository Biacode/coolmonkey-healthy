package com.sfl.coolmonkey.healthy.api.model.endpoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/24/17
 * Time: 6:58 PM
 */
@Getter
@Setter
@ToString
public class CreateHealthCheckEndpointRequest implements Serializable {
    private static final long serialVersionUID = 6795464283646957421L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
