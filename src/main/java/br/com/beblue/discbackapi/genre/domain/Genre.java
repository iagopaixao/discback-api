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
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
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

  @OneToMany(mappedBy = "genre", fetch = EAGER, cascade = {PERSIST, MERGE})
  private List<CashBack> cashBacks;

  public BigDecimal calculateCashBack() {
    if (isEmpty(this.cashBacks)) {
      return ZERO;
    }
    return emptyIfNull(this.cashBacks).stream()
        .filter(c -> c.getDay().equals(LocalDateTime.now().getDayOfWeek()))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new)
        .getPercentage();
  }
}
