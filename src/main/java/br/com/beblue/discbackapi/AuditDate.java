package br.com.beblue.discbackapi;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class AuditDate {

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public AuditDate() {
    beforePersist();
    afterPersist();
  }

  @PrePersist
  private void beforePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PostUpdate
  private void afterPersist() {
    this.updatedAt = LocalDateTime.now();
  }
}
