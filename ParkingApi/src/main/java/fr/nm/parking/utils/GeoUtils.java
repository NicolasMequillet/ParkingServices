package fr.nm.parking.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicLine;
import net.sf.geographiclib.GeodesicMask;

import java.util.regex.Pattern;

/**
 * Utility class

 * Uses GeographicLib API in order to calcul distance between two GPS positions
 * Checks format of latitude an Longitude with dedicated Pattern
 *
 * @author nm
 *
 */
@Slf4j
public class GeoUtils {

  private static Geodesic geodesic = Geodesic.WGS84;// This matches EPSG4326, which is the coordinate system used by Geolake

  /**
   * Private constructor
   * Not available for utility class
   */
  private GeoUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Check if latitude and longitude are in the correct range
   *
   * @param latitude
   * @param longitude
   * @return
   */
  public static boolean isCorrectPositionGps(Double latitude, Double longitude) {
    return checkLatitue(latitude) && checkLongitude(longitude);
  }

  public static boolean checkLatitue(Double latitude) {
    return latitude != null ?
        Pattern.matches(Constantes.LATITUDE_PATTERN, latitude.toString())
        : Boolean.FALSE;
  }

  public static boolean checkLongitude(Double longitude) {
    return longitude != null ?
        Pattern.matches(Constantes.LONGITUDE_PATTERN, longitude.toString())
        : Boolean.FALSE;
  }

  /**
   * Get the distance between two points in meters.
   * @param lat1 First point's latitude
   * @param lon1 First point's longitude
   * @param lat2 Second point's latitude
   * @param lon2 Second point's longitude
   * @return Distance between the first and the second point in meters
   */
  public static double getDistanceInMeters(double lat1, double lon1, double lat2, double lon2) {
    GeodesicLine line = geodesic.InverseLine(lat1, lon1, lat2, lon2, GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
    return Math.round(line.Distance());
  }

}
