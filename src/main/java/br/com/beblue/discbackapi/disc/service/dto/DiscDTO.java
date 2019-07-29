package br.com.beblue.discbackapi.disc.service.dto;

import br.com.beblue.discbackapi.disc.domain.AuditLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscDTO {

  private Long id;

  private AuditLog auditLog;
}
