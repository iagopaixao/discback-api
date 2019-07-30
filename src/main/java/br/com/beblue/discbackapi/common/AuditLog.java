package br.com.beblue.discbackapi.common;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class AuditLog {

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public AuditLog() {
    setup();
    afterUpdate();
  }

  @PrePersist
  private void setup() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PostUpdate
  private void afterUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
