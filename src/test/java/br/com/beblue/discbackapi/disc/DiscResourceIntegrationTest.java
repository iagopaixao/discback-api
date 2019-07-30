package br.com.beblue.discbackapi.disc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.beblue.discbackapi.util.JacksonMapperUtils.OBJECT_MAPPER;

//TODO: add tests
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
  public void shouldReturnAllDiscs_whenGetAllIsCalled() {
    assert true;
  }
}
