package br.com.beblue.discbackapi.disc.domain;

import br.com.beblue.discbackapi.common.AuditLog;
import br.com.beblue.discbackapi.common.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disc {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  private String name;

  private BigDecimal price;

  private Genre genre;

  @Embedded @Builder.Default() private AuditLog auditLog = new AuditLog();
}
