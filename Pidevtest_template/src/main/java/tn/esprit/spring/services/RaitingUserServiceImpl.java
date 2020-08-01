package tn.esprit.spring.services;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.raitingUser;
import tn.esprit.spring.repository.RaitingUserRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class RaitingUserServiceImpl implements RaitingUserService {
	@Autowired
	RaitingUserRepository raitingUserRepository;
	@Autowired
	UserRepository userRepo;
	@Autowired
	IAdService adService;

	@Override
	public String addRate(String IDConnecte, String IdRate, int nbretoile) {
		/*
		 * Long Idcon = Long.parseLong(IDConnecte);
		 * 
		 * Long IdRa = Long.parseLong(IdRate); raitingUser raiting = new raitingUser();
		 * raiting = raitingUserRepository.findRaiting(Idcon, IdRa); if (nbretoile > 5)
		 * { return "max 5"; } if (raiting == null) {
		 * raitingUserRepository.Insertrate(nbretoile, Idcon, IdRa); return
		 * "rate ajoute avec succes"; } else { raiting.setNbretoile(nbretoile);
		 * raitingUserRepository.save(raiting); return "raiting update"; }
		 */
		return null;

	}

	@Override
	public String moyenneRaiting(String IDConnecte) {
		Long Idcon = Long.parseLong(IDConnecte);
		List<raitingUser> Raitings = raitingUserRepository.findRaitingwithConnctUser(Idcon);
		if (Raitings == null) {
			return "you dont have any raiting";
		}
		int x = 1;
		int y = 1;
		for (raitingUser user : Raitings) {

			y = y + 1;
			x = x + user.getNbretoile();

		}
		int moy = x % y;
		return "Moyen d'etoile = " + moy;
	}

	@Override
	public int getRate(Long IdConnecte, Long IdReate) {
		List<raitingUser> Raitings = (List<raitingUser>) raitingUserRepository.findAll();
		for (raitingUser ra : Raitings) {

			if (ra.getUserConnecte().getId().equals(IdConnecte)) {
				if (ra.getUserRate().getId().equals(IdReate)) {
					return ra.getNbretoile();
				}
			}
		}
		// int x= raitingUserRepository.findnbreEtile(IdConnecte, IdReate);

		return 0;
	}

	@Override
	public void changedRate(Client ClientConnecte, Client ClientInvite, int nbr) {

		List<raitingUser> Raitings = (List<raitingUser>) raitingUserRepository.findAll();
		// if( raitingUserRepository.findRaiting(ClientConnecte.getId(),
		// ClientInvite.getId())!=null) {
		Long x = raitingUserRepository.findRaiting(ClientConnecte.getId(), ClientInvite.getId());
		raitingUserRepository.Insertrate(x, nbr, ClientConnecte.getId(), ClientInvite.getId());
		// }
		// raitingUserRepository.Insertrate(null, nbr, ClientConnecte.getId(),
		// ClientInvite.getId());

		System.out.println("ccccccccccccccc");

	}

	@Override
	public void DeleteRate(Client ClientConnecte, Client ClientInvite) {
		raitingUserRepository.delete(ClientConnecte.getId(), ClientInvite.getId());
		// int x= raitingUserRepository.findnbreEtile(IdConnecte, IdReate);

	}

	@Override
	public int moyenneRaiting(Long Idcon) {

		List<raitingUser> Raitings = raitingUserRepository.findRaitingwithConnctUser(Idcon);

		int x = 1;
		int y = 1;
		for (raitingUser user : Raitings) {

			y = y + 1;
			x = x + user.getNbretoile();

		}
		int moy = x / y;
		return moy;
	}


	@Override
	public List<Ad> AnnnceOfBestRaiting() {
		HashMap<String, Integer> hash_map = new HashMap<String, Integer>();
		List<User> users = (List<User>) userRepo.findAll();
		List<Ad> myList = new ArrayList<>();
		for (User user : users) {
			if (user instanceof Client) {
				System.out.println(moyenneRaiting(user.getId()));
				hash_map.put(""+user.getId(), moyenneRaiting(user.getId()));
			}

		}
		System.out.println(hash_map);
		//hash_map.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(-5).forEach(System.out::println);
		
		for (Entry<String, Integer> m : hash_map.entrySet()) {
			int count=0;
		    if (m.getValue() ==4) {
		    	//stringsID.addAll(adService.afficherAnnonceFollow(Long.parseLong(""+m.getKey())));
		    	myList.addAll(adService.annonceuser(Long.parseLong(""+m.getKey())));
		        //System.out.println(adService.annonceuser(Long.parseLong(""+m.getKey())));
		        count=count+1;
		        
		        
		    }
		    if(count==5) {
	        	break;
	        }else {
	        	if(m.getValue() ==3) {
	        		myList.addAll(adService.annonceuser(Long.parseLong(""+m.getKey())));
	        		//System.out.println(adService.annonceuser(Long.parseLong(""+m.getKey())));
	        		//stringsID.add(""+m.getKey());
			        count=count+1;
	        	}if(count==5) {
	        		break;
	        	}else {
	        		if(m.getValue()==2) {
	        			myList.addAll(adService.annonceuser(Long.parseLong(""+m.getKey())));
	        			//System.out.println(adService.annonceuser(Long.parseLong(""+m.getKey())));
	        			//stringsID.add(""+m.getKey());
				        count=count+1;	        			
	        		}if(count==5) {
		        		break;
		        	}
	        	}
	        	
	        }
		    
		    
		    
		    
		}
		
		System.out.println(myList);
		return myList;
	}

}
