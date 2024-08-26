package com.ceadar.uidashboard.datamodel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskEntity extends GeneralEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(name = "ended_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endedAt;
    private String name;
    @Column(name = "execution_time")
    private Long executionTime; // in seconds
    private Status status;
    @Column(name = "is_done")
    private boolean isDone;

    public TaskEntity(Long executionTime, Status status, String name, LocalDateTime endedAt, LocalDateTime createdAt) {
        this.executionTime = executionTime;
        this.status = status;
        this.name = name;
        this.endedAt = endedAt;
        this.createdAt = createdAt;
    }

    public TaskEntity() {

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public Long getId(){
        return this.id;
    }

    @JsonGetter("formattedExecutionTime")
    public String getFormattedExecutionTime() {
        long days = executionTime / (24 * 3600);
        long hours = (executionTime % (24 * 3600)) / 3600;
        long minutes = (executionTime % 3600) / 60;
        long seconds = executionTime % 60;
        return String.format("%d-%02d-%02d-%02d", days, hours, minutes, seconds);
    }

}
