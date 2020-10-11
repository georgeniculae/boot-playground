package ro.sda.spring.boot.dto;

import java.time.LocalDateTime;

public class BaseDTO {

    protected Long id;

    protected LocalDateTime createdTime;

    protected LocalDateTime lastTimeModified;

    public BaseDTO(Long id, LocalDateTime createdTime, LocalDateTime lastTimeModified) {
        this.id = id;
        this.createdTime = createdTime;
        this.lastTimeModified = lastTimeModified;
    }

    public BaseDTO() {
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
