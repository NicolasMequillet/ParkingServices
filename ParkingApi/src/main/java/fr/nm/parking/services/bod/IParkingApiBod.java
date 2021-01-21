package fr.nm.parking.services.bod;

import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.bod.ParkingBod;
import fr.nm.parking.services.IParkingApi;

import java.util.List;

public interface IParkingApiBod extends IParkingApi<ParkingBod> {

  List<ParkingBod> getParkings(GpsPosition position);

}
