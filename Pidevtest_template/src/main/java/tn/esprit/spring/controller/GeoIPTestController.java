package tn.esprit.spring.controller;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import tn.esprit.spring.entities.GeoIP;
import tn.esprit.spring.services.GeoServiceImpl;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.services.GeoIPLocationService;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
public class GeoIPTestController {
	@Autowired
	GeoIPLocationService GeoIPLocationService;
	    //private GeoServiceImpl locationService;

	   public GeoIPTestController() throws IOException {
	    	GeoIPLocationService = new GeoServiceImpl();
	    }
	    //localhost:8081/SpringMVC/servlet/GeoIPTest?IpAddress=161.185.160.93 ->Brooklyn //197.26.86.186->Arianna//
	   //http://localhost:8081/GeoIPTest?IpAddress=161.185.160.93
	    
	    @PostMapping("/GeoIPTest")
	    public GeoIP getLocation(
	      @RequestParam(value="IpAddress", required=true) String IpAddress
	    ) throws Exception {
	        GeoIPLocationService GeoIPlocationService
	          = new GeoServiceImpl();
	        return GeoIPlocationService.getLocation(IpAddress);
	    }
	    
}
