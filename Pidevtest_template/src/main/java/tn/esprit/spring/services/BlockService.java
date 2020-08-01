package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.User;

public interface BlockService {
	public List<User> randomfollowOrNot(Long id);
	public boolean verifId(Long idConnected,Long idverif);

}
