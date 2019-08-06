package br.com.beblue.discbackapi.util;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public final class MapperUtils {

  public static final ObjectMapper OBJECT_MAPPER;

  static {
    OBJECT_MAPPER =
        new ObjectMapper()
            .registerModules(new Jdk8Module(), new JavaTimeModule())
            .disable(WRITE_DATES_AS_TIMESTAMPS)
            .enable(ACCEPT_SINGLE_VALUE_AS_ARRAY);
  }

  private MapperUtils() {
    throw new IllegalStateException("Utility Class!");
  }

  public static <T> Predicate<T> distinctBy(Function<? super T, ?> keyExtractor) {
    final var seen = new ConcurrentHashMap<Object, Boolean>();

    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }
}
