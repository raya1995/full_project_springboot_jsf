package tn.esprit.spring.services;

import tn.esprit.spring.entities.Delivery;

public interface IDeliveyService {
	public String passerLivraison(Delivery delivery);
	public void affecterLivraisonALivreur(Long idLivreur,long idDelivery);

}
