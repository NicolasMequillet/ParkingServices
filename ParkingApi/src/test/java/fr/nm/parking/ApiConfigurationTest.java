package fr.nm.parking;

import fr.bordeaux_metropole.data.wfs.ParkingType;
import fr.nm.parking.bean.GpsPosition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.math.BigInteger;

@Configuration
public class ApiConfigurationTest {

  @Bean(name="parkingTypeBod")
  @Primary
  public ParkingType parkingTypeBod() {
    ParkingType parkingType = new ParkingType();
    parkingType.setADRESSE("adresse");
    parkingType.setNOM("nom");
    parkingType.setLIBRES(new BigInteger("100") );
    parkingType.setTOTAL(new BigInteger("200") );

    return parkingType;
  }

  @Bean(name="gpsPosition")
  @Primary
  GpsPosition gpsPosition() {
    return new GpsPosition()
        .latitude(44.837789d)
        .longitude(-0.579180d);
  }

}
