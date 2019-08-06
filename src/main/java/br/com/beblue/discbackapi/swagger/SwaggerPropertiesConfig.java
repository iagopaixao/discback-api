package br.com.beblue.discbackapi.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "swagger-ui.api-info")
public class SwaggerPropertiesConfig {

  private String title;

  private String description;

  private String license;

  private String basePackage;

  private String contractName;

  private String contractUrl;

  private String contractEmail;
}
