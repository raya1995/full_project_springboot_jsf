package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.ReclamationRepository;
import tn.esprit.spring.repository.ReclamtionRepository;

@Service
public class ReclamationServiceImpl implements ReclamationService {

	@Autowired
	ReclamtionRepository rec;
	

	@Autowired
	ClientRepository cl;
	@Autowired
	UserService us;
	@Autowired
	Filter filter;

	private static final Logger l = LogManager.getLogger(ReclamationServiceImpl.class);


	@Override
	public void addReclamation(Reclamation r) {
		rec.save(r);
	}

	@Override
	public List<Reclamation> retrieveAllReclamations() {
		return (List<Reclamation>) rec.findAll();
	}


	@Override
	public void deleteReclamation(long id) {
		rec.deleteById(id);
	}
	
	
	
	@Override
	public void ajouterReclamation(Reclamation reclamation) {

			rec.save(reclamation);
			
		
		
	}
	
	@Override
	public Reclamation findReclamation(String id) {
		Reclamation reclama= new Reclamation();
		Long j = Long.parseLong(id);
		List<Reclamation> reclamations =(List<Reclamation>) rec.findAll();
		for(Reclamation reclamation:reclamations) {
			if(reclamation.getId()==(j)) {
				l.info("user ++:"+reclamation);
				reclama=reclamation;
			}
		}
		return reclama;
	}
	@Override
	public List<Reclamation> afficherReclamation() {
		List<Reclamation> Reclamations =(List<Reclamation>) rec.findAll();
		for(Reclamation reclamation:Reclamations) {
			l.info("Reclamation ++:"+reclamation);
		}
		return Reclamations;
	}
	@Override
	public void affecterreclamationUser(String idReclamation, String IdUser) {
		User UserManageEntity = cl.findById(Long.parseLong(IdUser)).get();
		Reclamation ReclamationmanagerEntity= rec.findById(Long.parseLong(idReclamation)).get();
		ReclamationmanagerEntity.setUserId(UserManageEntity);
		
		
		
		rec.save(ReclamationmanagerEntity);
		
	}
	@Override
	public void ajouterReclamationuser(Reclamation reclamation, String IdUser) {
		int x = 0;
		rec.save(reclamation);
		long idReclamation=reclamation.getId();
		User UserManageEntity = cl.findById(Long.parseLong(IdUser)).get();
		Reclamation ReclamationmanagerEntity= rec.findById(idReclamation).get();
		ReclamationmanagerEntity.setUserId(UserManageEntity);
		rec.save(ReclamationmanagerEntity);
		List<Reclamation> Reclamations =(List<Reclamation>) rec.findAll();
		for(Reclamation recc:Reclamations) {
			
			if(recc.getUserId().getId().equals(Long.parseLong(IdUser))) {
				x=x+1;
			}
		}
		Client blockeduser = cl.findById(Long.parseLong(IdUser)).get();
		if(x>=3) {
			//System.out.println("xxxxxxxxxxxxxxxxxx"+blockeduser.isBlock()+"compte bloke");
			blockeduser.setBlock(true);
			blockeduser.setDescriptionBlock("you have 3 reclamation try to connect with th adminstation");
			cl.save(blockeduser);
			
		}
		
	}

	@Override
	public void deleteReclamation(Long id) {
		// TODO Auto-generated method stub
		
	}

	
	
}

