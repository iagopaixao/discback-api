package br.com.beblue.discbackapi.sale.domain;

import br.com.beblue.discbackapi.AuditDate;
import br.com.beblue.discbackapi.disc.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.DayOfWeek;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashBack {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(unique = true, nullable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DayOfWeek day;

  @ManyToOne
  private Genre genre;

  @Column(nullable = false)
  private BigDecimal percentage;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
