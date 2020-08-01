package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Factures;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.services.IFacturesService;

@Scope(value = "session")
@Controller(value = "FactureController")
@ELBeanName(value = "FactureController")
public class JSFfactureController {

	@Autowired
	IFacturesService FacturesService;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	UserRestController urc;
	
	private User user;
	private Factures facture;
	private int id;
	private double montant;

	private Date date_de_depart;
	private String type;
	private Commande commande;
	private List<Factures> listefacture;

	@Transactional
	public List<Factures> getAllfactures() {
		return FacturesService.getAllfactures();
	}

	@Transactional
	public String facturepdf() {
		Factures f=new Factures();
		long idF =FacturesService.ajouterFacture(f);
		System.out.println("aaa"+idF);
		Client c= urc.getClientConnecte();
		Commande cmd = commandeRepository.commandeParIdclient1(c.getId());
	
		FacturesService.affecterCommandeAFacture(idF,cmd.getId() );
		System.out.println("zaama");
		FacturesService.facturepdf(idF, cmd.getId() );
		
		return "/templateyoussef/checkout.xhtml?faces-redirect=true";
	}

	

	public IFacturesService getFacturesService() {
		return FacturesService;
	}

	public void setFacturesService(IFacturesService facturesService) {
		FacturesService = facturesService;
	}

	public Factures getFacture() {
		return facture;
	}

	public void setFacture(Factures facture) {
		this.facture = facture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDate_de_depart() {
		return date_de_depart;
	}

	public void setDate_de_depart(Date date_de_depart) {
		this.date_de_depart = date_de_depart;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<Factures> getListefacture() {
		return listefacture;
	}

	public void setListefacture(List<Factures> listefacture) {
		this.listefacture = listefacture;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
