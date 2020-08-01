package tn.esprit.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Factures;

public interface FactureRepository extends CrudRepository<Factures, Long> {

	@Query(value = "SELECT * FROM t_facture f WHERE f.commande_id=?1", nativeQuery = true)
	public List<Factures> getAllfacturesByCommande(long cmd);

	@Modifying
	@Transactional
	@Query(value = "	UPDATE t_facture c set c.commande_id=?1 where c.id_facture=?1", nativeQuery = true)

	public void affecterCommandeAFacture(long idCmmande, long idFacture);
}
