package br.com.beblue.discbackapi.sale;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import br.com.beblue.discbackapi.sale.aggregate.SaleAggregate;
import br.com.beblue.discbackapi.sale.service.SaleService;
import br.com.beblue.discbackapi.sale.service.request.SaleItemRequest;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
    origins = "*",
    methods = {GET, POST}
)
@Api("Sale Resource")
@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleResource {

  private final SaleService service;

  private final SaleAggregate aggregate;

  @GetMapping
  @ApiOperation("Get all Sales")
  public ResponseEntity<Page<SaleVO>> getSales(
      @RequestParam("initialDate") @DateTimeFormat(iso = DATE_TIME) LocalDateTime initialDate,
      @RequestParam("endDate") @DateTimeFormat(iso = DATE_TIME) LocalDateTime endDate,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {

    return ResponseEntity.ok(service.getSales(initialDate, endDate, PageRequest.of(page, size)));
  }

  @GetMapping("/{id}")
  @ApiOperation("Get one Sale by ID")
  public ResponseEntity<SaleVO> getOneSale(@PathVariable long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  @ApiOperation("Perform one Sale")
  public ResponseEntity<SaleVO> sell(@RequestBody @Valid List<SaleItemRequest> items) {
    return ResponseEntity.created(URI.create("/sales")).body((aggregate.sell(items)));
  }
}
