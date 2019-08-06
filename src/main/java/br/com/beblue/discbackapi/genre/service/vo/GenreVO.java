package br.com.beblue.discbackapi.genre.service.vo;

import br.com.beblue.discbackapi.sale.service.vo.CashBackVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class GenreVO {

  private Long id;

  private String name;

  @JsonIgnore private List<CashBackVO> cashBacks;
}
