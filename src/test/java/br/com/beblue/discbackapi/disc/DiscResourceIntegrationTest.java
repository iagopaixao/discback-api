package br.com.beblue.discbackapi.disc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.beblue.discbackapi.AbstractIntegrationConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
