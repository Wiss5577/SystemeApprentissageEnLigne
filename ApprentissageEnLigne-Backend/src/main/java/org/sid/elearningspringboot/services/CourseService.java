package org.sid.elearningspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sid.elearningspringboot.models.Chapter;
import org.sid.elearningspringboot.models.Course;
import org.sid.elearningspringboot.repositories.ChapterRepository;
import org.sid.elearningspringboot.repositories.CourseRepository;

@Service
public class CourseService 
{
	@Autowired
	private CourseRepository courseRepo;
	private ChapterRepository chapRepo;

	
	public Course saveCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public Course addNewCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public List<Course> getAllCourses()
	{
		return (List<Course>)courseRepo.findAll();
	}
	
	public void updateEnrolledcount(String coursename, int enrolledcount)
	{
		courseRepo.updateEnrolledcount(enrolledcount, coursename);
	}
	
	public Course fetchCourseByCoursename(String coursename)
	{
		return courseRepo.findByCoursename(coursename);
	}
	
	public Course fetchCourseByCourseid(String courseid)
	{
		return courseRepo.findByCourseid(courseid);
	}
	
	public List<Course> fetchByInstructorname(String instructorname)
	{
		return (List<Course>)courseRepo.findByInstructorname(instructorname);
	}
	
	public List<Course> fetchByInstructorinstitution(String instructorinstitution)
	{
		return (List<Course>)courseRepo.findByInstructorinstitution(instructorinstitution);
	}
	
	public List<Course> fetchByEnrolleddate(String enrolleddate)
	{
		return (List<Course>)courseRepo.findByEnrolleddate(enrolleddate);
	}
	
	public List<Course> fetchByCoursetype(String coursetype)
	{
		return (List<Course>)courseRepo.findByCoursetype(coursetype);
	}
	
	public List<Course> fetchByYoutubeurl(String youtubeurl)
	{
		return (List<Course>)courseRepo.findByYoutubeurl(youtubeurl);
	}
	
	public List<Course> fetchByWebsiteurl(String websiteurl)
	{
		return (List<Course>)courseRepo.findByWebsiteurl(websiteurl);
	}
	
	public List<Course> fetchBySkilllevel(String skilllevel)
	{
		return (List<Course>)courseRepo.findBySkilllevel(skilllevel);
	}
	
	public List<Course> fetchByLanguage(String language)
	{
		return (List<Course>)courseRepo.findByLanguage(language);
	}
	
	
	public List<Course> GetCoursesByEmail(String email){
		
		return courseRepo.GetCoursesByEmail(email);
	}
	
	public void DeleteCourse (int id) {
		 courseRepo.deleteById(id);
		
	}
	
	public Optional<Course> GetCourseById( int id) {
		return courseRepo.findById(id);
	}
	
	public Course UpdateCourse(Course course)
	{
		return courseRepo.save(course);
	}
	  public void SetCoursename(String coursename,int id) {
		  courseRepo.SetCoursename(coursename, id);
	  }
	  
	 
	
	  public Optional<Course> GetCoursById(int id) {
		return  courseRepo.findById(id);
	  }
	  
	  
	  public List<Course> GetCoursesByEmailAndCourseName(String email,String coursename){
		  return courseRepo.GetCoursesByEmailAndCourseName(email, coursename);
	  }
	
}