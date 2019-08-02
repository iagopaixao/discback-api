package br.com.beblue.discbackapi.artist.domain;

import br.com.beblue.discbackapi.audit.AuditDate;
import br.com.beblue.discbackapi.disc.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class Artist {

  @Id
  @GeneratedValue(generator = "artist_id_seq", strategy = SEQUENCE)
  @SequenceGenerator(name = "artist_seq", sequenceName = "artist_id_seq")
  @Column(updatable = false, nullable = false)
  private Long id;

  @Column(name = "artist_spotify_id", nullable = false)
  private String spotifyId;

  @Column(nullable = false)
  private String name;

  @ManyToMany(fetch = LAZY, cascade = ALL)
  @JoinTable(
      name = "Artist_Genre",
      joinColumns = { @JoinColumn(name = "genre_id") },
      inverseJoinColumns = { @JoinColumn(name = "artist_id") }
  )
  private Set<Genre> genres;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
