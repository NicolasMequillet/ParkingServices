package fr.nm.parking.services.mrs;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.mrs.ParkingMrs;
import fr.nm.parking.services.IParkingApi;

import java.util.List;

public interface IParkingApiMrs extends IParkingApi<ParkingMrs> {

  List<ParkingMrs> getParkings(GpsPosition position);

}
