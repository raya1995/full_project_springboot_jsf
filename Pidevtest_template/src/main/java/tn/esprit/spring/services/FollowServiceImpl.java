package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Follow;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.FollowRepository;
import tn.esprit.spring.repository.UserRepository;



@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	FollowRepository followRepository;

	@Override
	public List<User> MyFollow(Long id) {

		return userRepo.MyFollow(id);
	}

	@Override
	public List<User> following(Long id) {

		return userRepo.following(id);
	}

	@Override
	public int nbreFollow(Long id) {

		return followRepository.nombreFollow(id);
	}

	@Override
	public int nbreFollowing(Long id) {

		return followRepository.nombreFollowing(id);
	}

	@Override
	public void followService(long follow_id, long followed_id) {
		User user1 = new User();
		User user2 = new User();
		List<User> users = (List<User>) userRepo.findAll();
		Long id =null;
		  id = followRepository.ajouterFollow(follow_id, followed_id);
		 String a ="oussama"+id;
		 System.out.println(a);
		System.out.println(id);
		if (a.equals("oussamanull")) {
			followRepository.insertFollow(follow_id, followed_id);
			System.out.println("pussama");

		} else {
			System.out.println("not oussama");
			Follow f = new Follow();
			List<Follow> follows = (List<Follow>) followRepository.findAll();
			for (Follow follow : follows) {
				if (follow.getId().equals(id)) {
					f = follow;
				}

			}
			
			followRepository.deleteIdd(id);

		}

	}

	@Override
	public boolean findFollow(long follow_id, long followed_id) {
		
		Long id =null;
		  id = followRepository.ajouterFollow(follow_id, followed_id);
		 String a ="oussama"+id;
		 System.out.println(a);
		System.out.println(id);
		if (a.equals("oussamanull")) {
			return false;
			
		}else {
			return true;
		}
	}

}
