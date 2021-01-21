package fr.nm.parking.factory;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.IParking;
import fr.nm.parking.services.bod.IParkingApiBod;
import fr.nm.parking.services.mrs.IParkingApiMrs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * This class is a registry of the services implemented in the API
 * (Currently only one is implemented for Bordeaux.)
 *
 * We can imagine perfecting this factory and making it as generic as possible
 * in order to defined all needed service that could be used in a country for an client application
 * whose need would be to know the list of available car parks closest to his gps postion.
 *
 * Only 2 area have been initialized here
 *
 */
@Component
@Slf4j
@EnableCaching
public class AreaFactory {

  /**
   * Arbitrary range of latitude used here for Bordeaux
   * Arbitrary range of longitude used here for Bordeaux
   */
  private static final String AREA_BOD= "AREA_BOD";
  private static final Double BOD_LAT_MIN = 44.774274d;
  private static final Double BOD_LAT_MAX = 44.944685d;
  private static final Double BOD_LON_MIN = -0.701561d;
  private static final Double BOD_LON_MAX = -0.510386d;
  private static final Area areaBod = new Area( AREA_BOD,BOD_LAT_MIN,BOD_LAT_MAX,BOD_LON_MIN,BOD_LON_MAX);

  /**
   * Service used for Bordeaux (Uses Lacub WS)
   */
  @Autowired
  private IParkingApiBod parkingBodApi;

  /**
   * Arbitrary range of latitude used here for Marseille
   * Arbitrary range of longitude used here for Marseille
   */
  private static final String AREA_MRS= "AREA_MRS";
  private static final Double MRS_LAT_MIN = 43.230431d;
  private static final Double MRS_LAT_MAX = 43.417668d;
  private static final Double MRS_LON_MIN = 5.248766d;
  private static final Double MRS_LON_MAX = 5.634321d;
  private static final Area areaMrs = new Area( AREA_MRS,MRS_LAT_MIN,MRS_LAT_MAX,MRS_LON_MIN,MRS_LON_MAX);

  /**
   * Service used for Marseille (returns an empty list)
   */
  @Autowired
  private IParkingApiMrs parkingMrsApi;

  private static Map<String, AreaService<IParking>> mapServices = new HashMap<>();

  private static List<Area> areaList = new ArrayList<>();

  /**
   * Init the list of areas
   * Init the map associating each area to a service
   */
  @PostConstruct
  private void init() {
    areaList.add(areaBod);
    areaList.add(areaMrs);
    mapServices.put(AREA_BOD, new AreaService(parkingBodApi));
    mapServices.put(AREA_MRS, new AreaService(parkingMrsApi));
  }

  /**
   * Give a service for a given gsp position
   * If no corresponding area found then returns null
   * And no treatment  will be done by the controller
   *
   * @param gpsPosition
   * @return
   */
  public AreaService<IParking> getAreaService(GpsPosition gpsPosition) {

    AreaService<IParking> areaService = null;
    Optional<Area> result = areaList
        .stream()
        .filter(area -> area.getLatitudeMin() <= gpsPosition.getLatitude()
            && area.getLatitudeMax() >= gpsPosition.getLatitude()
            && area.getLongitudeMin() <= gpsPosition.getLongitude()
            && area.getLatitudeMax() >= gpsPosition.getLongitude()).findFirst();

    if (result.isPresent()) {
      Area found = result.get();
      log.info("Area found :{} for gps position : {} / {}",found.getName(), gpsPosition.getLatitude(), gpsPosition.getLongitude() );

      areaService = mapServices.get(found.getName());
    }
    return areaService;
  }
}
