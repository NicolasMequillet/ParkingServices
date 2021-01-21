package fr.nm.parking.services.mrs.impl;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.mrs.ParkingMrs;
import fr.nm.parking.mapper.mrs.ParkingMapperMrs;
import fr.nm.parking.services.mrs.IParkingApiMrs;
import fr.nm.parking.utils.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
public class ParkingApiImplMrs extends ServiceImplMrs<ParkingMapperMrs, ParkingMrs> implements IParkingApiMrs {

  @Value("${marseille.url}")
  String baseUrl ;

  @Override
  public List<ParkingMrs> getParkings(GpsPosition position) {
    WebClient webClient = WebClientUtil.getWebClient(baseUrl, null);
    return this.getList(webClient, position);
  }
}
