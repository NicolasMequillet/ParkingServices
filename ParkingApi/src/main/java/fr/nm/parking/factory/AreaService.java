package fr.nm.parking.factory;

import fr.nm.parking.services.IParkingApi;
import lombok.Getter;
import lombok.Setter;

/**
 * Contains the service linked to an area
 *
 * @param <B> bean like PakingBod
 */
@Setter
@Getter
public class AreaService<B> {

  private IParkingApi<B> parkingApi;

  public AreaService(IParkingApi<B> parkingApi){
    this.parkingApi = parkingApi;
  }

}
