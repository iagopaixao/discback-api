package br.com.beblue.discbackapi.sale.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.GenerationType.SEQUENCE;

import br.com.beblue.discbackapi.audit.AuditDate;
import br.com.beblue.discbackapi.disc.domain.Disc;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @OneToOne(cascade = MERGE)
  private Disc disc;

  @ManyToOne(cascade = MERGE)
  @JoinColumn(name = "sale_id")
  private Sale sale;

  private BigDecimal value;

  private Integer quantity;

  private BigDecimal cashBack;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
