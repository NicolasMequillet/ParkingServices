package fr.nm.parking.services.mrs.impl;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.mrs.ParkingMrs;
import fr.nm.parking.mapper.mrs.IParkingMapperMrs;
import fr.nm.parking.services.mrs.IServiceMrs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

/**
 * Service where is defined the logic to get elements provided by the WS
 *
 * @author nm
 *
 */
@Slf4j
public class ServiceImplMrs<M extends IParkingMapperMrs, P extends ParkingMrs> implements IServiceMrs<P> {

  @Autowired
  private M mapper;

  /**
   *
   * @param webClient
   * @param gpsPosition
   * @return
   */
  @Override
  public List<P> getList(WebClient webClient, GpsPosition gpsPosition) {

    return Collections.emptyList();

  }
}
