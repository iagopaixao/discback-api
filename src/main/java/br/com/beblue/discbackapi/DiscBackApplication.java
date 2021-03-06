package br.com.beblue.discbackapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication(scanBasePackages = "br.com.beblue.discbackapi")
public class DiscBackApplication {

  public static void main(String... args) {
    SpringApplication.run(DiscBackApplication.class, args);
  }
}
