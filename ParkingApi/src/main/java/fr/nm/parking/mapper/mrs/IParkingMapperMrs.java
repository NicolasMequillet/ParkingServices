package fr.nm.parking.mapper.mrs;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.mrs.ParkingMrs;
import org.mapstruct.Context;

/**
 * Parking Mrs Mapper interface
 *
 * @author nm
 *
 */
public interface IParkingMapperMrs<P extends ParkingMrs> {

  P fromSource(FakeSourceParkingMrs fakeSourceParkingMrs, @Context GpsPosition gpsPosition);

}
