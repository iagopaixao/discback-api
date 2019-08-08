package br.com.beblue.discbackapi.disc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.beblue.discbackapi.config.AbstractIntegrationConfig;
import org.junit.Test;

public class DiscResourceIntegrationTest extends AbstractIntegrationConfig {

  @Test
  public void shouldReturnOneDisc_whenGetDiscIsCalled() throws Exception {
    getMockMvc()
        .perform(
            get("/discs/{id}", 100)
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
  }

  @Test
  public void shouldReturnDiscsCatalog_whenGetCatalogIsCalled() throws Exception {
    getMockMvc()
        .perform(
            get("/discs/catalog" )
                .param("genre", "MPB")
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
    ;
  }
}
