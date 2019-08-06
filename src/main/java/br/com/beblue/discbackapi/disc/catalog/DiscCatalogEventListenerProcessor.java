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

  private final DiscCatalogConsumer consumer;

  @EventListener
  public void process(@Lazy ContextRefreshedEvent e) {
    consumer.populateDiscCatalog();
  }
}
