package br.com.beblue.discbackapi.sale.domain;

import br.com.beblue.discbackapi.AuditDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

  @Id
  @GeneratedValue(generator = "sale_id_seq", strategy = SEQUENCE)
  @SequenceGenerator(name = "sale_seq", sequenceName = "sale_id_seq")
  @Column(unique = true, nullable = false)
  private Long id;

  @OneToMany(mappedBy = "sale", fetch = LAZY, cascade = ALL)
  private List<SaleItem> saleItems;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
