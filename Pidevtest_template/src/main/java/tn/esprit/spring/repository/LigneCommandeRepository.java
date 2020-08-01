package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Furniture;
import tn.esprit.spring.entities.LigneCommande;

@Repository

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

	// @Query(value = "SELECT
	// f.furniture_name,l.quantity,f.furniture_price,l.quantity*f.furniture_price
	// FROM t_ligne_commande l join t_furniture f on
	// f.furniture_id=l.furniture_furniture_id join user u on
	// l.id=u.ligne_commande_id ",nativeQuery = true)
//	public L panierParIdclient(@Param("idc")long idc);
//	@Query(value = "SELECT * FROM t_ligne_commande l JOIN t_commande c on l.commande_id=c.id  WHERE l.furniture_furniture_id=?1 AND c.id_user=?2 AND l.status='en cours' and c.id !=?3 ", nativeQuery = true)
//	public LigneCommande findLigneCommande(int idFurniture,Long idClient,Long idCommande);
//	@Query(value = "SELECT * FROM t_ligne_commande l JOIN t_commande c on l.commande_id=c.id  WHERE l.furniture_furniture_id=?1 AND c.id_user=?2 AND c.status='en cours'", nativeQuery = true)
//	public LigneCommande findLigneCommande(int idFurniture,Long idClient);
//	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO `ligne_commande_furnitures`(`ligne_commande_id`, `furnitures`, `furnitures_key`) "
			+ "VALUES (:idCart,:quantity,:idProduct)", nativeQuery = true)
	public int setPannier(@Param("idProduct") Integer idProduct, @Param("quantity") Integer quantity,
			@Param("idCart") Long idCart);

//select f from  LigneCommande f join	
	@Query(value = "SELECT f.* FROM t_ligne_commande l JOIN ligne_commande_furnitures c on c.ligne_commande_id=l.id join t_furniture f"
			+ " on c.furnitures_key=f.furniture_id" + "  WHERE l.id=:idCard", nativeQuery = true)
	public List<String> getFurnitureByCard(@Param("idCard") Long idCard);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO `t_ligne_commande`(`id`, `user_id`)" + "VALUES (:idCart,:idUser)", nativeQuery = true)
	public int addCartByUserId(@Param("idCart") Long idCart, @Param("idUser") Long idUser);

	@Query(value = "SELECT count(*) FROM ligne_commande_furnitures l WHERE l.ligne_commande_id=:idCard", nativeQuery = true)
	public int countFurnitureByCart(@Param("idCard") Long idCard);

	@Query("select f from LigneCommande f where f.user.id=:id")
	public List<LigneCommande> getLigneCommandByUserId(@Param("id") Long idUser);

	@Query("select f from LigneCommande f where f.user.id=:id")
	public LigneCommande getLigneCommandByUserId1(@Param("id") Long idUser);
	//
}