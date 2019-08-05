package br.com.beblue.discbackapi.disc.domain;

import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.audit.AuditDate;
import br.com.beblue.discbackapi.genre.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;
import static java.util.Comparator.naturalOrder;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
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

  public BigDecimal applyCashBack() {
    return collectGenres().stream()
        .map(Genre::calculateCashBack)
        .max(naturalOrder())
        .orElse(ZERO);
  }

  private Set<Genre> collectGenres() {
    return CollectionUtils.emptyIfNull(artists).stream()
        .map(Artist::getGenres)
        .flatMap(Set::stream)
        .collect(Collectors.toSet());
  }
}
