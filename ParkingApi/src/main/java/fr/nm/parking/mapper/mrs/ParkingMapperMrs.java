package fr.nm.parking.mapper.mrs;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.mrs.ParkingMrs;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

/**
 * Mapper allowing to transform an input ST_PARK_PType into a ParkingMrs
 *
 * @author nm
 *
 */
@Mapper(componentModel = "spring")
public interface ParkingMapperMrs extends IParkingMapperMrs<ParkingMrs> {

  ParkingMrs fromSource(FakeSourceParkingMrs fakeSourceParkingMrs, @Context GpsPosition gpsPosition);
}
