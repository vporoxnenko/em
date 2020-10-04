package com.nsisc.emconnector.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Data;

@Embeddable
@Data
public abstract class VersionedEntity {
    public static final String CREATED_COLUMN_NAME = "created_utc";
    public static final String UPDATED_COLUMN_NAME = "updated_utc";
    public static final String VERSION_COLUMN_NAME = "version";

    @Column(name = CREATED_COLUMN_NAME)
    private Instant createdUtc;

    @Column(name = CREATED_COLUMN_NAME)
    private Instant updatedUtc;

    @Version
    @Column(name = VERSION_COLUMN_NAME, nullable = false)
    private int version = 0;
}
