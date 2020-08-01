package tn.esprit.spring.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.ChargeRequest;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.Delivery.StateDelivery;
import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.repository.DeliveryRepository;
import tn.esprit.spring.repository.FurnitureRepository;
import tn.esprit.spring.repository.LigneCommandeRepository;
import tn.esprit.spring.repository.LivreurRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class CommandeServiceImpl implements ICommandeService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	FurnitureRepository furnitureRepository;
	@Autowired
	ICommandeService icommandeService;
	@Autowired
	IDeliveyService ideliveryService;
	@Autowired
	DeliveryRepository deliveryRepository;
	@Autowired
	LivreurRepository livreurRepository;
	
	@Autowired
	StripeService stripeService;
	@Override
	public Commande saveCommande(Long idUser, long idCart, String typePayement) {
		
		Commande c = new Commande();
		User userManage = userRepository.findById(idUser).get();
		LigneCommande cardManag = ligneCommandeRepository.findById(idCart).get();
		System.out.println("tttttt"+c);
	
Delivery d = deliveryRepository.findById(c.getDeliveries().getDeliveryId()).get();
		
		Delivery deliveryManage = deliveryRepository.findById(c.getDeliveries().getDeliveryId()).get();
		double nombre = commandeRepository.NombredeProduitByLigneCommande(idCart);
		c.setIdUser(userManage);
		c.setLigneCommande(cardManag);
		c.setStatus("IN_PROGRESS");
		System.out.println("aaa" + userManage);
		System.out.println("bbb" + cardManag);
		System.out.println("ccc" + nombre);
		ZoneId zid = ZoneId.of("Africa/Tunis");
		c.setDate(LocalDate.now(zid));
		
		commandeRepository.save(c);

		if (nombre > 60) {
			double t = icommandeService.prixTotaleLigneCommande(idCart);
			double p = icommandeService.PourcentageRemiseCommande(idCart, c.getId());
			System.out.println("tttt" + t);
			System.out.println("pppp" + p);
			c.setRemise("true");
			c.setTotal(t);
			c.setPourcentageDeRemise(p);
			double m = c.getTotal() - c.getPourcentageDeRemise();
			System.out.println("mmm" + m);
			c.setMontant(m);
			//c.setTypedePayment("EN_LIGNES");
			 commandeRepository.save(c);
			if (typePayement.equals("EN_LIGNE")) {
			//	if(stripeService.confirm(id))
				// appel service stripe
				// condition if payement validé
				c.setStatus("CONFIRMED");
				c.setTypedePayment(typePayement);
				c.setDeliveries(d);
				// condition if payement non validé
				// c.setStatus("IN_PROGRESS");
				commandeRepository.save(c);
			}
			 if (typePayement.equals("PORTE_A_PORTE")) {
		//	deliveryService.passerLivraison(d);
				 if(d.getStateDelivery().equals(d.getStateDelivery().Approved)){
					 
					 c.setStatus("CONFIRMED");
						c.setTypedePayment(typePayement); 
				 }
				 else if(d.getStateDelivery().equals(d.getStateDelivery().IN_PROGRESS)){
					 c.setStatus("IN_PROGRESS");
					 c.setTypedePayment(typePayement); 
					 
				 }
				// appel service de livraison
				// condition if livraison apprové
				
				// condition if livraison non-apprové
				
				commandeRepository.save(c);

			}
			commandeRepository.save(c);
			// return c;
			//
		} else {

			c.setRemise("false");

			c.setTotal(icommandeService.prixTotaleLigneCommande(idCart));
			c.setMontant(c.getTotal());

			commandeRepository.save(c);

		}

		if (typePayement.equals("EN_LIGNE")) {
			// appel service stripe
			// condition if payement validééé
			c.setStatus("CONFIRMED");
			c.setTypedePayment(typePayement);
			// condition if payement non validé
			// c.setStatus("IN_PROGRESS");
			commandeRepository.save(c);
		} else if (typePayement.equals("PORTE_A_PORTE")) {
			
			 if(d.getStateDelivery().equals(d.getStateDelivery().Approved)){
				 
				 c.setStatus("CONFIRMED");
					c.setTypedePayment(typePayement); 
			 }
			 else if(d.getStateDelivery().equals(d.getStateDelivery().IN_PROGRESS)){
				 c.setStatus("IN_PROGRESS");
				 c.setTypedePayment(typePayement); 
				 
			 }
			// appel service de livraison
			// condition if livraison apprové
		//	c.setStatus("CONFIRMED");
			//c.setTypedePayment(typePayement);
			// condition if livraison non-apprové
			// c.setStatus("IN_PROGRESS");
			commandeRepository.save(c);

		}
		commandeRepository.save(c);

		// return commandeRepository.save(c);

		return c;

	}
	
	
	@Override
	public Commande saveCommande1(Long idUser, long idCart, String typePayement) {
		
		Commande c = new Commande();
		User userManage = userRepository.findById(idUser).get();
		//LigneCommande cardManag = ligneCommandeRepository.findById(idCart).get();
		Delivery d=new Delivery("youssefgabes","gabes","desc",StateDelivery.IN_PROGRESS);

		System.out.println("tttttt"+c);
		//Delivery deli = new Delivery();
		//deli = deliveryRepository.findById(c.getDeliveries().getDeliveryId()).get();
		
		//Delivery deliveryManage = deliveryRepository.findById(c.getDeliveries().getDeliveryId()).get();
		double nombre = commandeRepository.NombredeProduitByLigneCommande(idCart);
		c.setIdUser(userManage);
		//c.setLigneCommande(cardManag);
		c.setStatus("IN_PROGRESS");
		System.out.println("aaa" + userManage);
		//System.out.println("bbb" + cardManag);
		System.out.println("ccc" + nombre);
		ZoneId zid = ZoneId.of("Africa/Tunis");
		c.setDate(LocalDate.now(zid));
		
		//commandeRepository.save(c);

		if (nombre > 20) {
			double t = icommandeService.prixTotaleLigneCommande(idCart);
			double p = icommandeService.PourcentageRemiseCommande(idCart, c.getId());
			System.out.println("tttt" + t);
			System.out.println("pppp" + p);
			c.setRemise("true");
			c.setTotal(6000);
			c.setPourcentageDeRemise(p);
			double m = c.getTotal() - c.getPourcentageDeRemise();
			System.out.println("mmm" + m);
			c.setMontant(m);
			//c.setTypedePayment("EN_LIGNES");
			// commandeRepository.save(c);
			if (typePayement.equals("EN_LIGNE")) {
			//	if(stripeService.confirm(id))
				// appel service stripe
				// condition if payement validé
				c.setStatus("CONFIRMED");
				c.setTypedePayment(typePayement);
				
				ideliveryService.passerLivraison(d);
				ideliveryService.affecterLivraisonALivreur(18L, d.getDeliveryId());
				
				
				
				// condition if payement non validé
				// c.setStatus("IN_PROGRESS");
				commandeRepository.save(c);
			}
			 if (typePayement.equals("PORTE_A_PORTE")) {
		//	deliveryService.passerLivraison(d);
				 if(d.getStateDelivery().equals(d.getStateDelivery().Approved)){
					 
					 c.setStatus("CONFIRMED");
						c.setTypedePayment(typePayement); 
				 }
				 else if(d.getStateDelivery().equals(d.getStateDelivery().IN_PROGRESS)){
					 c.setStatus("IN_PROGRESS");
					 c.setTypedePayment(typePayement); 
					 
				 }
				// appel service de livraison
				// condition if livraison apprové
				
				// condition if livraison non-apprové
				
				commandeRepository.save(c);

			}
			commandeRepository.save(c);
			// return c;
			//
		} else {

			c.setRemise("false");

			//c.setTotal(icommandeService.prixTotaleLigneCommande(idCart));
		c.setTotal(5000);
			c.setMontant(c.getTotal());

			commandeRepository.save(c);

		}

		if (typePayement.equals("EN_LIGNE")) {
			// appel service stripe
			// condition if payement validééé
			c.setStatus("CONFIRMED");
			c.setTypedePayment(typePayement);
			// condition if payement non validé
			// c.setStatus("IN_PROGRESS");
			commandeRepository.save(c);
		} else if (typePayement.equals("PORTE_A_PORTE")) {
			
			 if(d.getStateDelivery().equals(d.getStateDelivery().Approved)){
				 
				 c.setStatus("CONFIRMED");
					c.setTypedePayment(typePayement); 
			 }
			 else if(d.getStateDelivery().equals(d.getStateDelivery().IN_PROGRESS)){
				 c.setStatus("IN_PROGRESS");
				 c.setTypedePayment(typePayement); 
				 
			 }
			// appel service de livraison
			// condition if livraison apprové
		//	c.setStatus("CONFIRMED");
			//c.setTypedePayment(typePayement);
			// condition if livraison non-apprové
			// c.setStatus("IN_PROGRESS");
			commandeRepository.save(c);

		}
		commandeRepository.save(c);

		// return commandeRepository.save(c);

		return c;

	}

	@Override
