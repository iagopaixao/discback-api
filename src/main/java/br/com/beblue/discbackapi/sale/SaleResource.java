package br.com.beblue.discbackapi.sale;

import br.com.beblue.discbackapi.sale.aggregate.SaleAggregate;
import br.com.beblue.discbackapi.sale.response.SaleResponse;
import br.com.beblue.discbackapi.sale.service.SaleService;
import br.com.beblue.discbackapi.sale.service.vo.SaleItemVO;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

// TODO: remove this anotation and add CORS config class
@CrossOrigin(
    origins = "*",
    methods = {GET, POST, PUT, PATCH, DELETE, OPTIONS})
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
      @RequestParam("initialDate") @DateTimeFormat(iso = DATE) LocalDate initialDate,
      @RequestParam("endDate") @DateTimeFormat(iso = DATE) LocalDate endDate,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {

    return ResponseEntity.ok(service.getSales(initialDate, endDate, PageRequest.of(page, size)));
  }

  @GetMapping("/{id}")
  @ApiOperation("Get one Sale by ID")
  public ResponseEntity<SaleVO> getSale(@PathVariable long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  @ApiOperation("Perform one Sale")
  public ResponseEntity<SaleResponse> sell(@RequestBody @Valid List<SaleItemVO> items)
      throws URISyntaxException {
    return ResponseEntity.created(new URI("/sales")).body((aggregate.sell(items)));
  }
}
