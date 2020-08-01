package tn.esprit.spring.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.BlockService;


@Scope(value = "session")
@Controller(value = "BlockRestController")
@ELBeanName(value = "BlockRestController")
@Named
@RequestScoped
@RestController
public class BlockRestController {
	@Autowired
	BlockService blockService;
	@Autowired
	UserRestController userRestController;
	@Autowired
	UserRepository userRepository;
	private String email;
	private int x=0;
	 private boolean value2;
	public static List<User> users;
	private Long id;
	private String picture;
	
	
	
	
	 private String[] selectedConsoles;
	 
	 
	 
	 
	 
	 public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getSelectedConsoles() {
		return selectedConsoles;
	}

	public void setSelectedConsoles(String[] selectedConsoles) {
		this.selectedConsoles = selectedConsoles;
	}

	@PostConstruct
	    public void init() {
		 User userBloked = userRestController.getBlokeduser();
		 List<User> list = blockService.randomfollowOrNot(userBloked.getId());
		 this.users = blockService.randomfollowOrNot(userBloked.getId());
	 }
	
	private void getUserPicture() {
		User userBloked = userRestController.getBlokeduser();
		System.out.println(userBloked);
		List<User> list = blockService.randomfollowOrNot(userBloked.getId());
		System.out.println(list);
		
		System.out.println(userBloked);
		this.users = blockService.randomfollowOrNot(userBloked.getId());

	}

	public List<User> getUsers() {
		
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	 public boolean isValue2() {
		return value2;
	}

	public void setValue2(boolean value2) {
		this.value2 = value2;
	}

	public String verif(Long id) {
		User userBloked = userRestController.getBlokeduser();
		System.out.println(id+"      "+userBloked.getId());
		if(blockService.verifId(userBloked.getId(), id)) {
			this.x=this.x+1;
			return "true";
			
		}
		else {
			this.x=this.x-1;
			return "false";
		}
	
		
		
		
	}
	public String  endverif() {
		
		//System.out.println(selectedConsoles.toString());
		if(this.x==2) {
			System.out.println("right answer");
			userRestController.setClientConnecte((Client)userRestController.getBlokeduser());
			User userBloked = userRestController.getBlokeduser();
			if(userBloked instanceof Client) {
				((Client) userBloked).setDescriptionBlock("nothing");
				((Client) userBloked).setNbre(0);
				((Client) userBloked).setBlock(false);
				userRepository.save(userBloked);
			}
			return "/pages/client/welcome.xhtml?faces-redirect=true";
		}else {
			User userBloked = userRestController.getBlokeduser();
			if(userBloked instanceof Client) {
				((Client) userBloked).setDescriptionBlock("forget password");
				userRepository.save(userBloked);
			}
		
			return "/login.xhtml?faces-redirect=true";
		
		}
	
			
		
	}
	
	

}
