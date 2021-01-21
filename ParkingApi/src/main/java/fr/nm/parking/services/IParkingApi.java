package fr.nm.parking.services;

import fr.nm.parking.bean.GpsPosition;

import java.util.List;

public interface IParkingApi<B> {

  List<B> getParkings(GpsPosition position);
}
