package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.services.IFurnitureService;

@RestController
public class RestControlFurniture {

	@Autowired
	IFurnitureService iFurnitureService;
	

	// @Autowired
	// IShoppingCardService iShoppingCardService;
	//
	// http://localhost:8081/SpringMVC/servlet/ajouterMeuble

	// {"name":"test","description":"test","price":20,"quantity":10,"type":"New"}
	@PostMapping("/ajouterMeuble")
	@ResponseBody
	public Furniture ajouterMeuble(@RequestBody Furniture furniture) {

		iFurnitureService.ajouterMeuble(furniture);

		return furniture;
	}

	public void ajouterMeuble1(Furniture furniture) {

		iFurnitureService.ajouterMeuble(furniture);

		
	}

	// http://localhost:8081/SpringMVC/servlet/getAllFurnitures
	@GetMapping(value = "/getAllFurnitures")
	@ResponseBody
	public List<Furniture> getAllFurniture() {
		return iFurnitureService.getAllFurniture();
	}

	// http://localhost:8081/SpringMVC/servlet/getAllFurnituresNamesJPQL
	@GetMapping(value = "/getAllFurnituresNamesJPQL")
	@ResponseBody
	public List<String> getAllFurnitureNamesJPQL() {

		return iFurnitureService.getAllFurnitureNamesJPQL();
	}

	// http://localhost:8081/SpringMVC/servlet/deleteFurnitureById/2
	@DeleteMapping("/deleteFurnitureById/{idfurniture}")
	@ResponseBody
	public void deleteFurnitureById(@PathVariable("idfurniture") int furnitureId) {

		iFurnitureService.deleteFurnitureById(furnitureId);
	}

	@PutMapping(value = "/addMeubleDansPanier12/{idmeuble}/{quantity}/{panierId}")
	public String addMeubleDansPanier(@PathVariable("idmeuble") int idmeuble, @PathVariable("quantity") int quantity,
			@PathVariable("panierId") long panierId) {
		return iFurnitureService.addMeubleDansPanier12(idmeuble, quantity, panierId);

	}

	@PutMapping(value = "/incrementMeubleDansPanier12/{idmeuble}/{quantity}/{panierId}")
	public String incrementMeubleDansPanier12(@PathVariable("idmeuble") int idmeuble,
			@PathVariable("quantity") int quantity, @PathVariable("panierId") long panierId) {
		return iFurnitureService.incrementMeubleDansPanier12(idmeuble, quantity, panierId);

	}

	@DeleteMapping(value = "/deleteMeubleFromPanier12/{idmeuble}/{panierId}")
	public String deleteMeubleFromPanier12(@PathVariable("idmeuble") int idmeuble,
			@PathVariable("panierId") long panierId) {
		return iFurnitureService.deleteMeubleFromPanier12(idmeuble, panierId);

	}

	@GetMapping(value = "/getFurnitureByCard/{panierId}")
	public List<String> getFurnitureByCard(@PathVariable("panierId") long panierId) {
		return iFurnitureService.getFurnitureByCard(panierId);

	}

	@GetMapping(value = "/getNombreFurnitureJPQL")
	@ResponseBody
	public int getNombreFurnitureJPQL() {
		return iFurnitureService.getNombreFurnitureJPQL();
	}

	@PostMapping("/updateQuantityFurnitureAfterCommande/{idProd}/{idCmd}")
	@ResponseBody
	public int updateQuantityFurnitureAfterCommande(@PathVariable("idProd") int idProd,
			@PathVariable("idCmd") long idCmd) {

		return iFurnitureService.updateQuantityFurnitureAfterCommande(idProd, idCmd);
	}

}
