package fr.nm.parking.controller;

import fr.nm.parking.bean.Parking;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.services.IParkingApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Mock
  private IParkingApi parkApi;


  @BeforeEach
  public void setup() {
  }

  @Test
  public void testRequest() {
    log.info("=== Test call list of parking for latitude 44, longitude -0.5 ===");

    given(parkApi.getParkings(any(GpsPosition.class))).willReturn(Collections.emptyList());

    ResponseEntity<List<Parking>> response = this.restTemplate.exchange(
        "/list?latitude=44&longitude=-0.5",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Parking>>() {
        });
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testBadRequest() {
    log.info("=== Test call list of parking with bad parameters ===");

    given(parkApi.getParkings(any(GpsPosition.class))).willReturn(Collections.emptyList());

    try {
      ResponseEntity<List<Parking>> response = this.restTemplate.exchange(
          "/list?latitude=100&longitude=200",
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<Parking>>() {
          });

    } catch ( Exception e) {
      log.error(e.getMessage());
    }
  }
}