package fr.nm.parking.utils;

/**
 * Utility class
 * Where constants of application were defined
 *
 * @author nm
 *
 */
public class Constantes {

  public static final String LONGITUDE_PATTERN = "[\\-+]?(0?\\d{1,2}|0?\\d{1,2}\\.\\d{1,15}|1[0-7]?\\d|1[0-7]?\\d\\.\\d{1,15}|180|180\\.0{1,15})";

  public static final String LATITUDE_PATTERN = "[\\-+]?([0-8]?\\d|[0-8]?\\d\\.\\d{1,15}|90|90\\.0{1,15})";

  public static final String LAT_LON_FORMAT = "#.######";

  public static final String AVAILABLE = "LIBRE";

  public static final String DISTANCE_XX = "XX";

  public static final String DISTANCE_IN_KM = "~ XX Km";

  public static final String DISTANCE_IN_M = "~ XX meters";

  public static final String YES = "Oui";

  public static final String NO = "Non";

  public static final double THOUSAND_M = 1000d;


  /**
   * Private constructor
   * Not available for utility class
   */
  private Constantes() {
    throw new IllegalStateException("Utility class");
  }

}
