package br.com.beblue.discbackapi.artist.service.dto;

import br.com.beblue.discbackapi.common.AuditLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO {

  private String id;

  private String name;

  private AuditLog auditLog;
}
