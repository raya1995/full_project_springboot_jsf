package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Delivery;

import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.repository.DeliveryRepository;
import tn.esprit.spring.services.IDeliveyService;

@RestController
public class RestControllerDelivery {
	
	@Autowired
	IDeliveyService ideliveryService;
	@Autowired
	DeliveryRepository deliveryRepository;
	@Autowired
	CommandeRepository commandeRepository;
	
	@PostMapping("/passDelivery")
	@ResponseBody
	public String passerLivraison(@RequestBody Delivery delivery){
		
	
		   ideliveryService.passerLivraison(delivery);

	return" delivery added good";
	    
	}
	
	@PostMapping("/affecterLivraisonALivreur/{idLivreur}/{idDelivery}")
	@ResponseBody
	public void affecterLivraisonALivreur(@PathVariable ("idLivreur")long idLivreur, @PathVariable ("idDelivery") long idDelivery){
		
		
		   ideliveryService.affecterLivraisonALivreur(idLivreur, idDelivery);


	    
	}
}
