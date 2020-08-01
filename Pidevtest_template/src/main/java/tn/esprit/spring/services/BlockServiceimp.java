package tn.esprit.spring.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.FollowRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class BlockServiceimp implements BlockService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	FollowRepository followRepository;
	@Override
	public List<User> randomfollowOrNot(Long id) {
		List<User> follow=userRepository.whofollowyou(id);
		System.out.println(follow);
		List<User> notfollow=userRepository.whofollowdontyou(id);
		System.out.println(notfollow);
		follow.addAll(notfollow);
		Collections.shuffle(follow); 
		System.out.println("********************************************");
		System.out.println(follow);
		return follow;
	}

	@Override
	public boolean verifId(Long idConnected, Long idverif) {
		if(followRepository.ajouterFollow(idConnected, idverif)!=null) {
			return true;
		}
		return false;
	}

}
