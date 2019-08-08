package br.com.beblue.discbackapi.disc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import br.com.beblue.discbackapi.disc.service.DiscService;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods = GET)
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
  public ResponseEntity<DiscVO> getOneDisc(@PathVariable long id) {
    return ResponseEntity.ok(service.getById(id));
  }
}
