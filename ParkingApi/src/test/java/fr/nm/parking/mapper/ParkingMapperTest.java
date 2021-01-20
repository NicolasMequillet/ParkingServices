package fr.nm.parking.mapper;

import fr.bordeaux_metropole.data.wfs.ParkingType;
import fr.nm.parking.bean.Parking;
import fr.nm.parking.ApiConfigurationTest;
import fr.nm.parking.bean.GpsPosition;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApiConfigurationTest.class, ParkingMapperImpl.class})
public class ParkingMapperTest {

  @Resource(name="parkingTypeBod")
  ParkingType parkingTypeBod;

  @Resource(name="gpsPosition")
  GpsPosition gpsPosition;

  @Autowired
  ParkingMapper parkingMapper;

  @Test
  public void fromSourceTest() {
    log.info("=== Test parking mapper ===");

    Parking parking = parkingMapper.fromSource(parkingTypeBod, gpsPosition);
    assertNotNull(parking);
  }
}
