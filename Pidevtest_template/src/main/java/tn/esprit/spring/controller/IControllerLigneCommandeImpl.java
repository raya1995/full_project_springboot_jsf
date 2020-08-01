package tn.esprit.spring.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.services.ILigneCommandeService;



@Controller

public class IControllerLigneCommandeImpl {

//	
//	@Autowired
//	IShoppingCardService iShoppingCardService;
//	
	
//	public int ajouterShoppingCard(ShoppingCard shoppingCard){
//		iShoppingCardService.ajouterShoppingCard(shoppingCard);
//		return shoppingCard.getId();
//		
//	}
//	
//	public ShoppingCard getShoppingCardById(int cardId){
//		
//	return iShoppingCardService.getShoppingCardById(cardId);
//	}

	@Autowired
	ILigneCommandeService ligneCommandeService;
	
//	public List<List<String>> AjouterAuPanier(int idprod, long iduser, LigneCommande lc){
//		
//		return ligneCommandeService.AjouterAuPanier(idprod, iduser, lc);
//	}
//	
//	
//	public LigneCommande findOne(Long id){
//		
//		return ligneCommandeService.findOne(id);
//	}
//	
	public List<LigneCommande> getAllProductByCart(){
		
		return ligneCommandeService.getAllProductByCart();
	}
//	public LigneCommande findLigneCommande(int idFurniture,Long idClient,Long idCommande){
//		
//		return ligneCommandeService.findLigneCommande(idFurniture, idClient, idCommande);
//	}
//	
//	public LigneCommande ajouterLigneCommande(LigneCommande lc, long idUser	){
//		return       ligneCommandeService.saveLigneCommande(lc, idUser);
//		       
//		     
//		
//	}
}
