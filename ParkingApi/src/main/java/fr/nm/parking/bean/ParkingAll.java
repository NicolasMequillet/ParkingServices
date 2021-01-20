package fr.nm.parking.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.nm.parking.utils.Constantes;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Bean containing a lot information about a Park
 *
 * @author nm
 *
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParkingAll extends Parking implements Serializable {

  private static final long serialVersionUID = 1842399466153309329L;

  private Integer total ;

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

  private String url;

  private Double tarif15mn;

  private Double tarif30mn;

  private Double tarif1h;

  private Double tarif2h;

  private Double tarif3h;

  private Double tarif4h;

  private Double tarif10h;

  private Double tarif24h;

  private Double tarifNuit;

  private String information;

  private String secteur;

  private String type;

  @JsonIgnore
  private Integer nbNiveaux;

  private Integer nbPlacesHGINF;

  private Integer nbPlacesHGSUP;

  @JsonIgnore
  private Integer nbPlacesFOURR;

  @JsonIgnore
  private Integer nbPlacesGLOBAL;

  @JsonIgnore
  private Integer nbPlacesTOTAL;

  private Integer nbPlacesPR;

  private Integer nbPlacesPMR;

  private Integer nbPlacesVLE;

  private Integer nbPlacesMOBALT;

  private Integer nbPlacesCOVOIT;

  private Integer nbPlacesSTLAV;

  private Integer nbPlaces2RMOT;

  private Integer nbPlaces2RELE;

  private Integer nbPlacesVELTOT;

  private Integer nbPlacesVELEC;

  private Double gabariMax;

  @JsonIgnore
  private Double gabariStandard;

  private String tarifType;

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
  public String getConnecte() {
    return StringUtils.equals("1", connected)
        ? Constantes.YES : Constantes.NO;
  }
}
