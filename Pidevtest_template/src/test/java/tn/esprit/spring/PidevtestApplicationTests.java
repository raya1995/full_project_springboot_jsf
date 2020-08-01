

package tn.esprit.spring;
//import java.io.File;
import java.io.IOException;
//import java.net.InetAddress;
//import java.text.ParseException;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
//import com.maxmind.geoip2.model.CityResponse;

import tn.esprit.spring.services.AchatLocationService;
import tn.esprit.spring.services.IAdService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class  PidevtestApplicationTests  {
	@Autowired 
	IAdService as;
	@Autowired
	AchatLocationService al;
	@Test
	public void contextLoads(){
		
	//al.RemplissageTablePrix();
		
		/*SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
		Date d1=f.parse("2015-02-01");

	   Ad a = new Ad("Dari","Marsa",250,d1,100,true,100,KindOfGood.Villa);
	   Ad a2 = new Ad("dourou","Lac",100,d1,100,true,100,KindOfGood.Apartment);
		Comment com = new Comment("raya",20,false,a);
		Comment com2 = new Comment("youssef",10,true,a2);
		Comment com3 = new Comment("youssef",10,true,a2);
		as.addAd(a);
		as.addAd(a2);
		as.addComment(com);
		as.addComment(com2);
		as.addComment(com3);*/
		/*as.deleteComment(1);
		as.deleteComment(15);*/
	}
	@Test
	public void givenIP__whenFetchingCity__thenReturnsCityData()
			throws IOException, GeoIp2Exception{

		/*  String ip = "your-ip-address";
		    String dbLocation = "your-path-to-mmdb";

		    File database = new File(dbLocation);
		    DatabaseReader dbReader = new DatabaseReader.Builder(database)
		      .build();

		    InetAddress ipAddress = InetAddress.getByName(ip);
		    CityResponse response = dbReader.city(ipAddress);

		    String countryName = response.getCountry().getName();
		    String cityName = response.getCity().getName();
		    String postal = response.getPostal().getCode();
		    String state = response.getLeastSpecificSubdivision().getName();*/
		}
	
	
	
}



