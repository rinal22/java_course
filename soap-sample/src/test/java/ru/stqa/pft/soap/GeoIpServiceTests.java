package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.138.135.251");
    Assert.assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
    }
  }

