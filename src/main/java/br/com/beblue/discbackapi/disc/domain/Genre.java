package br.com.beblue.discbackapi.disc.domain;

import br.com.beblue.discbackapi.AuditDate;
import br.com.beblue.discbackapi.sale.domain.CashBack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "genre", fetch = LAZY, cascade = ALL)
  private List<CashBack> cashBacks;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
