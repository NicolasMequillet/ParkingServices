package fr.nm.parking.controller;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.IParking;
import fr.nm.parking.factory.AreaFactory;
import fr.nm.parking.factory.AreaService;
import fr.nm.parking.utils.GeoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Parking Controller
 *
 * @author nm
 *
 */
@RestController
@Slf4j
public class ParkingController {

  /**
   * Factory that contains service to used by zone
   */
  @Autowired
  AreaFactory areaFactory;

  /**
   * Get a list of Parking
   *
   * @param latitude Latitude of the Gps position
   * @param longitude Longitude of the Gps position
   * @return a list of parkings containing information about Parking
   */
  @GetMapping("/list")
  public ResponseEntity<List<IParking>> getList (
      @RequestParam(value = "latitude") Double latitude
      ,@RequestParam(value = "longitude") Double longitude) {

    log.info("Call \"/list of parking \" for latitude: {} and longitude: {}",latitude, longitude);

    /**
     * Check if the input latitude and longitude are correct
     */
    checkInputParameters(latitude, longitude);

    /**
     * Build the Gps Position
     */
    GpsPosition gpsPosition = new GpsPosition().latitude(latitude).longitude(longitude);

    /**
     * Used the area Factory component
     * in order to get the appropriate implementation associated at gps position
     * Ex: if the gps position is in the range on gps position defined for Bordeaux
     * So the Factory will return the ParkingApi implementation defined for Bordeaux.
     *
     */
    AreaService<IParking> areaService = areaFactory.getAreaService(gpsPosition);
    if(areaService != null) {
      /**
       * Call the service that retrieve the list of available parkings
       */
      return ResponseEntity.ok().body(areaService.getParkingApi().getParkings(gpsPosition));
    } else {
      log.error("Gps Area not implemented yet for latitude: {} and longitude: {}",latitude, longitude);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gps Area not implemented") ;
    }
  }

  /**
   * Check The position sent
   *
   * @param latitude
   * @param longitude
   * @throws ResponseStatusException
   */
  private void checkInputParameters(Double latitude, Double longitude) {
    if (!GeoUtils.isCorrectPositionGps(latitude,longitude)) {
      log.error("checkInputParameters: Bad Gps position for latitude: {} and longitude: {}",latitude, longitude);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Gps position") ;
    }
  }

}
