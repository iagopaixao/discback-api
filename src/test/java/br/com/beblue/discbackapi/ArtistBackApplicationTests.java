package br.com.beblue.discbackapi;

import br.com.beblue.discbackapi.disc.client.DiscClient;
import br.com.beblue.discbackapi.spotify.client.AuthenticationClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@EnableCircuitBreaker
@RunWith(SpringRunner.class)
@EnableFeignClients(clients = {DiscClient.class, AuthenticationClient.class})
public class ArtistBackApplicationTests {

  @Test
  public void contextLoads() {}
}
