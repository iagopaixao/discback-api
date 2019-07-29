package br.com.beblue.discbackapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public final class JacksonMapperUtils {

  public static final ObjectMapper OBJECT_MAPPER;

  static {
    OBJECT_MAPPER =
        new ObjectMapper()
            .registerModules(new Jdk8Module(), new JavaTimeModule())
            .disable(WRITE_DATES_AS_TIMESTAMPS);
  }

  private JacksonMapperUtils() {
    throw new IllegalStateException("Utility Class!");
  }
}
