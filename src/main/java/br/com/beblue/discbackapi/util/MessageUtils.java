package br.com.beblue.discbackapi.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import java.util.Locale;
import java.util.Properties;

public final class MessageUtils {

  private static final MessageSource MESSAGE_SOURCE;

  static {
    MESSAGE_SOURCE = messageSource();
  }

  private MessageUtils() {
    throw new IllegalStateException("Utility Class!");
  }

  public static String getMessage(final String messageKey, final Object... args) {
    return MESSAGE_SOURCE.getMessage(messageKey, args, Locale.US);
  }

  private static MessageSource messageSource() {
    final var messageSource = new ResourceBundleMessageSource();
    messageSource.setCommonMessages(yamlProperties());

    return messageSource;
  }

  private static Properties yamlProperties() {
    final var yamlProperties = new YamlPropertiesFactoryBean();
    yamlProperties.setResources(new ClassPathResource("messages.yml"));

    return yamlProperties.getObject();
  }
}
