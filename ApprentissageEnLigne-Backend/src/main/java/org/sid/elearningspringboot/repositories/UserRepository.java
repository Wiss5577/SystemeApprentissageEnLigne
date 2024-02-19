package org.sid.elearningspringboot.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.sid.elearningspringboot.models.User;

public interface UserRepository extends CrudRepository<User, String>{
	
    public User findByEmail(String email);
	
	public User findByUsername(String username);
	
	public User findByEmailAndPassword(String email, String password);
	
	public List<User> findProfileByEmail(String email);
	
}