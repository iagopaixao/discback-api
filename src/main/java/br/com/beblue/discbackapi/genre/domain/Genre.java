package br.com.beblue.discbackapi.genre.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

import br.com.beblue.discbackapi.sale.domain.CashBack;
import java.util.List;
import javax.persistence.Column;
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
