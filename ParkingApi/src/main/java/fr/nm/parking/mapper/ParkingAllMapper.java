package fr.nm.parking.mapper;

import fr.bordeaux_metropole.data.wfs.ParkingType;
import fr.nm.parking.bean.ParkingAll;
import fr.nm.parking.bean.GpsPosition;
import org.mapstruct.*;

import java.math.BigInteger;

/**
 * Mapper allowing to transform an input ST_PARK_PType into a ParkAll
 * ParkAll contains almost all the elements defined in ST_PARK_PType
 *
 * @author nm
 *
 */
@Mapper(componentModel = "spring")
public interface ParkingAllMapper extends IParkingMapper<ParkingAll> {

  @Mappings({
      @Mapping(target = "nom", source = "parkingType.NOM")
      , @Mapping(target = "libres", source = "parkingType.LIBRES", qualifiedByName = "computeNumber")
      , @Mapping(target = "total", source = "parkingType.TOTAL", qualifiedByName = "computeNumber")
      , @Mapping(target = "distanceEnMetres", source = "parkingType", qualifiedByName = "computeDistance")
      , @Mapping(target = "gpsPosition", source = "parkingType", qualifiedByName = "computePosition")
      , @Mapping(target = "adresse", source = "parkingType.ADRESSE")
      , @Mapping(target = "id", source = "parkingType.id")
      , @Mapping(target = "gid", source = "parkingType.GID")
      , @Mapping(target = "ident", source = "parkingType.IDENT")
      , @Mapping(target = "prepaye", source = "parkingType.PREPAYE")
      , @Mapping(target = "etat", source = "parkingType.ETAT")
      , @Mapping(target = "connected", source = "parkingType.CONNECTE")
      , @Mapping(target = "url", source = "parkingType.URL")
      , @Mapping(target = "tarif15mn", source = "parkingType.TH_QUAR")
      , @Mapping(target = "tarif30mn", source = "parkingType.TH_DEMI")
      , @Mapping(target = "tarif1h", source = "parkingType.TH_HEUR")
      , @Mapping(target = "tarif2h", source = "parkingType.TH_2")
      , @Mapping(target = "tarif3h", source = "parkingType.TH_3")
      , @Mapping(target = "tarif4h", source = "parkingType.TH_4")
      , @Mapping(target = "tarif10h", source = "parkingType.TH_10")
      , @Mapping(target = "tarif24h", source = "parkingType.TH_24")
      , @Mapping(target = "tarifNuit", source = "parkingType.TH_NUIT")
      , @Mapping(target = "information", source = "parkingType.INFOR")
      , @Mapping(target = "secteur", source = "parkingType.SECTEUR")
      , @Mapping(target = "type", source = "parkingType.TYPE")
      , @Mapping(target = "nbNiveaux", source = "parkingType.NB_NIV" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesHGINF", source = "parkingType.NP_HGINF" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesHGSUP", source = "parkingType.NP_HGSUP" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesFOURR", source = "parkingType.NP_FOURR" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesGLOBAL", source = "parkingType.NP_GLOBAL" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesTOTAL", source = "parkingType.NP_TOTAL" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesPR", source = "parkingType.NP_PR" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesPMR", source = "parkingType.NP_PMR" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesVLE", source = "parkingType.NP_VLE" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesMOBALT", source = "parkingType.NP_MOBALT" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesCOVOIT", source = "parkingType.NP_COVOIT" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesSTLAV", source = "parkingType.NP_STLAV" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlaces2RMOT", source = "parkingType.NP_2RMOT" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlaces2RELE", source = "parkingType.NP_2RELE" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesVELTOT", source = "parkingType.NP_VELTOT" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "nbPlacesVELEC", source = "parkingType.NP_VELEC" , qualifiedByName = "computeBigInteger")
      , @Mapping(target = "gabariMax", source = "parkingType.GABARI_MAX")
      , @Mapping(target = "gabariStandard", source = "parkingType.GABARI_STD")
      , @Mapping(target = "tarifType", source = "parkingType.TA_TYPE")
      , @Mapping(target = "tarifHandi", source = "parkingType.TA_HANDI")
  })
  ParkingAll fromSource(ParkingType parkingType, @Context GpsPosition gpsPosition);

  @Named("computeBigInteger")
  default Integer computeBigInteger (BigInteger value) {
    return  value != null && value.intValue() != 0
        ? value.intValue()
        : null;
  }
}
