package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.User;


public interface UserRepository extends CrudRepository<User, Long> {
	@Query(value="SELECT count(*) FROM USER",nativeQuery = true)
	public int retrieveUsers();
}
