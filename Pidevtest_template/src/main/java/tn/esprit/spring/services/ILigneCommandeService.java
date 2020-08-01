package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.LigneCommande;

public interface ILigneCommandeService {
//	public LigneCommande findOne(Long id);
//	public List<List<String>> panierParIdclient( long iduser);
	public List<LigneCommande> getAllProductByCart();
//	public LigneCommande findLigneCommande(int idFurniture,Long idClient,Long idCommande);
//	public List<List<String>> AjouterAuPanier(int idprod, long iduser,LigneCommande lc );
	public LigneCommande saveLigneCommande(Long idUser);
	public int  countFurnitureByCart (Long idCard);
//	public Double PrixTotalCommande(long iduser);
}