public void saveCommande2(Long idUser, long idCart, String typePayement) {
		System.out.println("hhhhhhhhhhhh"+idUser +idCart     +typePayement);

		Commande c = new Commande();
		User userManage = userRepository.findById(idUser).get();
		System.out.println("ssssssssss"+userManage);
		//LigneCommande cardManag = ligneCommandeRepository.findById(idCart).get();
		//System.out.println("111111111"+cardManag);

		System.out.println("yyyyyyyyy");
		//double nombre = commandeRepository.NombredeProduitByLigneCommande(idCart);
		c.setIdUser(userManage);
		System.out.println("tttttttttt");
		//c.setLigneCommande(cardManag);
		c.setStatus("IN_PROGRESS");
		c.setPourcentageDeRemise(10);
		c.setRemise("true");
		c.setTotal(5000);
		c.setTypedePayment("IN_PROGRESS");
		c.setMontant(4500);
		
		//c.setStatus(StateDelivery.IN_PROGRESS);
		commandeRepository.save(c);
		//return c;
		
	
		
}
		//commandeRepository.save(c);


	
	
	@Transactional
	@Override
	public String addCommandeFurniture(Long idCommande, int idProduct) {
		// bech na3mlou insertion fi table de jointure ;

		commandeRepository.setCommandeFurniture(idCommande, idProduct);

		// LigneCommande card =
		// ligneCommandeRepository.findById(panierId).get();
		// Furniture furniture =
		// furnitureRepository.findById(furnitureId).get();

		return "product added";
	}

	@Override
	public List<String> getCommandeProductNameByIdUser(Long idUser) {

		return commandeRepository.getCommandeProductNameByIdUser(idUser);
	}

	@Override
	public double PourcentageRemiseCommande(Long idCart, Long idCmd) {
		Commande c = commandeRepository.findById(idCmd).get();
		// double
		// nombre=commandeRepository.NombredeProduitByLigneCommande(idCart);
		double somme = prixTotaleLigneCommande(idCart);
		
		if(somme>=200 && somme<=499){
			double pourcentage = (somme -somme*0.1);
			return pourcentage;
		}
		else if(somme>=500 && somme<=999){
			
			double pourcentage = (somme -somme*0.15);
			return pourcentage;
		}
		else if(somme>=1000){
			double pourcentage = (somme -somme*0.20);
			return pourcentage;
		}
		else{
		double pourcentage=(somme);
		return pourcentage;
		}
	}

	@Override
	public double prixTotaleLigneCommande(Long idCart) {

		return commandeRepository.getPrixTotalLigneCommande(idCart);

	}

}
