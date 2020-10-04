package com.nsisc.emconnector.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * Abstract entity for any key type
 */
@MappedSuperclass
@Data
public abstract class AbstractEntity<K extends Serializable> implements PersistentObject<K> {
    public static final String ID_COLUMN_NAME = "id";

    @Id
    @Column(name = ID_COLUMN_NAME, nullable = false)
    private K id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
