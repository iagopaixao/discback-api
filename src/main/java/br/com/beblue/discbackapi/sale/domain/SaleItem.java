package br.com.beblue.discbackapi.sale.domain;

import br.com.beblue.discbackapi.audit.AuditDate;
import br.com.beblue.discbackapi.disc.domain.Disc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Table(name = "sale_item")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {

  @Id
  @GeneratedValue(generator = "sale_item_id_seq", strategy = SEQUENCE)
  @SequenceGenerator(name = "sale_item_seq", sequenceName = "sale_item_id_seq")
  @Column(updatable = false, nullable = false)
  private Long id;

  @OneToOne(cascade = ALL)
  private Disc disc;

  @ManyToOne(cascade = ALL)
  private Sale sale;

  private BigDecimal value;

  private Integer quantity;

  private BigDecimal cashBack;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();

  public void calculate(Disc disc) {
    this.value = disc.getValue().multiply(new BigDecimal(quantity));
    this.cashBack = disc.applyCashBack();
  }
}
