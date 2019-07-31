package br.com.beblue.discbackapi.sale.domain;

import br.com.beblue.discbackapi.AuditDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  //TODO: solving when create migration
  @Transient
  private List<String> discIds;

  private BigDecimal value;

  private BigDecimal cashbackTotal;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
