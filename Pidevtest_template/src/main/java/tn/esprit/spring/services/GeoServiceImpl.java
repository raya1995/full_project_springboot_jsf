package tn.esprit.spring.services;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import tn.esprit.spring.entities.GeoIP;
@Service
public class GeoServiceImpl implements GeoIPLocationService{
		private DatabaseReader dbReader;

		public GeoServiceImpl() throws IOException {
	        File database = new File("your-mmdb-location");
	        dbReader = new DatabaseReader.Builder(database).build();
	    }

	    public GeoIP getLocation(String ip)  throws IOException, GeoIp2Exception{
	        InetAddress IpAddress = InetAddress.getByName(ip);
	        CityResponse response = dbReader.city(IpAddress);

	        String cityName = response.getCity().getName();
	        String latitude =
	          response.getLocation().getLatitude().toString();
	        String longitude =
	          response.getLocation().getLongitude().toString();
	        return new GeoIP(ip, cityName, latitude, longitude);
	    }

	
	}


     

