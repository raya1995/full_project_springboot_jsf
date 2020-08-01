package tn.esprit.spring.controller;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ReclamationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.ReclamationService;



@Scope(value = "session")
@Controller(value = "ReclamationController")
@ELBeanName(value = "ReclamationController")

@RestController
public class ReclamationController {
	@Autowired
	ReclamationService reclamtionService;
	@Autowired
	UserRestController userRestController;
	@Autowired
	ReclamationRepository reclamtionRepository;
	@Autowired
	UserRepository userRepository;

	private List<Reclamation> reclamations;
	private Reclamation reclamation;
	private String description;
	private User userId;
	private String email;
	private Long id; 
	

	

	/*public User getUser(Long id) {
		List<User> users = (List<User>) userRepository.findAll();
		User use= new User();
		for (User us:users) {
			if(us.getId().equals(id)) {
				use=us;
			}
			
			
		}
		return use;
	}


	public void setUser(User user) {
		this.user = user;
	}*/


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public List<Reclamation> getReclamations() {
		return reclamtionService.afficherReclamation();
	}

	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public Reclamation getReclamation() {
		return reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String sendReclamation() {
		String navigateTo=null;
		
		if(description!=null) {
			Client ClientConnecte = userRestController.getInviteClient();
			System.out.println(description);
			//Reclamation rec = new Reclamation(null, description);
			//reclamtionService.ajouterReclamationuser(rec, ClientConnecte.getId().toString());
		}
		navigateTo = "/pages/client/Users1.xhtml?faces-redirect=true";
		return navigateTo;
		
	
	}
	public void annulreclamation(Long id) {
		System.out.println(id);
		reclamtionService.deleteReclamation(id);
		
		
		
	}
	

}
