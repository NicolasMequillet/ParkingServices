package fr.nm.parking.services.bod.impl;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.bod.ParkingBod;
import fr.nm.parking.mapper.bod.ParkingMapperBod;
import fr.nm.parking.services.bod.IParkingApiBod;
import fr.nm.parking.utils.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Implementation of service used to manipulate Parking elements
 *
 * @author nm
 *
 */
@Service
@Slf4j
@EnableCaching
public class ParkingApiImplBod extends ServiceImplBod<ParkingMapperBod, ParkingBod> implements IParkingApiBod {

  @Value("${bordeaux.cub.url}")
  String baseUrl ;

  @Override
  public List<ParkingBod> getParkings(GpsPosition position) {
    WebClient webClient = WebClientUtil.getWebClient(baseUrl, contentTypeInterceptor());
    return this.getList(webClient, position);
  }

  /**
   * https://stackoverflow.com/questions/56950034/how-to-override-any-httpheader-in-response-of-webclient
   *
   * override any HttpHeader in response of WebClient
   * webclient interceptor that overrides the response headers ...
   *
   * */
  private ExchangeFilterFunction contentTypeInterceptor() {
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
