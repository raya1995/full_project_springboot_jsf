package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.services.ICommandeService;



@RestController
@RequestMapping("/Commande")
public class RestControllerCommande {
	
	@Autowired 
	ICommandeService commandeService;
	@Autowired
	CommandeRepository commandeRepository;
	
//	
//	@PostMapping("/ajouter")
//	@ResponseBody
//	public Commande saveCommande(Commande c) {
//	return 	commandeService.saveCommande(c);
//	}
	
	@PostMapping("/ajouter/{idUser}/{idCart}/{typePayement}")
	@ResponseBody
	public ResponseEntity<List<Commande>> ajouetrCommande(@PathVariable("idUser") Long idUser,
														  @PathVariable("idCart")long idCart,
														  @PathVariable("typePayement")String typePayement){
		List<Commande> list =commandeRepository.findAll();
//		//Commande cmd = new Commande();
//		LigneCommande l = new LigneCommande();
//		if(list.isEmpty()){
		//cmd.getLigneCommande().equals(l.getId());
		commandeService.saveCommande(idUser, idCart,typePayement);
		return ResponseEntity.ok(list);
		
	//}
	//return ResponseEntity.ok(list);		
		
	
	}
	
	@PutMapping(value = "/addCommandeFurniture/{idCommande}/{idProduct}") 
	public String addCommandeFurniture(@PathVariable("idCommande") long idCommande, @PathVariable("idProduct") int idProduct){
	   return   commandeService.addCommandeFurniture(idCommande, idProduct);
		
	}
	@GetMapping(value = "/getCommandeProductNameByIdUser/{idUser}")
	@ResponseBody
	public List<String> getCommandeProductNameByIdUser(@PathVariable("idUser") Long idUser){
		return commandeService.getCommandeProductNameByIdUser(idUser);
	}
	
	@GetMapping(value = "/getPrixTotal/{idCart}")
	@ResponseBody
	public Double PrixTotalCommande(@PathVariable("idCart") Long idCart){
		return commandeService.prixTotaleLigneCommande(idCart);
	}
//	@GetMapping(value = "/PourcentageRemiseCommande/{idUser}/{idCmd}")
//	@ResponseBody
//	public Commande PourcentageRemiseCommande(@PathVariable("idUser") Long idUser,@PathVariable("idCmd") Long idcmd){
//		return commandeService.PourcentageRemiseCommande(idUser, idCmd);
//	}
}
