package br.com.beblue.discbackapi.liquibase;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfig {

  @Bean
  public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties properties) {
    final var liquibase = new SpringLiquibase();

    log.debug("Configuring Liquibase...");

    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog(properties.getChangeLog());
    liquibase.setContexts(properties.getContexts());
    liquibase.setDefaultSchema(properties.getDefaultSchema());
    liquibase.setDropFirst(properties.isDropFirst());
    liquibase.setShouldRun(properties.isEnabled());

    log.debug("Liquibase configured.");

    return liquibase;
  }
}
