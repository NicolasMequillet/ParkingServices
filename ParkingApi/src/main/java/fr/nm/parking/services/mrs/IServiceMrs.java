package fr.nm.parking.services.mrs;

import fr.nm.parking.bean.GpsPosition;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public interface IServiceMrs<P> {

  List<P> getList(WebClient webClient, GpsPosition gpsPosition) ;

}
