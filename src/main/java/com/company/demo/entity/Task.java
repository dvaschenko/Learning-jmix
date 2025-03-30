package com.company.demo.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "TASK_", indexes = {
        @Index(name = "IDX_TASK__PROJECT", columnList = "PROJECT_ID"),
        @Index(name = "IDX_TASK__AUTHOR", columnList = "AUTHOR_ID"),
        @Index(name = "IDX_TASK__PEFORMER", columnList = "PEFORMER_ID"),
        @Index(name = "IDX_TASK__CONTROLLER", columnList = "CONTROLLER_ID")
})
@Entity(name = "Task_")
public class Task {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @JoinColumn(name = "AUTHOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @JoinColumn(name = "PEFORMER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User peformer;
    @JoinColumn(name = "CONTROLLER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User controller;
    @Column(name = "STATUS")
    private String status;
    @InstanceName
    @Column(name = "NAME")
    private String name;
    @Column(name = "EFFORTS")
    private Integer efforts;

    @NotNull
    @JoinColumn(name = "PROJECT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Project project;

    public User getController() {
        return controller;
    }

    public void setController(User controller) {
        this.controller = controller;
    }

    public User getPeformer() {
        return peformer;
    }

    public void setPeformer(User peformer) {
        this.peformer = peformer;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public TaskStatus getStatus() {
        return status == null ? null : TaskStatus.fromId(status);
    }

    public void setStatus(TaskStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEfforts(Integer efforts) {
        this.efforts = efforts;
    }

    public Integer getEfforts() {
        return efforts;
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

    @JmixProperty
    public String getConcat() {
        return this.name + this.efforts;
    }
}