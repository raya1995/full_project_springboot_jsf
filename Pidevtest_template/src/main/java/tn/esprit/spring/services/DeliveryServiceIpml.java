package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.repository.DeliveryRepository;
import tn.esprit.spring.repository.LivreurRepository;


@Service
public class DeliveryServiceIpml implements IDeliveyService {

	@Autowired
	DeliveryRepository deliveryRepository;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	LivreurRepository livreurRepository;

	@Override
	public String passerLivraison(Delivery delivery) {

	
			deliveryRepository.save(delivery);
	
		return "delivery went successfully";
	}

	@Override
	public void affecterLivraisonALivreur(Long idLivreur, long idDelivery) {
		
		Livreur livreurManage = livreurRepository.findById(idLivreur).get();
		Delivery DeliveryManage = deliveryRepository.findById(idDelivery).get();	
		
		
		
	if(livreurManage.getStateLivreur().equals(livreurManage.getStateLivreur().InActive)){
	
		 System.out.println("Ce livreur est non disponible pour le momment");
		
	}
	else 
		livreurManage.setNbMission(livreurManage.getNbMission()+1);
	livreurManage.setStateLivreur(livreurManage.getStateLivreur().InActive);
	DeliveryManage.setLivreur(livreurManage);
	
	
	}

	
	
	
}
