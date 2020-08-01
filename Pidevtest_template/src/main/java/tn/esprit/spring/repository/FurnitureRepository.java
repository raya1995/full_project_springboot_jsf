package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Furniture;

public interface FurnitureRepository extends CrudRepository<Furniture, Integer> {

	@Query("SELECT name FROM Furniture")
	public List<String> furnitureNames();
//"UPDATE Friend f SET f.verifsig =true WHERE (select count(f) from Friend f where (f.user1=:id or f.user2=:id) and f.idsign!=:id and f.sigblock=true and f.verifsig=false)>1 and(f.user1=:id or f.user2=:id)and f.sigblock=true and f.verifsig=false")

	@Query("SELECT count(*) FROM Furniture")
	public int countFurn();
	/*
	 * @Query("Select " + "DISTINCT fur from Furniture fur " +
	 * "join fur.shoppingCards card " + "where card=:shoppingCards") public
	 * List<Furniture> getAllFurnitureByCard(@Param("shoppingCards") ShoppingCard
	 * shoppingCards); //
	 */
//	 @Query(value="UPDATE t_furniture t INNER JOIN  ligne_commande_furnitures l ON t.furniture_id=l.furnitures_key "
//	 		+ "INNER join t_ligne_commande lc ON lc.id=l.ligne_commande_id "
//	 		+ "INNER join t_commande c on c.id=lc.commande_id  SET t.furniture_quantity=(t.furniture_quantity-l.furnitures)"
//	 		+ " WHERE t.furniture_id=1 WHERE t.furniture_id=?1",nativeQuery=true)
//	 public int updateQuantityFurniture(@Param("id") int furnitureId);

	@Query(value = "SELECT l.furnitures FROM ligne_commande_furnitures l WHERE l.furnitures_key=?1 AND l.ligne_commande_id=?1", nativeQuery = true)
	public int nombreProduitByCart(@Param("idProd") int idProd, @Param("idCart") long idCart);

	@Query(value = "SELECT * FROM t_furniture t join ligne_commande_furnitures c "
			+ "ON t.furniture_id=c.furnitures_key join t_commande tc"
			+ " on c.ligne_commande_id=tc.ligne_commande_id WHERE tc.id_user=1", nativeQuery = true)
	public List<String> getFurnitureByCards();
}
