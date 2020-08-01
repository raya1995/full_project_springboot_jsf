package tn.esprit.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.repository.LigneCommandeRepository;
import tn.esprit.spring.services.ILigneCommandeService;

@RestController
@RequestMapping("/Panier")
public class RestControllerLigneCommande {

//	@Autowired
//	IShoppingCardService iShoppingCardService;
//	
//	
//	@PostMapping("/ajouterCard")
//	@ResponseBody
//	public ShoppingCard ajouterShoppingCard(ShoppingCard shoppingCard){
//		
//		iShoppingCardService.ajouterShoppingCard(shoppingCard);
//		return shoppingCard;
//	}
//	
//	
//	@GetMapping(value = "/getShoppingCardById/{idCard}")
//	@ResponseBody
//	public ShoppingCard getShoppingCardById(@PathVariable("idCard")int cardId){
//		return iShoppingCardService.getShoppingCardById(cardId);
//	}
	@Autowired
	ILigneCommandeService ligneCommandeService;
	
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	

	
//	
//	public LigneCommande findOne(Long id){
//		
//		return ligneCommandeService.findOne(id);
//	}
//	
//	
//	
//	
//	
//	@GetMapping("/{idUser}")
//	public List<List<String>> panierParIdclient(@PathVariable(value = "idUser") long id){
//		
//		return ligneCommandeService.panierParIdclient(id);
//		
//	}
//	
//	
//	
//	
	@GetMapping(value = "/getAllProductByCart")
	@ResponseBody
	public List<LigneCommande> getAllProductByCart(){ 
		
	
		return ligneCommandeService.getAllProductByCart();
	}
//	
//	
//	
//	
//	@GetMapping("/{idprod}/{idUser}/{idCommande}")
//	public LigneCommande findLigneCommande(@PathVariable(value = "idprod") int idFurniture,@PathVariable(value = "idUser")Long idClient,@PathVariable(value = "idCommande") Long idCommande){
//		
//		return ligneCommandeService.findLigneCommande(idFurniture, idClient, idCommande);
//	}
//	
//	
//	
	
	@PostMapping("/ajouter/{idUser}")
	@ResponseBody
	public ResponseEntity<List<LigneCommande>> ajouterLigneCommande(@PathVariable ("idUser")long idUser ){
		
		List<LigneCommande> list=ligneCommandeRepository.getLigneCommandByUserId(idUser);
		if(list.isEmpty()){
		   ligneCommandeService.saveLigneCommande(idUser);
		   return ResponseEntity.ok(list);
		}
		return ResponseEntity.ok(list);
	    
	}
	
	@GetMapping(value = "/countFurnitureByCart/{idCard}")
	public int countFurnitureByCart(@PathVariable("idCard") Long idCard) {
		
		return ligneCommandeService.countFurnitureByCart( idCard);
	}
//	
//	
//	@PostMapping("/g/{idCart}/{idUser}")
//	@ResponseBody
//	public String addCartByUserByUserId(@PathVariable("idCart") long  idCart , @PathVariable("idUser ") long idUser ) {
//		return ligneCommandeService.addCartByUserByUserId( idCart, idUser);
//	}
}
