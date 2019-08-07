package br.com.beblue.discbackapi.sale;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.beblue.discbackapi.AbstractIntegrationConfig;
import java.time.LocalDateTime;
import org.junit.Test;

public class SaleResourceIntegrationTest extends AbstractIntegrationConfig {

  @Test
  public void shouldReturnSales_whenGetSalesIsCalled() throws Exception {
    getMockMvc()
        .perform(
            get("/sales/")
                .param("initialDate", LocalDateTime.now().toString())
                .param("endDate", LocalDateTime.now().plusDays(1).toString())
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
  }
}
