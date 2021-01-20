package fr.nm.parking.utils;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
  public static WebClient getWebClient(String baseUrl) {
    WebClient webClient = WebClient.builder()
        .filter(contentTypeInterceptor())
        .baseUrl(baseUrl)
        .build();
    return webClient;
  }

  /**
   * https://stackoverflow.com/questions/56950034/how-to-override-any-httpheader-in-response-of-webclient
   *
   * override any HttpHeader in response of WebClient
   * webclient interceptor that overrides the response headers ...
   *
   * */
  private static ExchangeFilterFunction contentTypeInterceptor() {
    return ExchangeFilterFunction.ofResponseProcessor(clientResponse ->
        Mono.just(
            ClientResponse
                .from(clientResponse) //clientResponse  is immutable, so,we create a clone. but from() only clones headers and status code
                .headers(headers -> headers.remove(HttpHeaders.CONTENT_TYPE)) //override the content type
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE)
                .body(clientResponse.body(BodyExtractors.toDataBuffers())) // copy the body as bytes with no processing
                .build())
    );
  }
}