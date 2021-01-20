package fr.nm.parking.services.impl;

import fr.bordeaux_metropole.data.wfs.OpengisFeatureCollectionType;
import fr.bordeaux_metropole.data.wfs.ParkingType;
import fr.nm.parking.bean.Parking;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.mapper.IParkingMapper;
import fr.nm.parking.services.IService;
import fr.nm.parking.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service where is defined the logic to get elements provided by the WS
 *
 * @author nm
 *
 */
@Slf4j
public class ServiceImpl<M extends IParkingMapper, P extends Parking> implements IService<P> {

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

    /**
     * retrieves the element wfs:FeatureCollection
     * Here the code is reported in several block for more visibility
     */
    Mono<OpengisFeatureCollectionType>  opengisFeatureCollectionType = webClient.get()
        .retrieve()
        .bodyToMono(OpengisFeatureCollectionType.class);

    /**
     * Define a Stream of ParkingType(bm:ST_PARK_PType)
     */
    Stream<ParkingType> sp = opengisFeatureCollectionType
        .flatMapIterable(OpengisFeatureCollectionType::getFeatureMember)
        .toStream()
        .filter(featurePropertyType -> featurePropertyType.get_Feature() != null
            && featurePropertyType.get_Feature().getValue() instanceof ParkingType)
        .map(featurePropertyType -> (ParkingType) featurePropertyType.get_Feature().getValue());

    //Return a Stream<Parking> and make the sort in controller ?
    //return sp.filter(parkingType -> StringUtils.equals(Constantes.AVAILABLE, parkingType.getETAT()))
    //.map(parkingType ->  (P) mapper.fromSource(parkingType, gpsPosition));

    /**
     *
     * A generic mapper is used here allowing to use several type of bean
     * Here the list is sorted by the distance in Km (not by meters) (asc) and by nb space available (desc)
     * Maybe we could decide to not display parking with at least nbAvailable > minimum acceptable (ex 5)
     */
    return sp.filter(parkingType -> StringUtils.equals(Constantes.AVAILABLE, parkingType.getETAT()))
        .map(parkingType ->  (P) mapper.fromSource(parkingType, gpsPosition))
        .filter(p -> p.getLibres() != null && p.getGpsPosition() != null )
        .sorted(Comparator.comparingDouble(P::getDistanceInKm )
            .thenComparing(P::getLibres , Comparator.reverseOrder()))
//          .limit(5)
        .collect(Collectors.toList());
  }
}
