package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.repository.FurnitureRepository;
import tn.esprit.spring.repository.LigneCommandeRepository;
import tn.esprit.spring.services.IFurnitureService;
import tn.esprit.spring.services.ILigneCommandeService;
import tn.esprit.spring.services.StripeService;

@Scope(value = "session")
@Controller(value ="commandeController")
@ELBeanName(value = "commandeController")
//@Join(path= "/", to = "/pages/furniture.jsf")
public class JSFControllerCommandeImpl {
	private String carta;
	private int expMonth;
	private int expYear;
	private String cvc;
	private long idCommande;
	@Autowired
	StripeService stripeService;
	@Autowired
	UserRestController urc;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	IFurnitureService iFurnitureService;
	@Autowired
	FurnitureRepository furnitureRepository;
	@Autowired
	ILigneCommandeService iLigneCommandeService;
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	
	public void Pay(long idCommande,long idc,String carta, int expMonth, int expYear, String cvc) throws AuthenticationException, 
	InvalidRequestException, CardException, StripeException{
	
		Client c= urc.getClientConnecte();
		Commande cmd = commandeRepository.commandeParIdclient1(c.getId());
		
		//LigneCommande ligne= ligneCommandeRepository.getLigneCommandByUserId1(c.getId());
		
		stripeService.Pay(cmd.getId(), idc, carta, expMonth, expYear, cvc);
		
		//iFurnitureService.deleteMeubleFromPanier12(1, ligne.getId());
		

		//iFurnitureService.updateQuantityFurnitureAfterCommande(1, cmd.getId());
		}


	
	public String getCarta() {
		return carta;
	}

	public void setCarta(String carta) {
		this.carta = carta;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}



	public long getIdCommande() {
		return idCommande;
	}



	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}
	
}
