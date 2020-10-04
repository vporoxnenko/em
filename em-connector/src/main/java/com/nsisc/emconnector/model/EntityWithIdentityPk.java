package com.nsisc.emconnector.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * An entity that has integer primary key generated using IDENTITY sequence.
 */
@MappedSuperclass
@Data
public abstract class EntityWithIdentityPk implements PersistentObject<Long> {
    public static final String ID_COLUMN_NAME = "id";

    @Id
    @Column(name = ID_COLUMN_NAME, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityWithIdentityPk that = (EntityWithIdentityPk) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
