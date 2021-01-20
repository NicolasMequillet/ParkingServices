package fr.nm.parking.controller;

import fr.nm.parking.bean.Parking;
import fr.nm.parking.bean.ParkingAll;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.services.IParkingAllApi;
import fr.nm.parking.services.IParkingApi;
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
   * Service used to manage Parking beans
   */
  @Autowired
  private IParkingApi parkingApi;

  /**
   * Service used to manage ParkingAll beans
   */
  @Autowired
  private IParkingAllApi parkingAllApi;

  /**
   * Get a list of Parking
   *
   * @param latitude Latitude of the Gps position
   * @param longitude Longitude of the Gps position
   * @return a list of parkings containing a minimum of information about Parking
   */
  @GetMapping("/list")
  public ResponseEntity<List<Parking>> getList (
      @RequestParam(value = "latitude") Double latitude
      ,@RequestParam(value = "longitude") Double longitude)  throws ResponseStatusException {

    log.info("Call \"/list of parking \" for latitude: {} and longitude: {}",latitude, longitude);

    /**
     * Check if the input latitude and longitude are correct
     */
    checkInputParameters(latitude, longitude);

    /**
     * Call the service that retrieve the list of available parkings
     */
    return ResponseEntity.ok().body(parkingApi.getParkings(new GpsPosition().latitude(latitude).longitude(longitude)));
  }

  /**
   * Get a list of ParkAll
   *
   * @param latitude Latitude of the Gps position
   * @param longitude Longitude of the Gps position
   * @return a list of parkings containing a  lot of information about a Parking
   */
  @GetMapping("/list/all")
  public ResponseEntity<List<ParkingAll>> getListAll(
      @RequestParam(value = "latitude") Double latitude
      ,@RequestParam(value = "longitude") Double longitude) {

    log.info("Call \"/list of parking containing a lot of information\" for latitude: {} and longitude: {}",latitude, longitude);

    /**
     * Check if the input latitude and longitude are correct
     */
    checkInputParameters(latitude, longitude);

    /**
     * Call the service that retrieve the list of available parkings
     */
    return ResponseEntity.ok().body(parkingAllApi.getParkings(new GpsPosition().latitude(latitude).longitude(longitude)));
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
