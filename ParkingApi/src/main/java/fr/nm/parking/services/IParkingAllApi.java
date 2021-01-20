package fr.nm.parking.services;

import fr.nm.parking.bean.ParkingAll;
import fr.nm.parking.bean.GpsPosition;

import java.util.List;

public interface IParkingAllApi {

  List<ParkingAll> getParkings(GpsPosition position);

}
