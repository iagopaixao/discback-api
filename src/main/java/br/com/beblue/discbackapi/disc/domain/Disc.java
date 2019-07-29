package br.com.beblue.discbackapi.disc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car", schema = "public")
public class Disc {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Embedded @Builder.Default() private AuditLog auditLog = new AuditLog();
}
