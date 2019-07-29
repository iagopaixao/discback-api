package br.com.beblue.discbackapi.web.rest;

import br.com.beblue.discbackapi.disc.service.CarService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  private final CarService service;
}