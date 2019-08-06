package br.com.beblue.discbackapi.sale.domain;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import br.com.beblue.discbackapi.genre.domain.Genre;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class CashBack {

  @Id
  @GeneratedValue(generator = "cash_back_id_seq", strategy = SEQUENCE)
  @SequenceGenerator(name = "cash_back_seq", sequenceName = "cash_back_id_seq")
  @Column(updatable = false, nullable = false)
  private Long id;

  @Enumerated(STRING)
  @Column(nullable = false)
  private DayOfWeek day;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @Column(nullable = false)
  private BigDecimal percentage;
}
