package br.com.beblue.discbackapi.disc.catalog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscCatalogEventListenerProcessor {

  private final CatalogService service;

  @EventListener
  //TODO: add verification if catalog is already populated
  public void populate(@Lazy ContextRefreshedEvent event) {
    log.info("m=populate status=initial message=populating disk catalog  event={}", event.getSource());

//    final var map = service.populateDiscCatalog();
//    log.info("m=populate status=finished payload={}", map.toString());
    log.info("m=populate status=finished payload=hai hai");

  }
}
