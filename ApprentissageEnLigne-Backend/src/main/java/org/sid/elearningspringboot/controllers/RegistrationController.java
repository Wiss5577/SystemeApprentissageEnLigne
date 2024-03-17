package org.sid.elearningspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.sid.elearningspringboot.models.Course;
import org.sid.elearningspringboot.models.Enrollment;
import org.sid.elearningspringboot.models.Professor;
import org.sid.elearningspringboot.models.User;
import org.sid.elearningspringboot.services.CourseService;
import org.sid.elearningspringboot.services.EnrollmentService;
import org.sid.elearningspringboot.services.ProfessorService;
import org.sid.elearningspringboot.services.UserService;

@RestController
public class RegistrationController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CourseService courseService;
	private EnrollmentService enrollmentService;
	@PostMapping("/registeruser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception
	{
		String currEmail = user.getEmail();
		String newID = getNewID();
		user.setUserid(newID);
		
		if(currEmail != null || !"".equals(currEmail))
		{
			User userObj = userService.fetchUserByEmail(currEmail);
			if(userObj != null)
			{
				throw new Exception("User with "+currEmail+" already exists !!!");
			}
		}
		User userObj = null;
		userObj = userService.saveUser(user);
		return userObj;
	}
	//register as professor
	@PostMapping("/registerprofessor")
	@CrossOrigin(origins = "http://localhost:4200")
	
	public Professor registerDoctor(@RequestBody Professor professor) throws Exception
	{
		String currEmail = professor.getEmail();
		String newID = getNewID();
		professor.setProfessorid(newID);
		
		if(currEmail != null || !"".equals(currEmail))
		{
			Professor professorObj = professorService.fetchProfessorByEmail(currEmail);
			if(professorObj != null)
			{
				throw new Exception("Professor with "+currEmail+" already exists !!!");
			}
		}
		Professor professorObj = null;
		professorObj = professorService.saveProfessor(professor);
		return professorObj;
	}
	
	public String getNewID()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) 
        {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
	}
	
	@GetMapping("/courseslist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Course>> getAllCourses() throws Exception{
		List<Course> courses =courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	

	

}