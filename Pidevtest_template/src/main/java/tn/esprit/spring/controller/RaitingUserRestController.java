package tn.esprit.spring.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.RateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.repository.RaitingUserRepository;
import tn.esprit.spring.services.RaitingUserService;

@Scope(value = "session")
@Controller(value = "RaitingUserRestController")
@ELBeanName(value = "RaitingUserRestController")

@RestController
public class RaitingUserRestController {

	@Autowired
	RaitingUserRepository raitingUserRepository;
	@Autowired
	RaitingUserService raitingUserService;
	@Autowired
	UserRestController userRestController;
	private int etoile;

	private int nbretoile;

	public void changed() {

		System.out.println(etoile);
	}

	public void onrate(RateEvent<Integer> rateEvent) {
		
		System.out.println(rateEvent.getRating());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event",
				"You rated:" + rateEvent.getRating());
		FacesContext.getCurrentInstance().addMessage(null, message);
		Client ClientConnecte = userRestController.getClientConnecte();
		Client ClientInvite = userRestController.getInviteClient();
		raitingUserService.changedRate(ClientConnecte, ClientInvite, rateEvent.getRating());

	}

	public void oncancel() {
		Client ClientConnecte = userRestController.getClientConnecte();
		Client ClientInvite = userRestController.getInviteClient();
		raitingUserService.DeleteRate(ClientConnecte, ClientInvite);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@GetMapping("/Insertrate/{nbretoile}/{user_connecte_id}/{user_rate_id}") // http://localhost:8081/SpringMVC/servlet/Insertrate
	@ResponseBody
	public String addUser(@PathVariable("nbretoile") int nbretoile,
			@PathVariable("user_connecte_id") Long user_connecte_id, @PathVariable("user_rate_id") Long user_rate_id) { //
		return "";
	}

	public int getNbretoile() {
		Client ClientConnecte = userRestController.getClientConnecte();
		Client ClientInvite = userRestController.getInviteClient();
		System.out.println(ClientConnecte.getId() + "          " + ClientInvite.getId());
		int x = raitingUserService.getRate(ClientConnecte.getId(), ClientInvite.getId());

		return x;

	}

	public void setNbretoile(int nbretoile) {
		this.nbretoile = nbretoile;
	}

	public int getEtoile() {
		return etoile;
	}

	public void setEtoile(int etoile) {
		this.etoile = etoile;
	}

}
