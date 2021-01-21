package fr.nm.parking.mapper.mrs;


import com.fasterxml.jackson.annotation.JsonInclude;
import fr.nm.parking.bean.IParking;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Fake bean to mock a type of parking that could be returned By a Marseille WS
 *
 * @author nm
 *
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FakeSourceParkingMrs implements IParking {

  protected String name ;

  protected String address ;


}