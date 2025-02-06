package com.company.demo.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "NEW_ENTITY", indexes = {
        @Index(name = "IDX_NEW_ENTITY_CREATOR", columnList = "CREATOR_ID")
})
@Entity
public class NewEntity {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "CREATOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SECOND_FIELD")
    private String secondField;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getSecondField() {
        return secondField;
    }

    public void setSecondField(String secondField) {
        this.secondField = secondField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"creator", "name"})
    public String getInstanceName(MetadataTools metadataTools) {
        return String.format("%s with %s",
                metadataTools.format(creator),
                metadataTools.format(name));
    }
}