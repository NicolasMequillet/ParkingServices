package fr.nm.parking.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
public class GeoUtilsTest {

  @Test
  public void latitudeTest(){
    log.info("=== Test latitudes ===");

    Double latitudeToTest = -90.01;
    boolean result = GeoUtils.checkLatitue(latitudeToTest);
    log.info("Latitude: tested {}. Result matches: {}", latitudeToTest, result);
    Assert.assertEquals(Boolean.FALSE, result);

    latitudeToTest = 90.01;
    result = GeoUtils.checkLatitue(latitudeToTest);
    log.info("Latitude: tested {}. Result matches: {}", latitudeToTest, result);
    Assert.assertEquals(Boolean.FALSE, result);

    latitudeToTest = -90.0;
    result = GeoUtils.checkLatitue(latitudeToTest);
    log.info("Latitude: tested {}. Result matches: {}", latitudeToTest, result);
    Assert.assertEquals(Boolean.TRUE, result);

    latitudeToTest = 90.0;
    result = GeoUtils.checkLatitue(latitudeToTest);
    log.info("Latitude: tested {}. Result matches: {}", latitudeToTest, result);
    Assert.assertEquals(Boolean.TRUE, result);

    latitudeToTest = 44.0;
    result = GeoUtils.checkLatitue(latitudeToTest);
    log.info("Latitude: tested {}. Result matches: {}", latitudeToTest, result);
    Assert.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void longitudeTest(){
    log.info("=== Test longitudes ===");

    Double longitudeToTest = -180.01;
    boolean result = GeoUtils.checkLongitude(longitudeToTest);
    log.info("Longitude: tested {}. Result matches: {}", longitudeToTest, result);
    Assert.assertEquals(Boolean.FALSE, result);

    longitudeToTest = 180.01;
    result = GeoUtils.checkLongitude(longitudeToTest);
    log.info("Longitude: tested {}. Result matches: {}", longitudeToTest, result);
    Assert.assertEquals(Boolean.FALSE, result);

    longitudeToTest = -180.0;
    result = GeoUtils.checkLongitude(longitudeToTest);
    log.info("Longitude: tested {}. Result matches: {}", longitudeToTest, result);
    Assert.assertEquals(Boolean.TRUE, result);

    longitudeToTest = 180.0;
    result = GeoUtils.checkLongitude(longitudeToTest);
    log.info("Longitude: tested {}. Result matches: {}", longitudeToTest, result);
    Assert.assertEquals(Boolean.TRUE, result);

    longitudeToTest = -0.5;
    result = GeoUtils.checkLongitude(longitudeToTest);
    log.info("Longitude: tested {}. Result matches: {}", longitudeToTest, result);
    Assert.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void gpsPositionTest() {
    log.info("=== Test Gps Positions ===");
    log.info("Test with bad latitude");
    Assert.assertEquals(Boolean.FALSE, GeoUtils.isCorrectPositionGps(-200d, -0.5d));

    log.info("Test with bad latitude");
    Assert.assertEquals(Boolean.FALSE, GeoUtils.isCorrectPositionGps(-200d, -0.5d));

    /**
     *  <gml:Envelope srsName="EPSG:4326">
     *  <gml:lowerCorner>44.774274 -0.701561</gml:lowerCorner>
     *  <gml:upperCorner>44.944685 -0.510386</gml:upperCorner>
     *  </gml:Envelope>
     */
    log.info("Test EPSG:4326");
    Assert.assertEquals(Boolean.TRUE, GeoUtils.isCorrectPositionGps(44.774274d, -0.701561d));
    Assert.assertEquals(Boolean.TRUE, GeoUtils.isCorrectPositionGps(44.944685d, -0.510386));
  }

}
