package fr.nm.parking.bean.bod;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.IParking;
import fr.nm.parking.utils.Constantes;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Bean containing information about a Parking in Bordeaux
 * A lot of properties will not displayed to the front client
 * because this is not useful in this test
 * (see @JsonIgnore annotation)
 *
 * @author nm
 *
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParkingBod implements IParking, Serializable {

  private static final long serialVersionUID = 1842399466153309329L;

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

  private Integer total;

  @JsonIgnore
  private String id;

  @JsonIgnore
  private BigInteger gid;

  @JsonIgnore
  private String ident;

  @JsonIgnore
  private String prepaye;

  @JsonIgnore
  private String etat;

  @Getter(AccessLevel.NONE)
  private String connected;

  @JsonIgnore
  private String url;

  @JsonIgnore
  private Double tarif15mn;

  @JsonIgnore
  private Double tarif30mn;

  @JsonIgnore
  private Double tarif1h;

  @JsonIgnore
  private Double tarif2h;

  @JsonIgnore
  private Double tarif3h;

  @JsonIgnore
  private Double tarif4h;

  @JsonIgnore
  private Double tarif10h;

  @JsonIgnore
  private Double tarif24h;

  @JsonIgnore
  private Double tarifNuit;

  @JsonIgnore
  private String information;

  @JsonIgnore
  private String secteur;

  @JsonIgnore
  private String type;

  @JsonIgnore
  private Integer nbNiveaux;

  @JsonIgnore
  private Integer nbPlacesHGINF;

  @JsonIgnore
  private Integer nbPlacesHGSUP;

  @JsonIgnore
  private Integer nbPlacesFOURR;

  @JsonIgnore
  private Integer nbPlacesGLOBAL;

  @JsonIgnore
  private Integer nbPlacesTOTAL;

  @JsonIgnore
  private Integer nbPlacesPR;

  @JsonIgnore
  private Integer nbPlacesPMR;

  @JsonIgnore
  private Integer nbPlacesVLE;

  @JsonIgnore
  private Integer nbPlacesMOBALT;

  @JsonIgnore
  private Integer nbPlacesCOVOIT;

  @JsonIgnore
  private Integer nbPlacesSTLAV;

  @JsonIgnore
  private Integer nbPlaces2RMOT;

  @JsonIgnore
  private Integer nbPlaces2RELE;

  @JsonIgnore
  private Integer nbPlacesVELTOT;

  @JsonIgnore
  private Integer nbPlacesVELEC;

  @JsonIgnore
  private Double gabariMax;

  @JsonIgnore
  private Double gabariStandard;

  @JsonIgnore
  private String tarifType;

  @JsonIgnore
  private String tarifHandi;

  /**
   * Used only in this test API to have a visual indicator
   * Should be done by a front api
   *
   * @return
   */
  public String getTauxOccupation() {
    return libres != null && total != null && total > 0d
        ? Math.round( (total -libres) * 100d / total) +"%"
        : "-";
  }

  /**
   * Used only in this test API to have a visual indicator
   * Should be done by a front api
   *
   * @return
   */
  @JsonIgnore
  public String getConnecte() {
    return StringUtils.equals("1", connected)
        ? Constantes.YES : Constantes.NO;
  }

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
