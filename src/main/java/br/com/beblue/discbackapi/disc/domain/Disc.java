package br.com.beblue.discbackapi.disc.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.audit.AuditDate;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
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
public class Disc {

  @Id
  @GeneratedValue(generator = "disc_id_seq", strategy = SEQUENCE)
  @SequenceGenerator(name = "disc_seq", sequenceName = "disc_id_seq")
  @Column(updatable = false, nullable = false)
  private Long id;

  @Column(name = "disc_spotify_id", nullable = false)
  private String spotifyId;

  private String name;

  private BigDecimal value;

  @ManyToMany(fetch = EAGER, cascade = MERGE)
  @JoinTable(
      name = "disc_artist",
      joinColumns = {@JoinColumn(name = "disc_id")},
      inverseJoinColumns = {@JoinColumn(name = "artist_id")})
  @OrderColumn
  private Set<Artist> artists;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
