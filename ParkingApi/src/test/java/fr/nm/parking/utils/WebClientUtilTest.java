package fr.nm.parking.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
public class WebClientUtilTest {

  @Value("${bordeaux.cub.url}")
  String baseUrl ;

  @Test
  public void webClientTest() {
    log.info("=== Test webClient utils ===");

    assertNotNull(WebClientUtil.getWebClient(baseUrl));
  }
}
