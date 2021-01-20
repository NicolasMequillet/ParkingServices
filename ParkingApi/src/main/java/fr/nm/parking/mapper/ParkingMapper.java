package fr.nm.parking.mapper;

import fr.bordeaux_metropole.data.wfs.ParkingType;
import fr.nm.parking.bean.Parking;
import fr.nm.parking.bean.GpsPosition;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper allowing to transform an input ST_PARK_PType into a Parking
 *
 * @author nm
 *
 */
@Mapper(componentModel = "spring")
public interface ParkingMapper extends IParkingMapper<Parking> {

  @Mappings({
      @Mapping(target = "nom", source = "parkingType.NOM")
      , @Mapping(target = "libres", source = "parkingType.LIBRES", qualifiedByName = "computeNumber")
      , @Mapping(target = "distanceEnMetres", source = "parkingType", qualifiedByName = "computeDistance")
      , @Mapping(target = "adresse", source = "parkingType.ADRESSE")
      , @Mapping(target = "gpsPosition", source = "parkingType", qualifiedByName = "computePosition")
  })
  Parking fromSource(ParkingType parkingType, @Context GpsPosition gpsPosition);
}
