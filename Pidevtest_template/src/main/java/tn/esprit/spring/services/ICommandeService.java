package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Commande;

public interface ICommandeService {
	public Commande saveCommande(Long idUser,long idCart,String typePayement);
	public String addCommandeFurniture(Long idCommande,int idProduct);

	public List<String> getCommandeProductNameByIdUser(Long idUser);

	
	public double prixTotaleLigneCommande(Long idCart);
//	public double montantTotaleCommande();
	
	public double PourcentageRemiseCommande(Long idCart,Long idCmd);
	Commande saveCommande1(Long idUser, long idCart, String typePayement);
	public void saveCommande2(Long idUser, long idCart, String typePayement);
	
	
}
