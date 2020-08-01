package tn.esprit.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO `t_commande_furnitures`(`commande_id`, `furnitures_furniture_id`) "
			+ "VALUES (:idCommande,:idProduct)", nativeQuery = true)
	public int setCommandeFurniture(@Param("idCommande") Long idCommande, @Param("idProduct") Integer idProduct);

//	

	@Query(value = "SELECT count(*) FROM ligne_commande_furnitures l WHERE l.ligne_commande_id=?1", nativeQuery = true)
	public double NombredeProduitByLigneCommande(long idCart);

	@Query("SELECT c FROM Commande c WHERE c.idUser.id=:idUser")
	public List<Commande> getCommandeByIdUser(@Param("idUser") Long idUser);
//	
//	idC || id p  userId =panier id 
//					
//	1		1 		1
//	1		1		1	
//	1		2		1
//	2		1		1
//	2		1		1
//	2		1		1

	@Modifying
	@Transactional
	@Query("UPDATE Commande c set c.Remise='oui',c.status='en cours' where c.idUser.id=:id")
	public void SetRemiseByIdUser(@Param("id") long idUser);

	@Query(value = "SELECT f.furniture_name FROM t_furniture f JOIN t_commande_furnitures c on c.furnitures_furniture_id=f.furniture_id "
			+ "join t_commande tc on tc.id=c.commande_id WHERE tc.id_user=?1", nativeQuery = true)
	public List<String> getCommandeProductNameByIdUser(@Param("idUser") Long idUser);// 4 kressi ;2kressi ;3kressi

//@Query("SELECT c from Commande c WHERE c.idUser.id=?1 and c.status='en cours'")
//public Commande CommandeEnCoursParClient(long idUser);

	@Query(value = "SELECT SUM((l.furnitures)*(f.furniture_price)) FROM ligne_commande_furnitures l "
			+ "JOIN t_furniture f ON f.furniture_id=l.furnitures_key "
			+ "JOIN t_commande_furnitures c on c.furnitures_furniture_id=l.furnitures_key "
			+ "JOIN t_commande lc on lc.id=c.commande_id WHERE lc.id_user=?1", nativeQuery = true)
	public double getPrixTotalByCommandeByIdUser(@Param("idUser") Long idUser);

	@Query(value = "SELECT SUM(f.furniture_price*lcf.furnitures) FROM ligne_commande_furnitures lcf "
			+ "join  t_furniture f on lcf.furnitures_key=f.furniture_id  where lcf.ligne_commande_id=?1", nativeQuery = true)
	public double getPrixTotalLigneCommande(@Param("idCart") Long idCart);

	@Query(value = "SELECT f.furniture_name,l.furnitures,f.furniture_price,l.furnitures*f.furniture_price FROM ligne_commande_furnitures l"
			+ " join t_commande c on l.ligne_commande_id=c.ligne_commande_id "
			+ "join t_furniture f on f.furniture_id=l.furnitures_key WHERE c.id_user=1? and c.status='IN_PROGRESS'", nativeQuery = true)
	public List<List<String>> commandeParIdclient(@Param("idc") long idc);

	@Query(value = "SELECT * FROM t_commande WHERE id_user=1", nativeQuery = true)
	public Commande commandeParIdclient1(@Param("idc") long idc);

}