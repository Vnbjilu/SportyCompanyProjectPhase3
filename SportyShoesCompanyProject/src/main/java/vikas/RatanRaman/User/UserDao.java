package vikas.RatanRaman.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
	
	@Autowired
	UserRepository udao;
	
	public void insert(UserEntity ue)
	{
		udao.save(ue);
	}

	public UserEntity login(String username,String userpassword)
	{
		return udao.login(username, userpassword);
		
	}
	public List<UserEntity> GetAllRecord()
	{
		return udao.findAll();
	}
}
