package tn.esprit.spring.services;







import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.repository.FurnitureRepository;
import tn.esprit.spring.repository.LigneCommandeRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class LigneCommandeServiceImpl implements ILigneCommandeService{


	@Autowired
	FurnitureRepository furnitureRepository;
	
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;


//	@Override
//	public List<List<String>> panierParIdclient( long iduser) {
//		return ligneCommandeRepository.panierParIdclient(iduser);
//
//	}
//	@Override
//	public LigneCommande findOne(Long id) {
//		return ligneCommandeRepository.getOne(id);
//	}
//	
	@Override
	public List<LigneCommande> getAllProductByCart() {
		return  (List<LigneCommande>) ligneCommandeRepository.findAll();
	}
//	@Override
//	public LigneCommande findLigneCommande(int idFurniture,Long idClient,Long idCommande){
//		return ligneCommandeRepository.findLigneCommande(idFurniture, idClient,idCommande);
//	}
//
//	
	
	
	@Override
	public LigneCommande  saveLigneCommande(Long idUser) {
		//LigneCommande cardManage = ligneCommandeRepository.findById(panierId).get();
		LigneCommande c = new LigneCommande();
		

			//Commande commandeManage = commandeRepository.getCommandeByIdUser(idUser).get(0);
			User userMAnage = userRepository.findById(idUser).get();
			System.out.println("aaa"+userMAnage);
			

			c.setUser(userMAnage);
			//c.setCommande(commandeManage);
		
		return  ligneCommandeRepository.save(c);
	}


	@Override
	public int countFurnitureByCart(Long idCard) {
		
		return ligneCommandeRepository.countFurnitureByCart(idCard);
	}

	
	
	
//	@Override
//	public Double PrixTotalCommande(long iduser) {
//	double sum = 0D;
//	Commande c = commandeRepository.CommandeEnCoursParClient(iduser);
//
//	sum+=c.getTotal();
//	return sum;
//	
//    }
//	

	
	

	

	
//	@Override
//	public List<List<String>> AjouterAuPanier(int idprod, long iduser, LigneCommande lc) {
//		List<List<String>>List=ligneCommandeRepository.panierParIdclient(iduser);
//		System.out.println(List);
//		Furniture f = furnitureRepository.findById(idprod).get();
//		Commande c = commandeRepository.CommandeEnCoursParClient(iduser);
//		LigneCommande l=ligneCommandeRepository.findLigneCommande(idprod, iduser);
//		User u = userRepository.findById(iduser).get();
//		
//		 if(List.isEmpty())
//		 {
//			 float total=0; 
//			 Commande  c1= new Commande(); 
//				c1.setIdUser(u);
//			 ZoneId zid = ZoneId.of("Africa/Tunis");
//			 
//			 c1.setDate(LocalDate.now(zid));
//				c1.setStatus("en cours");
//				c1.setTypedePayment("en cours");
//				c1.setRemise("non");
//				c1.setMontant(total);
//				lc.setPrice(f.getPrice());
//			//	 lc.setStatus("en cours");
//				 lc.setCommande(c1);
//				 lc.setFurniture(f);;
//				 ligneCommandeRepository.save(lc);
//				 total=(float) (lc.getPrice()*lc.getQuantity());
//				c1.setMontant(total);
//				 commandeRepository.save(c1);
//		 }
//		 
//		 else if ((c!=null))
//		 {
//			
//				if(l!=null){
//					l.setQuantity(l.getQuantity()+1);
//					ligneCommandeRepository.save(l);
//			 }
//				else
//				{
//			 lc.setCommande(c);
//				lc.setPrice(f.getPrice());
//				// lc.setStatus("en cours");
//				 lc.setFurniture(f);
//				 ligneCommandeRepository.save(lc);
//				}
//				double nombre=commandeRepository.NombreDeCommandeParUser(iduser);
//				if(nombre>500)
//				{
//				double a= PrixTotalCommande(iduser);
//				c.setMontant((float)a);
//				//c.setPourcentageDeRemise(0.3);
//				c.setPourcentageDeRemise(a-c.getMontant()*0.3);
//				ZoneId zid = ZoneId.of("Africa/Tunis");
//				c.setDate(LocalDate.now(zid));
//				commandeRepository.remise(iduser);
//				commandeRepository.save(c);
//				}
//		 
//				else
//				{
//					double a= PrixTotalCommande(iduser);
//					if(a>5000)
//					{
//						c.setMontant((float)a);
//					//c.setMontant((float) ((float) a-(c.getPourcentageDeRemise()*a)));
//						c.setPourcentageDeRemise(a-c.getMontant()*0.3);
//					ZoneId zid = ZoneId.of("Africa/Tunis");
//					c.setDate(LocalDate.now(zid));
//					commandeRepository.save(c);
//				}
//					else
//					{
//						c.setMontant((float) a);
//						ZoneId zid = ZoneId.of("Africa/Tunis");
//						c.setDate(LocalDate.now(zid));
//						commandeRepository.save(c);
//					}
//				}
//				}  
//		return ligneCommandeRepository.panierParIdclient(iduser) ;
//	}


	
	
}
