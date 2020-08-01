package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.ConnectedUser;


public interface ConnectedUserRepository extends CrudRepository<ConnectedUser, Long> {

}
