package com.nsisc.emconnector.model;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * An entity that has integer primary key generated using IDENTITY sequence.
 */
@MappedSuperclass
@Data
public abstract class VersionedEntityWithLongPk extends AbstractEntity<Long> {

    @Embedded
    private VersionedEntity versionedEntity;
}
