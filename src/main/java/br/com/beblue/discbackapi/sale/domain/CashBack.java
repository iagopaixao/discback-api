package br.com.beblue.discbackapi.sale.domain;

import br.com.beblue.discbackapi.genre.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.DayOfWeek;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;

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

  @ManyToOne
  private Genre genre;

  @Column(nullable = false)
  private BigDecimal percentage;
}
