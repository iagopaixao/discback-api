package br.com.beblue.discbackapi.disc;

import br.com.beblue.discbackapi.disc.service.DiscService;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(
    origins = "*",
    methods = {GET, POST, PUT, PATCH, DELETE, OPTIONS}
)
@Api("Disc Resource")
@RestController
@RequestMapping("/discs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscResource {

  private final DiscService service;

  @GetMapping("/catalog")
  public ResponseEntity<Page<DiscVO>> getCatalog(Pageable pageable) {
    return ResponseEntity.ok(service.getCatalog(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DiscVO> getDisc(@PathVariable long id) {
    return ResponseEntity.ok(service.getById(id));
  }
}