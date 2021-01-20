package fr.nm.parking.services;

import fr.nm.parking.bean.GpsPosition;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public interface IService<P> {

  List<P> getList(WebClient webClient, GpsPosition gpsPosition) ;

}
