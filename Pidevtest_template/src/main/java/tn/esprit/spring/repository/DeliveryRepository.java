package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Delivery;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

	@Query(value = "SELECT * from t_delivery d join t_commande c on c.deliveries_delivery_id=d.delivery_id"
			+ " WHERE c.id_user=1 and d.delivery_state='Approved'", nativeQuery = true)
	public List<Delivery> getDeliveryByUserAndState(@Param("idUSer") Long idUser, @Param("state") String state);

}
