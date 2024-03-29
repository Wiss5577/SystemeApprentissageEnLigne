package org.sid.elearningspringboot.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Optional;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sid.elearningspringboot.models.Chapter;
import org.sid.elearningspringboot.models.Enrollment;
import org.sid.elearningspringboot.models.Professor;
import org.sid.elearningspringboot.models.User;
import org.sid.elearningspringboot.models.Wishlist;
import org.sid.elearningspringboot.services.ChapterService;
import org.sid.elearningspringboot.services.CourseService;
import org.sid.elearningspringboot.services.EnrollmentService;
import org.sid.elearningspringboot.services.ProfessorService;
import org.sid.elearningspringboot.services.UserService;
import org.sid.elearningspringboot.services.WishlistService;

@RestController
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private ChapterService chapterService;
	
	//liste des users
	@GetMapping("/userlist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> getUsers() throws Exception
	{
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	//ajouter dans table enrollment corps : enrollment
	@PostMapping("/enrollnewcourse/{email}/{role}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String enrollNewCourse(@RequestBody Enrollment enrollment, @PathVariable String email, @PathVariable String role) throws Exception
	{
		String enrolledUserName = "",enrolledUserID = "";
		
		if(role.equalsIgnoreCase("user"))
		{
		    List<User> users = userService.getAllUsers();
		    for(User userObj:users)
		    {
			    if(userObj.getEmail().equalsIgnoreCase(email))
			    {
				    enrolledUserName = userObj.getUsername();
				    enrolledUserID = userObj.getUserid();
				    enrollment.setEnrolleduserid(enrolledUserID);
				    enrollment.setEnrolledusername(enrolledUserName);
				    break;
			    }
		    }
		}
		else if(role.equalsIgnoreCase("professor"))
		{
		    List<Professor> professors = professorService.getAllProfessors();
		    for(Professor professorObj:professors)
		    {
			    if(professorObj.getEmail().equalsIgnoreCase(email))
			    {
				    enrolledUserName = professorObj.getProfessorname();
				    enrolledUserID = professorObj.getProfessorid();
				    enrollment.setEnrolleduserid(enrolledUserID);
				    enrollment.setEnrolledusername(enrolledUserName);
				    break;
			    }
		    }
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date();  
        String todayDate = formatter.format(date);
        enrollment.setEnrolleddate(todayDate);
         	
		Enrollment enrollmentObj = null;
		enrollmentObj = enrollmentService.saveEnrollment(enrollment);
		System.out.println(enrollmentObj);
		
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
		Map<String, Integer> enrolledCount = new LinkedHashMap<>();
		for(Enrollment enrollObj : enrollments)
		{
			String courseName = enrollObj.getCoursename();
			if(enrolledCount.containsKey(courseName))
			enrolledCount.put(courseName, enrolledCount.get(courseName)+1);
			else
			enrolledCount.put(courseName, 1);
		}
		for(Map.Entry<String, Integer> obj : enrolledCount.entrySet())
		{
			if(obj.getKey().equalsIgnoreCase(enrollment.getCoursename()))
			{
			    enrollmentService.updateEnrolledcount(obj.getKey(), obj.getValue());
			    courseService.updateEnrolledcount(obj.getKey(), obj.getValue());
			}
		}
		
		return "done";
	}
	
	//status d'inscription : wch kayn had user awla prof f table dial enrollment ?
	
	@GetMapping("/getenrollmentstatus/{coursename}/{email}/{role}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Set<String>> getEnrollmentStatus(@PathVariable String coursename, @PathVariable String email, @PathVariable String role) throws Exception{
		List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
		User userObj;
		Professor professorObj;
		String enrolledUser = "";
		if(role.equalsIgnoreCase("user"))
		{
		    userObj = userService.fetchUserByEmail(email); //knjbdo smia dial user by email
		    enrolledUser = userObj.getUsername();
		}
		else if(role.equalsIgnoreCase("professor"))
		{
		    professorObj = professorService.fetchProfessorByEmail(email);
		    enrolledUser = professorObj.getProfessorname();
		}
		
		Set<String> enrollmentStatus = new LinkedHashSet<>();
		int flag = 0;
		OUTER:for(Enrollment enrollmentObj : enrollments){
	if(enrollmentObj.getCoursename().equalsIgnoreCase(coursename) && 
			enrollmentObj.getEnrolledusername().equalsIgnoreCase(enrolledUser))
			{ //wach name dial user awla prof  w course kayn  f enrollement table ?
				enrollmentStatus.add("enrolled");
				flag = 1;
				break OUTER;
			}
		}
		if(flag == 0)
		enrollmentStatus.add("notenrolled");
		return new ResponseEntity<Set<String>>(enrollmentStatus, HttpStatus.OK);
	}
	
	//like
	@PostMapping("/addtowishlist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Wishlist> addNewCourse(@RequestBody Wishlist wishlist) throws Exception
	{
		Wishlist wishlistObj = null;
		wishlistObj = wishlistService.addToWishlist(wishlist);
		return new ResponseEntity<Wishlist>(wishlistObj, HttpStatus.OK);
	}
	
	
	// course liked by this user ?
	
	@GetMapping("/getwishliststatus/{coursename}/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Set<String>> getWishlistStatus(@PathVariable String coursename, @PathVariable String email) throws Exception
	{
		List<Wishlist> wishlists = wishlistService.getAllLikedCourses();
		Set<String> wishlistsStatus = new LinkedHashSet<>();
		int flag = 0;
		OUTER:for(Wishlist wishlistsObj : wishlists)
		{
			if(wishlistsObj.getCoursename().equalsIgnoreCase(coursename) && wishlistsObj.getLikeduser().equalsIgnoreCase(email))
			{
				wishlistsStatus.add("liked");
				flag = 1;
				break OUTER;
			}
		}
		if(flag == 0)
		wishlistsStatus.add("notliked");
		return new ResponseEntity<Set<String>>(wishlistsStatus, HttpStatus.OK);
	}
	
	//listes des likes
	@GetMapping("/getallwishlist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Wishlist>> getAllWislist() throws Exception
	{
		List<Wishlist> Wishlists = wishlistService.getAllLikedCourses();
		return new ResponseEntity<List<Wishlist>>(Wishlists, HttpStatus.OK);
	}
	
	
	//which courses this user has liked?
	@GetMapping("/getwishlistbyemail/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Wishlist>> getWishlistByEmail(@PathVariable String email) throws Exception
	{
		List<Wishlist> Wishlists = wishlistService.fetchByLikeduser(email);
		return new ResponseEntity<List<Wishlist>>(Wishlists, HttpStatus.OK);
	}
	
	
	//fach msjl user/prof
	@GetMapping("/getenrollmentbyemail/{email}/{role}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Enrollment>> getEnrollmentsByEmail(@PathVariable String email, @PathVariable String role) throws Exception
	{
		User userObj;
		Professor professorObj;
		String enrolledUser = "";
		if(role.equalsIgnoreCase("user"))
		{
		    userObj = userService.fetchUserByEmail(email);
		    enrolledUser = userObj.getUsername();
		}
		else if(role.equalsIgnoreCase("professor"))
		{
		    professorObj = professorService.fetchProfessorByEmail(email);
		    enrolledUser = professorObj.getProfessorname();
		}
		
		List<Enrollment> enrollments = enrollmentService.fetchByEnrolledusername(enrolledUser);
		return new ResponseEntity<List<Enrollment>>(enrollments, HttpStatus.OK);
	}
	
	//chap dial cours
	@GetMapping("/getchapterlistbycoursename/{coursename}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Chapter>> getChapterListByCoursename(@PathVariable String coursename) throws Exception
	{
		List<Chapter> chapterLists = chapterService.fetchByCoursename(coursename);
		if(chapterLists.size()==0)
		{
			Chapter obj1 = new Chapter();
			obj1.setChapter1name("");
			obj1.setChapter2name("");
			obj1.setChapter3name("");
			obj1.setChapter4name("");
			obj1.setChapter5name("");
			obj1.setChapter6name("");
			obj1.setChapter7name("");
			obj1.setChapter8name("");
			chapterLists.add(obj1);
		}
		return new ResponseEntity<List<Chapter>>(chapterLists, HttpStatus.OK);
	}
	
	//user
	@GetMapping("/userprofileDetails/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> getProfileDetails(@PathVariable String email) throws Exception
	{
		List<User> users = userService.fetchProfileByEmail(email);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	//update user info
	@PutMapping("/updateuser")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<User> updateUserProfile(@RequestBody User user) throws Exception
	{
		User userobj = userService.updateUserProfile(user);
		return new ResponseEntity<User>(userobj, HttpStatus.OK);
	}
	
	//nombre des utilisateurs
	@GetMapping("/gettotalusers")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalUsers() throws Exception
	{
		List<User> users = userService.getAllUsers();
		List<Integer> usersCount = new ArrayList<>();
		usersCount.add(users.size());
		return new ResponseEntity<List<Integer>>(usersCount, HttpStatus.OK);
	}
	//total insc count ?
	@GetMapping("/gettotalenrollmentcount")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalEnrollmentcount() throws Exception
	{
		List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
		int count = 0;
		for(Enrollment enrollmentObj : enrollments)
		{
			count += Integer.parseInt(enrollmentObj.getEnrolledcount());
		}
		List<Integer> enrollmentsCount = new ArrayList<>();
		enrollmentsCount.add(count);
		return new ResponseEntity<List<Integer>>(enrollmentsCount, HttpStatus.OK);
	}
	
	//total inscr
	@GetMapping("/gettotalenrollments")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalEnrollments() throws Exception
	{
		List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
		List<Integer> enrollmentsCount = new ArrayList<>();
		enrollmentsCount.add(enrollments.size());
		return new ResponseEntity<List<Integer>>(enrollmentsCount, HttpStatus.OK);
	}
	
	//convertir l'url de pic
	@GetMapping("/getPicUser/{email}")
    public byte[] getPicUser(@PathVariable String email) throws Exception {
        // Récupérer l'utilisateur par email
        User user = (User) userService.fetchProfileByEmail(email);
        
        // Vérifier si l'utilisateur et son image existent
        if (user != null && user.getPic() != null) {
            // Lire les octets de l'image à partir du chemin spécifié
            return Files.readAllBytes(Paths.get("/chemin/vers/votre/dossier/Images/" + user.getPic()));
        } else {
            // Gérer le cas où l'utilisateur ou son image n'existent pas
            throw new Exception("L'utilisateur ou son image n'existent pas");
        }
    }
	@GetMapping("/getuserbyid/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Optional<User> GetUserByID(@PathVariable String email){
		return userService.GetUserByID(email);
	}
	
	

}
