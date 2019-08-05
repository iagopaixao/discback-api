package br.com.beblue.discbackapi.disc;

import br.com.beblue.discbackapi.disc.service.DiscService;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(
    origins = "*",
    methods = {GET, POST, PUT, PATCH, DELETE, OPTIONS})
@Api("Disc Resource")
@RestController
@RequestMapping("/discs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscResource {

  private final DiscService service;

  @GetMapping("/catalog")
  @ApiOperation("Get the Disc Catalog")
  public ResponseEntity<Page<DiscVO>> getCatalog(
      @RequestParam("genre") @NotEmpty String genre,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {
    return ResponseEntity.ok(service.getCatalog(genre, PageRequest.of(page, size)));
  }

  @GetMapping("/{id}")
  @ApiOperation("Get one Disc by ID")
  public ResponseEntity<DiscVO> getDisc(@PathVariable long id) {
    return ResponseEntity.ok(service.getById(id));
  }
}
