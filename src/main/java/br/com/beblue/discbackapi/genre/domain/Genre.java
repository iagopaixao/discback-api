package br.com.beblue.discbackapi.genre.domain;

import br.com.beblue.discbackapi.sale.domain.CashBack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

  @Id
  @GeneratedValue(generator = "genre_id_seq", strategy = SEQUENCE)
  @SequenceGenerator(name = "genre_seq", sequenceName = "genre_id_seq")
  @Column(updatable = false, nullable = false)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "genre", cascade = ALL)
  private List<CashBack> cashBacks;
}
