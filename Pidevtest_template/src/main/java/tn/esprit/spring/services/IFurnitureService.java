package tn.esprit.spring.services;

import java.util.List;

import org.primefaces.model.file.UploadedFiles;

import tn.esprit.spring.entities.Furniture;


public interface IFurnitureService {
	public int ajouterMeuble(Furniture furniture);
	public List<Furniture> getAllFurniture();
	public List<String> getAllFurnitureNamesJPQL();
	public void deleteFurnitureById(int furnitureId);

	public int getNombreFurnitureJPQL();
	//public List<Furniture> getAllFurnitureByCard(ShoppingCard card);
	public String addMeubleDansPanier12(int furnitureId,int quantity, long panierId);
	public String incrementMeubleDansPanier12(int furnitureId,int quantity, long panierId);
	public String deleteMeubleFromPanier12(int furnitureId, long panierId);
	public List<String> getFurnitureByCard(Long idCard);
	public int getQuantityFurnitureByCart(int idProd,long idCart);
	public int updateQuantityFurnitureAfterCommande(int idProd, long idCmd);
	public void addProduitWithImage(Furniture p, UploadedFiles files);
}
