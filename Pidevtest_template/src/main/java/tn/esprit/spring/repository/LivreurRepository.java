package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Livreur;

public interface LivreurRepository extends JpaRepository <Livreur, Long>  {
	
	

}
