package tn.esprit.spring.services;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entities.Reclamation;


public interface ReclamtionService {
	public void ajouterReclamation(Reclamation reclamation);
	void deleteReclamation(String id);
	Reclamation findReclamation(String id);
	List<Reclamation> afficherReclamation();
	public void affecterreclamationUser(String idReclamation,String IdUser);
	public void ajouterReclamationuser(Reclamation reclamation,String IdUser);
	
	

}
