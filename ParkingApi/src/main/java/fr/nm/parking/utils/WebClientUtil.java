package fr.nm.parking.utils;


import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Utility class
 * Provide a WebClient
 *
 * @author nm
 *
 */
public class WebClientUtil {

  /**
   * Private constructor
   * Not available for utility class
   */
  private WebClientUtil() {
    throw new IllegalStateException("Utility class");
  }


  /**
   * Create WebClient using WebClient builder
   * @param baseUrl
   * @return
   */
  public static WebClient getWebClient(String baseUrl, ExchangeFilterFunction exchangeFilterFunction) {
    WebClient.Builder builder = WebClient.builder();
    if (exchangeFilterFunction != null) {
      builder.filter(exchangeFilterFunction);
    }
    return builder.baseUrl(baseUrl).build();
  }


}