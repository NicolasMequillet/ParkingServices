package fr.nm.parking.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Bean representing a GPS position
 *
 * @author nm
 *
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GpsPosition implements Serializable {

  private static final long serialVersionUID = -2294299600440382542L;

  private Double latitude ;

  private Double longitude ;

  public GpsPosition latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  public GpsPosition longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }
}
