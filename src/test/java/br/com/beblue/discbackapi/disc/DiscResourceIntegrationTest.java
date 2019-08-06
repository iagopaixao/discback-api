package br.com.beblue.discbackapi.disc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.beblue.discbackapi.util.MapperUtils.OBJECT_MAPPER;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscResourceIntegrationTest {

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc =
        MockMvcBuilders.standaloneSetup()
            .setMessageConverters(new MappingJackson2HttpMessageConverter(OBJECT_MAPPER))
            .build();
  }

  @Test
  public void shouldGetOneDisc_whenGetDiscIsCalled() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/discs/{id}")
                .param("id", "1")
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    //        .andExpect(jsonPath("$.id").value(carDTO.getId()))
    //        .andExpect(jsonPath("$.brand").value(carDTO.getBrand()))
    //        .andExpect(jsonPath("$.color").value(carDTO.getColor()))
  }
}
