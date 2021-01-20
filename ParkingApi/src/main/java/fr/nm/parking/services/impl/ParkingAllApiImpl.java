package fr.nm.parking.services.impl;

import fr.nm.parking.bean.ParkingAll;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.mapper.ParkingAllMapper;
import fr.nm.parking.services.IParkingAllApi;
import fr.nm.parking.utils.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Implementation of service used to manipulate ParkingAll elements
 *
 * @author nm
 *
 */
@Service
@Slf4j
@EnableCaching
public class ParkingAllApiImpl extends ServiceImpl<ParkingAllMapper, ParkingAll> implements IParkingAllApi {

  @Value("${bordeaux.cub.url}")
  String baseUrl ;

  @Override
  public List<ParkingAll> getParkings(GpsPosition position) {
    WebClient webClient = WebClientUtil.getWebClient(baseUrl);
    return this.getList(webClient, position);
  }
}
