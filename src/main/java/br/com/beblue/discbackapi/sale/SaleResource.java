package br.com.beblue.discbackapi.sale;

import br.com.beblue.discbackapi.sale.aggregate.SaleAggregate;
import br.com.beblue.discbackapi.sale.response.SaleResponse;
import br.com.beblue.discbackapi.sale.service.SaleService;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

//TODO: remove this anotation and add CORS config class
@CrossOrigin(
    origins = "*",
    methods = {GET, POST, PUT, PATCH, DELETE, OPTIONS}
)
@Api("Sale Resource")
@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleResource {

  private final SaleService service;

  private final SaleAggregate aggregate;

  @GetMapping("/{id}")
  @ApiOperation("Get one Sale by ID")
  public ResponseEntity<SaleVO> getDisc(@PathVariable long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  @ApiOperation("Perform one Sale")
  public ResponseEntity<SaleResponse> sell(@RequestBody @Valid SaleVO vo) throws URISyntaxException {
    return ResponseEntity.created(new URI("/sales")).body((aggregate.sell(vo)));
  }
}
