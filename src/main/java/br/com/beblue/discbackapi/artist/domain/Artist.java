package br.com.beblue.discbackapi.artist.domain;

import br.com.beblue.discbackapi.common.AuditLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artist", schema = "public")
public class Artist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private String id;

  @Column(nullable = false)
  private String name;

  @Embedded @Builder.Default() private AuditLog auditLog = new AuditLog();
}
