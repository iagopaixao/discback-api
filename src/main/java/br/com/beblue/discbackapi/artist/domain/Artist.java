package br.com.beblue.discbackapi.artist.domain;

import br.com.beblue.discbackapi.AuditDate;
import br.com.beblue.discbackapi.disc.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(name = "spotify_id", nullable = false, unique = true)
  private String spotifyId;

  @Column(nullable = false)
  private String name;

  @ManyToMany(fetch = LAZY, cascade = ALL)
  protected Set<Genre> genres;

  @Embedded @Builder.Default() private AuditDate auditDate = new AuditDate();
}
