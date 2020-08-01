package tn.esprit.spring.services;

import java.util.List;
import java.util.Map;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.spring.Utils.AppConstants;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.entities.FurnitureState;

import tn.esprit.spring.entities.LigneCommande;
import tn.esprit.spring.repository.CommandeRepository;
import tn.esprit.spring.repository.FurnitureRepository;
import tn.esprit.spring.repository.LigneCommandeRepository;

@Service
public class FurnitureServiceImpl implements IFurnitureService{

	@Autowired
	FurnitureRepository furnitureRepository;
	
	@Autowired
	 LigneCommandeRepository ligneCommandeRepository ;
	@Autowired
	CommandeRepository commandeRepository;
//	@Autowired
//	FileStorageServiceImpl fileStorageServiceImpl;
//	
//	
	@Override
	public int ajouterMeuble(Furniture furniture) {
		
		furnitureRepository.save(furniture);
		
		
		
		return furniture.getId();
	}



	@Override
	public List<Furniture> getAllFurniture() {
	
		return (List<Furniture>) furnitureRepository.findAll();
	}



	@Override
	public List<String> getAllFurnitureNamesJPQL() {
		
		return  furnitureRepository.furnitureNames();
	}



	@Override
	public void deleteFurnitureById(int furnitureId) {
		furnitureRepository.delete(furnitureRepository.findById(furnitureId).get());
		
	}

	
	@Transactional
	@Override
	public String addMeubleDansPanier12(int furnitureId,int quantity, long panierId) {
		// bech na3mlou insertion fi table de jointure ;
		
		ligneCommandeRepository.setPannier(furnitureId,quantity, panierId);
		
//		LigneCommande card = ligneCommandeRepository.findById(panierId).get();
//		Furniture furniture = furnitureRepository.findById(furnitureId).get();
		
		return "product added";
	}
	
	
	@Override
	public String incrementMeubleDansPanier12(int furnitureId,int quantity, long panierId) {
		
		LigneCommande card = ligneCommandeRepository.findById(panierId).get();
		Furniture furniture = furnitureRepository.findById(furnitureId).get();
		Map<Furniture,Long> map =card.getFurnitures();

		map.put(furniture, (long) quantity);
	//	 map.remove(furniture);
		card.setFurnitures(map);
		System.out.println("mmlmlml"+card.getFurnitures());
		System.out.println(card.getFurnitures().values());
		ligneCommandeRepository.save(card);
		
		return "product added";
	}
	
	@Override
	public String deleteMeubleFromPanier12(int furnitureId, long panierId) {
		
		LigneCommande card = ligneCommandeRepository.findById(panierId).get();
		Furniture furniture = furnitureRepository.findById(furnitureId).get();
		Map<Furniture,Long> map =card.getFurnitures();
		 map.remove(furniture);
		card.setFurnitures(map);
		
		ligneCommandeRepository.save(card);
		
		return "product deleted";
	}
	
	
	//
	
	
	
	
	
	


	@Override
	public int getNombreFurnitureJPQL() {
	
		return furnitureRepository.countFurn();
	}



	@Override
	public List<String> getFurnitureByCard(Long idCard) {
		// TODO Auto-generated method stub
		return ligneCommandeRepository.getFurnitureByCard(idCard);
		
	}



	@Override
	public int getQuantityFurnitureByCart(int idProd, long idCart) {
		return furnitureRepository.nombreProduitByCart(idProd, idCart);
	}



	@Override
	public int updateQuantityFurnitureAfterCommande(int idProd, long idCmd) {
		Commande c =commandeRepository.findById(idCmd).get();
		Furniture furniture = furnitureRepository.findById(idProd).get();
		int nombreByCart= getQuantityFurnitureByCart(idProd, c.getLigneCommande().getId());
		int nmobreFurniture= furniture.getQuantity();
	
		if(c.getStatus().equals("CONFIRMED")){
			
			nmobreFurniture=nmobreFurniture-nombreByCart;
			System.out.println("nbr===="+nmobreFurniture);
			System.out.println("test====="+nombreByCart);
			furniture.setQuantity(nmobreFurniture);
			furnitureRepository.save(furniture);
			if( nmobreFurniture==0){
				
				furniture.setState(FurnitureState.Inactive);
				System.out.println("ttttttttttttt"+furniture.getState());
				furnitureRepository.save(furniture);
			}
	
	//	return (nmobreFurniture);
			
		}
//		else if( nmobreFurniture==0){
//			
//			furniture.setState(FurnitureState.Inactive);
//			System.out.println("ttttttttttttt"+furniture.getState());
//			furnitureRepository.save(furniture);
//		}
		furnitureRepository.save(furniture);
		return (nmobreFurniture);

	}



	@Override
	public void addProduitWithImage(Furniture p, UploadedFiles files) {
	
for (UploadedFile f : files.getFiles()) {
 	//String newFileName = fileStorageServiceImpl.UploadImages(f);
 	//String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
	Furniture furniture = new Furniture();
	//furniture.setImage(fileDownloadUri);
	
furnitureRepository.save(p);	
}
//furnitureRepository.save(p);
	}



}
