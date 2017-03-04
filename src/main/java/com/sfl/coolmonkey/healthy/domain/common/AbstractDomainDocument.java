package com.sfl.coolmonkey.healthy.domain.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/23/17
 * Time: 3:26 PM
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractDomainDocument implements Serializable {
    private static final long serialVersionUID = 5823007055659258430L;

    @Id
    private ObjectId id;

    @Field("created")
    @Indexed
    private Date created;

    @Field("updated")
    @Indexed
    private Date updated;

    @Field("removed")
    @Indexed
    private Date removed;

    protected AbstractDomainDocument() {
        created = new Date();
        updated = ObjectUtils.clone(created);
    }
}
