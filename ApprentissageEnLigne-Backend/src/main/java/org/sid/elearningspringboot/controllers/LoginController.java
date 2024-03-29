package org.sid.elearningspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sid.elearningspringboot.models.Professor;
import org.sid.elearningspringboot.models.User;
import org.sid.elearningspringboot.services.ProfessorService;
import org.sid.elearningspringboot.services.UserService;

@RestController
public class LoginController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService professorService;
	
	//pour tester from browser or postman
	@GetMapping("/")
    public String welcomeMessage()
    {
    	return "Welcome";
    }
	
	//login de user
	@PostMapping("/loginuser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception
	{
		String currEmail = user.getEmail();
		String currPassword = user.getPassword();
		
		User userObj = null;
		if(currEmail != null && currPassword != null)
		{
			userObj = userService.fetchUserByEmailAndPassword(currEmail, currPassword);
		}
		if(userObj == null)
		{
			throw new Exception("User doesnt exist");
		}		
		return userObj;
	}
	
	//login de prof
	@PostMapping("/loginprofessor")
	@CrossOrigin(origins = "http://localhost:4200")
	public Professor loginDoctor(@RequestBody Professor professor) throws Exception
	{
		String currEmail = professor.getEmail();
		String currPassword = professor.getPassword();
		
		Professor professorObj = null;
		if(currEmail != null && currPassword != null)
		{
			professorObj = professorService.fetchProfessorByEmailAndPassword(currEmail, currPassword);
		}
		if(professorObj == null)
		{
			throw new Exception("Professor doesnt exist");
		}		
		return professorObj;
	}
}