package br.com.beblue.discbackapi.disc.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscResponse {

  private String id;

  private String name;

  private BigDecimal price;
}
