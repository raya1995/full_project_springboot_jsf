package tn.esprit.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.services.ILivreurService;


@RestController
public class RestControllerLivreur {
	
	@Autowired
	ILivreurService iLivreurService;
	
	

	/*Enregistrer un livreur*/
	@PostMapping("/ajout")
	
	public Livreur createLivreur(@Valid @RequestBody Livreur liv)
	{
		return iLivreurService.save(liv);
	}
	
	/*get all employees*/
	@GetMapping("/affichall")
	public List<Livreur> getAllLivreur(){
		
		return iLivreurService.findall();
		
	}
	
	
  @PutMapping("/modifier")
	
	public Livreur updateLiv(@RequestBody Livreur liv)
	{
		return iLivreurService.updateLiv(liv);
	}
  
  @DeleteMapping("/delete/{id}")
	
	public void  delete(@PathVariable(value="id") long id)
	{
	  iLivreurService.delete(id);
	}
	
	
	

}
