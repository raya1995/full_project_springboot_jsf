package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Follow;
import tn.esprit.spring.entities.User;


public interface FollowRepository extends CrudRepository<Follow, Long> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO `follow` (`id`, `follow_id`, `followed_id`) VALUES (NULL, :follow_id, :followed_id);", nativeQuery = true)
	void insertFollow(@Param("follow_id") Long follow_id, @Param("followed_id") Long followed_id);
	//

	@Query(value = "SELECT * FROM USER U inner JOIN follow f ON f.follow_id=11 and U.id=f.followed_id ", nativeQuery = true)
	public List<User> MyFollow();
	
	@Query(value = "SELECT COUNT(*) FROM follow WHERE follow_id=:follow_id", nativeQuery = true)
	public int nombreFollow(@Param("follow_id") Long follow_id);
	@Query(value = "SELECT COUNT(*) FROM follow WHERE followed_id=:follow_id", nativeQuery = true)
	public int nombreFollowing(@Param("follow_id") Long follow_id);
	@Query(value = "SELECT `id` FROM `follow` WHERE `follow_id`=:follow_id and `followed_id`=:followed_id ", nativeQuery = true)
	public Long ajouterFollow(@Param("follow_id") Long follow_id,@Param("followed_id") Long followed_id);
	//SELECT * FROM USER U inner JOIN follow f ON f.follow_id<> and U.id=f.followed_id 
	
	@Modifying
	@Transactional
	@Query(value = "DELETE from follow WHERE id = :id ", nativeQuery = true)
	public void deleteIdd(@Param("id") Long id);
	

}
