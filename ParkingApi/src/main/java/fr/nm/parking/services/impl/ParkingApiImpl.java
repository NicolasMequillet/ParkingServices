package fr.nm.parking.services.impl;

import fr.nm.parking.bean.Parking;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.mapper.ParkingMapper;
import fr.nm.parking.services.IParkingApi;
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
public class ParkingApiImpl extends ServiceImpl<ParkingMapper, Parking> implements IParkingApi {

  @Value("${bordeaux.cub.url}")
  String baseUrl ;

  @Override
  public List<Parking> getParkings(GpsPosition position) {
    WebClient webClient = WebClientUtil.getWebClient(baseUrl);
    return this.getList(webClient, position);
  }
}
