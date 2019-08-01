package br.com.beblue.discbackapi.sale.domain;

import br.com.beblue.discbackapi.AuditDate;
import br.com.beblue.discbackapi.disc.domain.Disc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.CascadeType.ALL;

@Data
@Table(name = "sale_item")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @OneToOne(cascade = ALL)
  private Disc disc;

  @ManyToOne(cascade = ALL)
  private Sale sale;

  private BigDecimal value;

  private BigDecimal quantity;

  private BigDecimal cashBack;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
