package br.com.beblue.discbackapi.artist.service.vo;

import br.com.beblue.discbackapi.AuditDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistVO {

  private String id;

  private String name;

  private AuditDate auditDate;
}
