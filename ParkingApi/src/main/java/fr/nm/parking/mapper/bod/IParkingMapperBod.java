package fr.nm.parking.mapper.bod;

import fr.bordeaux_metropole.data.wfs.DirectPositionType;
import fr.bordeaux_metropole.data.wfs.ParkingType;
import fr.bordeaux_metropole.data.wfs.PointType;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.bod.ParkingBod;
import fr.nm.parking.utils.GeoUtils;
import org.mapstruct.Context;
import org.mapstruct.Named;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

/**
 * Parking Bod Mapper interface
 *
 * @author nm
 *
 */
public interface IParkingMapperBod<P extends ParkingBod> {

  P fromSource(ParkingType parkingType, @Context GpsPosition gpsPosition);

  /**
   * Return a bean Position from the values found in ST_PARK_P element
   *
   * @param parkingType
   * @return
   */
  @Named("computePosition")
  default GpsPosition computePosition(ParkingType parkingType) {
    List<Double> currentPos = getInputPosition(parkingType);
    return !currentPos.isEmpty()
        ? new GpsPosition().latitude(currentPos.get(0)).longitude(currentPos.get(1))
        : null;
  }

  /**
   * Calcul the distance in meters
   * between the gps position defined in ST_PARK_P element
   * and the position sent
   *
   * @param parkingType
   * @param gpsPosition
   * @return
   */
  @Named("computeDistance")
  default Double computeDistance( ParkingType parkingType, @Context GpsPosition gpsPosition){
    List<Double> currentPos = getInputPosition(parkingType);
    if (!currentPos.isEmpty()) {
      return GeoUtils.getDistanceInMeters(gpsPosition.getLatitude(), gpsPosition.getLongitude(), currentPos.get(0), currentPos.get(1));
    } else {
      return -1D;
    }
  }

  /**
   *
   * @param value
   * @return
   */
  @Named("computeNumber")
  default Integer computeNumber (BigInteger value) {
    return  value != null
        ? value.intValue()
        : 0;
  }

  /**
   * Get the GPS position from a ST_PARK_P element
   *
   * @param parkingType
   * @return
   */
  default List<Double> getInputPosition(ParkingType parkingType) {
    if (parkingType.getGeometry() != null
        && parkingType.getGeometry().get_Geometry() != null
        && parkingType.getGeometry().get_Geometry().getValue() != null) {

      DirectPositionType directPositionType = ((PointType) parkingType.getGeometry().get_Geometry().getValue()).getPos();
      return (directPositionType != null && directPositionType.getValue() != null  && directPositionType.getValue().size() == 2)
          ? directPositionType.getValue() : null;
    } else {
      return Collections.emptyList();
    }
  }
}
