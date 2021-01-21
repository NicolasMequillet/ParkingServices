package fr.nm.parking.mapper;

import fr.bordeaux_metropole.data.wfs.ParkingType;
import fr.nm.parking.ApiConfigurationTest;
import fr.nm.parking.bean.GpsPosition;
import fr.nm.parking.bean.bod.ParkingBod;
import fr.nm.parking.mapper.bod.ParkingMapperBod;
import fr.nm.parking.mapper.bod.ParkingMapperBodImpl;
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
@ContextConfiguration(classes = {ApiConfigurationTest.class, ParkingMapperBodImpl.class})
public class ParkingMapperTest {

  @Resource(name="parkingTypeBod")
  ParkingType parkingTypeBod;

  @Resource(name="gpsPosition")
  GpsPosition gpsPosition;

  @Autowired
  ParkingMapperBod parkingMapper;

  @Test
  public void fromSourceTest() {
    log.info("=== Test parking mapper ===");

    ParkingBod parking = parkingMapper.fromSource(parkingTypeBod, gpsPosition);
    assertNotNull(parking);
  }
}
