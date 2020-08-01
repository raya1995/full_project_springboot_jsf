package tn.esprit.spring.services;


import java.util.List;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Client;


public interface RaitingUserService {

	public String addRate(String IDConnecte, String IdRate,int nbretoile);
	public String moyenneRaiting(String IDConnecte);
	public int getRate(Long IdConnecte,Long IdReate);
	public void changedRate(Client ClientConnecte, Client ClientInvite,int nbr);
	public void DeleteRate(Client ClientConnecte, Client ClientInvite);
	public int moyenneRaiting(Long IdConnecte);
	public  List<Ad> AnnnceOfBestRaiting();

}
