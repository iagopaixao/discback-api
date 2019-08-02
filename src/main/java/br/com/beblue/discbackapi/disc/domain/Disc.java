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
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disc {

  @Id
  @GeneratedValue(generator = "disc_id_seq", strategy = SEQUENCE)
  @SequenceGenerator(name = "disc_seq", sequenceName = "disc_id_seq")
  @Column(updatable = false, nullable = false)
  private Long id;

  @Column(name = "spotify_id", unique = true, nullable = false)
  private String spotifyId;

  private String name;

  private BigDecimal price;

  @ManyToMany(fetch = LAZY, cascade = ALL)
  @JoinTable(
      name = "Artist_Disc",
      joinColumns = { @JoinColumn(name = "artist_id") },
      inverseJoinColumns = { @JoinColumn(name = "disc_id") }
  )
  private Set<Artist> artists;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
