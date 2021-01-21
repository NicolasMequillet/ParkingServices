package fr.nm.parking.services.bod;

import fr.nm.parking.bean.GpsPosition;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public interface IServiceBod<P> {

  List<P> getList(WebClient webClient, GpsPosition gpsPosition) ;

}
