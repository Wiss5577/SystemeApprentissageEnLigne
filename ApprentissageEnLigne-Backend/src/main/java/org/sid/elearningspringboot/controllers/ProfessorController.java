package org.sid.elearningspringboot.controllers;

import java.util.ArrayList;
import java.util.List;
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
import org.sid.elearningspringboot.models.Course;
import org.sid.elearningspringboot.models.Professor;
import org.sid.elearningspringboot.models.Wishlist;
import org.sid.elearningspringboot.services.ChapterService;
import org.sid.elearningspringboot.services.CourseService;
import org.sid.elearningspringboot.services.ProfessorService;
import org.sid.elearningspringboot.services.WishlistService;

@RestController
public class ProfessorController {	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private WishlistService wishlistService;
	
	
	
	//Liste des profs
	@GetMapping("/professorlist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Professor>> getProfessorList() throws Exception{
		List<Professor> professors = professorService.getAllProfessors();
		return new ResponseEntity<List<Professor>>(professors, HttpStatus.OK);
	}
	
	
	//Cours de type youtube 
	@GetMapping("/youtubecourselist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Course>> getYoutubeCourseList() throws Exception{
		List<Course> youtubeCourseList = courseService.fetchByCoursetype("Youtube");
		return new ResponseEntity<List<Course>>(youtubeCourseList, HttpStatus.OK);
	}
	
	//cours de type website
	@GetMapping("/websitecourselist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Course>> getWebsiteCourseList() throws Exception
	{
		List<Course> websiteCourseList = courseService.fetchByCoursetype("Website");
		return new ResponseEntity<List<Course>>(websiteCourseList, HttpStatus.OK);
	}
	
	//cours by name
	@GetMapping("/courselistbyname/{coursename}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Course>> getCourseListByName(@PathVariable String coursename) throws Exception
	{
		Course courseList = courseService.fetchCourseByCoursename(coursename);
		System.out.println(courseList.getCoursename()+" ");
		List<Course> courselist = new ArrayList<>();
		courselist.add(courseList);
		return new ResponseEntity<List<Course>>(courselist, HttpStatus.OK);
	}
	
	
	//prof par email
	
	@GetMapping("/professorlistbyemail/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Professor>> getProfessorListByEmail(@PathVariable String email) throws Exception
	{
		List<Professor> professors = professorService.getProfessorsByEmail(email);
		return new ResponseEntity<List<Professor>>(professors, HttpStatus.OK);
	}
	
	
	
	
	//Ajouter prof
	
	@PostMapping("/addProfessor")
	@CrossOrigin(origins = "http://localhost:4200")
	public Professor addNewProfessor(@RequestBody Professor professor) throws Exception{
		Professor professorObj = null;
		String newID = getNewID();
		professor.setProfessorid(newID);
		professorObj = professorService.addNewProfessor(professor);
		professorService.updateStatus(professor.getEmail());
		return professorObj;
	}
	
	
	//Ajouter cours
	@PostMapping("/addCourse")
	@CrossOrigin(origins = "http://localhost:4200")
	public Course addNewCourse(@RequestBody Course course) throws Exception
	{
		Course courseObj = null;
		String newID = getNewID();
		course.setCourseid(newID);
		
		courseObj = courseService.addNewCourse(course);
		return courseObj;
	}
	
	//Ajouter Chapitre
	@PostMapping("/addnewchapter")
	@CrossOrigin(origins = "http://localhost:4200")
	public Chapter addNewChapters(@RequestBody Chapter chapter) throws Exception
	{
		Chapter chapterObj = null;
		chapterObj = chapterService.addNewChapter(chapter);
		return chapterObj;
	}
	
	//Pour accepter prof
	@GetMapping("/acceptstatus/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<String>> updateStatus(@PathVariable String email) throws Exception
	{
		professorService.updateStatus(email);
		List<String> al=new ArrayList<>();
		al.add("accepted");
		return new ResponseEntity<List<String>>(al,HttpStatus.OK);
	}
	
	
	//Pour refuser prof
	@GetMapping("/rejectstatus/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<String>> rejectStatus(@PathVariable String email) throws Exception
	{
		professorService.rejectStatus(email);
		List<String> al=new ArrayList<>();
		al.add("rejected");
		return new ResponseEntity<List<String>>(al,HttpStatus.OK);
	}
	
	//!!!!!! Pour envoyer autre demande d'app par prof
		@GetMapping("/sendrequest/{email}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<String>> SendRequest(@PathVariable String email) throws Exception
		{
			professorService.SendRequest(email);
			List<String> al=new ArrayList<>();
			al.add("pas encore");
			return new ResponseEntity<List<String>>(al,HttpStatus.OK);
		}
	
	
	
	//les info d'un prof
	@GetMapping("/professorprofileDetails/{email}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Professor>> getProfileDetails(@PathVariable String email) throws Exception
	{
		List<Professor> professors = professorService.fetchProfileByEmail(email);
		return new ResponseEntity<List<Professor>>(professors, HttpStatus.OK);
	}
	
	//modifier info de prof
	@PutMapping("/updateprofessor")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Professor> updateProfessorProfile(@RequestBody Professor professor) throws Exception
	{
		Professor professorobj = professorService.updateProfessorProfile(professor);
		return new ResponseEntity<Professor>(professorobj, HttpStatus.OK);
	}
	
	//nombre des profs
	@GetMapping("/gettotalprofessors")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalProfessors() throws Exception
	{
		List<Professor> professors = professorService.getAllProfessors();
		List<Integer> professorsCount = new ArrayList<>();
		professorsCount.add(professors.size());
		return new ResponseEntity<List<Integer>>(professorsCount, HttpStatus.OK);
	}
	
	//nombre des chapitres
	
	@GetMapping("/gettotalchapters")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalChapters() throws Exception
	{
		List<Chapter> chapters = chapterService.getAllChapters();
		List<Integer> chaptersCount = new ArrayList<>();
		chaptersCount.add(chapters.size());
		return new ResponseEntity<List<Integer>>(chaptersCount, HttpStatus.OK);
	}
	
	//nombre des cours
	
	@GetMapping("/gettotalcourses")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalCourses() throws Exception
	{
		List<Course> courses = courseService.getAllCourses();
		List<Integer> coursesCount = new ArrayList<>();
		coursesCount.add(courses.size());
		return new ResponseEntity<List<Integer>>(coursesCount, HttpStatus.OK);
	}
	
	//nbr des wishlist
	
	@GetMapping("/gettotalwishlist")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalWishlist() throws Exception
	{
		List<Wishlist> wishlists = wishlistService.getAllLikedCourses();
		List<Integer> wishlistCount = new ArrayList<>();
		wishlistCount.add(wishlists.size());
		return new ResponseEntity<List<Integer>>(wishlistCount, HttpStatus.OK);
	}
  
//les noms des cours
	
  @GetMapping("/getcoursenames")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<String>> getCourseNames() throws Exception
	{
		List<Course> courses = courseService.getAllCourses();
		List<String> coursenames = new ArrayList<>();
		for(Course obj : courses)
		{
			coursenames.add(obj.getCoursename());
		}
		return new ResponseEntity<List<String>>(coursenames, HttpStatus.OK);
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
	
}
