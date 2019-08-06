package br.com.beblue.discbackapi.spotify.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableFeignClients(clients = SpotifyAuthenticationClient.class)
public class SpotifyAuthenticationClientIntegrationTest {

  private @Autowired SpotifyAuthenticationClient client;

  @Test
  public void shouldReturnAuthenticationResponse_whenGetTokenIsCalled() {
    final var response = client.getToken();

    assertTrue(response.isPresent());
    assertNotNull(response.get());
  }
}
