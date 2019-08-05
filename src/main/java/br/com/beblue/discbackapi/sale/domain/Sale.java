package br.com.beblue.discbackapi.sale.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

import br.com.beblue.discbackapi.audit.AuditDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @Column(updatable = false, nullable = false)
  private Long id;

  @OneToMany(mappedBy = "sale", fetch = EAGER, cascade = {PERSIST, MERGE})
  private List<SaleItem> saleItems;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
