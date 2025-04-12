package com.company.demo.entity;

import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "CLIENT")
@Entity
public class Client {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    @Column(name = "IMAGE")
    private byte[] image;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "CONTACT_INFORMATION_EMAIL")),
            @AttributeOverride(name = "phone", column = @Column(name = "CONTACT_INFORMATION_PHONE")),
            @AttributeOverride(name = "address", column = @Column(name = "CONTACT_INFORMATION_ADDRESS")),
            @AttributeOverride(name = "url", column = @Column(name = "CONTACT_INFORMATION_URL"))
    })
    private ContactInformation contactInformation;

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}