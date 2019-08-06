package br.com.beblue.discbackapi.genre.service.vo;

import br.com.beblue.discbackapi.sale.service.vo.CashBackVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class GenreVO {

  private Long id;

  private String name;

  @JsonIgnore private List<CashBackVO> cashBacks;
}
