package fr.nm.parking.factory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Area {

  private String name;

  private Double latitudeMin;

  private Double latitudeMax;

  private Double longitudeMin;

  private Double longitudeMax;

  public Area(String name
      , Double latitudeMin
      , Double latitudeMax
      , Double longitudeMin
      , Double longitudeMax){
    this.name = name;
    this.latitudeMin = latitudeMin;
    this.latitudeMax = latitudeMax;
    this.longitudeMin = longitudeMin;
    this.longitudeMax = longitudeMax;
  }
}
