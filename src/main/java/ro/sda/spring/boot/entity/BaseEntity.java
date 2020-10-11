package ro.sda.spring.boot.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    protected Long id;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdTime;

    @Column(nullable = false, updatable = true)
    protected LocalDateTime lastTimeModified;

    @PrePersist
    private void prePersist() {
        this.createdTime = LocalDateTime.now();
        this.lastTimeModified = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.lastTimeModified = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastTimeModified() {
        return lastTimeModified;
    }

    public void setLastTimeModified(LocalDateTime lastTimeModified) {
        this.lastTimeModified = lastTimeModified;
    }
}
