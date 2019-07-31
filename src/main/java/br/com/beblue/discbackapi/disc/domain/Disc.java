package br.com.beblue.discbackapi.disc.domain;

import br.com.beblue.discbackapi.AuditDate;
import br.com.beblue.discbackapi.artist.domain.Artist;
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
public class Disc {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true, nullable = false)
  private String id;

  private String name;

  private Genre genre;

  private BigDecimal price;

  private BigDecimal cashback;

  //TODO: solving when create migration
  @Transient
  private List<Artist> artists;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
