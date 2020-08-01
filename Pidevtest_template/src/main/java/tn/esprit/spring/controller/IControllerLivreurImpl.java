package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.services.ILivreurService;


@Controller
public class IControllerLivreurImpl {
 
	
	@Autowired
	ILivreurService iLivreurService;
	
	

	/*Enregistrer un livreur*/
	
	
	public Livreur createLivreur( Livreur liv)
	{
		return iLivreurService.save(liv);
	}
	
	/*get all employees*/

	public List<Livreur> getAllLivreur(){
		
		return iLivreurService.findall();
		
	}
	
	

	
	public Livreur updateLiv(Livreur liv)
	{
		return iLivreurService.updateLiv(liv);
	}

	
	public void  delete( long id)
	{
		iLivreurService.delete(id);
	}
	
	
	

}
