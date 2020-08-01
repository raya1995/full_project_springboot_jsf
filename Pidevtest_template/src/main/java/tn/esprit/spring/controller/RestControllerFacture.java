package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Factures;
import tn.esprit.spring.services.IFacturesService;

@RestController
public class RestControllerFacture {
	@Autowired
	IFacturesService factureservice;
	

	// URL : http://localhost:8081/SpringMVC/servlet/ajouterFacture
	//{"id": 1,"montant": 223,"date_de_depart": "2021-03-03T00:00:00.000+0000","type": "bbbb","commande":1}
	@PostMapping("/ajouterFacture")
	@ResponseBody
	public Factures ajouteFacture(@RequestBody Factures facture)
	{
		factureservice.ajouterFacture(facture);
		return facture;	
		}
	// URL : http://localhost:8081/SpringMVC/servlet/affecterCommande_A_Facture
	   @PutMapping(value = "affecterCommande_A_Facture/{idf}/{idc}")
		public void affecterCommande_A_Facture(@PathVariable("idf")int idFacture,@PathVariable("idc")int idCommande){
		   factureservice.affecterCommandeAFacture(idFacture, idCommande);;
	   }
	   @GetMapping(value = "/afficherPDF/{id}/{idClient}")
	   public void facturepdf (@PathVariable("id") int id,@PathVariable("idClient") long idClient) {
		   
		   factureservice.facturepdf(id,idClient);
	   }
}
