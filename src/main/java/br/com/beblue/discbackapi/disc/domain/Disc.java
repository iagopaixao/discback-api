package br.com.beblue.discbackapi.disc.domain;

import br.com.beblue.discbackapi.AuditDate;
import br.com.beblue.discbackapi.artist.domain.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disc {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(name = "spotify_id", nullable = false, unique = true)
  private String spotifyId;

  private String name;

  private BigDecimal price;

  @ManyToMany(fetch = LAZY, cascade = ALL)
  private Set<Artist> artists;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
