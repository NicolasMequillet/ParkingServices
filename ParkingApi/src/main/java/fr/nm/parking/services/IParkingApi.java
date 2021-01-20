package fr.nm.parking.services;

import fr.nm.parking.bean.Parking;
import fr.nm.parking.bean.GpsPosition;

import java.util.List;

public interface IParkingApi {

  List<Parking> getParkings(GpsPosition position);

}
