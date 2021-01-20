package fr.nm.parking.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.nm.parking.utils.Constantes;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean containing the minimum of useful information about a Park
 *
 * @author nm
 *
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Parking {

  protected String nom ;

  protected Integer libres ;

  protected Double distanceEnMetres ;

  protected String adresse ;

  protected GpsPosition gpsPosition;

  @Getter(AccessLevel.NONE)
  protected String distance;

  /**
   * Only used to sort list
   */
  @JsonIgnore
  @Getter(AccessLevel.NONE)
  protected String distanceInKm;
  public Long getDistanceInKm() {
    return distanceEnMetres > Constantes.THOUSAND_M
        ? Math.round(distanceEnMetres / Constantes.THOUSAND_M)
        : 0L;
  }

  /**
   * Only used in this test API to have a visual indicator
   * Not needed in real API
   *
   * @return
   */
  public String getDistance() {
    return distanceEnMetres > Constantes.THOUSAND_M
        ? Constantes.DISTANCE_IN_KM.replace(Constantes.DISTANCE_XX,String.valueOf(Math.round(distanceEnMetres / Constantes.THOUSAND_M)))
        : Constantes.DISTANCE_IN_M.replace(Constantes.DISTANCE_XX,String.valueOf(Math.round(distanceEnMetres)));
  }
}
