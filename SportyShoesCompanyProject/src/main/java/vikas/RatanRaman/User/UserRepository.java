package vikas.RatanRaman.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query("From UserEntity where userName=:username and userPassword=:userpassword")
	public UserEntity login(String username,String userpassword);
	
	
	
}
