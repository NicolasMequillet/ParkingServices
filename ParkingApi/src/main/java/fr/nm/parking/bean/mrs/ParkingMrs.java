package fr.nm.parking.bean.mrs;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.nm.parking.bean.IParking;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean containing the minimum of useful information about a Parking in Marseille
 *
 * @author nm
 *
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParkingMrs implements IParking {

  protected String nom ;

  protected String adresse ;


}

