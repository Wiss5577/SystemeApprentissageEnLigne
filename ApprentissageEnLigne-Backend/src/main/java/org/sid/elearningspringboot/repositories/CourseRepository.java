package org.sid.elearningspringboot.repositories;

import java.util.List;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.sid.elearningspringboot.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{    
    public Course findByCoursename(String coursename);
	
	public Course findByCourseid(String courseid);
	
	public List<Course> findByInstructorname(String instructorname);
	
	public List<Course> findByInstructorinstitution(String instructorinstitution);
	
    public List<Course> findByEnrolleddate(String enrolleddate);
	
	public List<Course> findByCoursetype(String coursetype);
	
	public List<Course> findByYoutubeurl(String youtubeurl);
	
	public List<Course> findByWebsiteurl(String websiteurl);
	
    public List<Course> findBySkilllevel(String skilllevel);
	
	public List<Course> findByLanguage(String language);
	
	
	@Transactional
	@Modifying
	@Query(value = "update course set enrolledcount = ?1 where coursename = ?2",nativeQuery = true)
	public void updateEnrolledcount(int enrolledcount, String coursename);
	
	@Transactional
	@Modifying
	@Query(value = "select * from course where email = ?1", nativeQuery = true)
	public List<Course> GetCoursesByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update chapter set coursename = ?1 where id= ?2",nativeQuery = true)
	public void SetCoursename(String coursename,int id);
	
	@Transactional
	@Modifying
	@Query(value = "select * from course where email = ?1 and coursename = ?2", nativeQuery = true)
	public List<Course> GetCoursesByEmailAndCourseName(String email,String coursename);
	
	
	
	@Transactional
	@Modifying
	@Query(value = "select * from course where instructorname = ?1 and coursename = ?2", nativeQuery = true)
	public List<Course> GetCoursByEmailEnsAndName(String instructorname,String coursename);
}