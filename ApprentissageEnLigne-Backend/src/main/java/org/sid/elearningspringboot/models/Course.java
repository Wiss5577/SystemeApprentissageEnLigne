package org.sid.elearningspringboot.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Course")

//lombok 

@Data 
@AllArgsConstructor 
@NoArgsConstructor

//classe des cours des profs

public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //auto-inc
	private int id;
	
	private String coursename;
	private String courseid;
	private String enrolleddate;
	private String instructorname;
	private String instructorinstitution;
	private String enrolledcount;
	private String youtubeurl;
	private String websiteurl;
	private String coursetype;
	private String skilllevel;
	private String language;
	private String description;
	
	
	//je pense relation entre course et prof  
		//course et chap (course 1---contient---1..n chap)
		//course et ins ( course 1..n---dans---1..n insc)
	
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getEnrolleddate() {
		return enrolleddate;
	}

	public void setEnrolleddate(String enrolleddate) {
		this.enrolleddate = enrolleddate;
	}

	public String getInstructorname() {
		return instructorname;
	}

	public void setInstructorname(String instructorname) {
		this.instructorname = instructorname;
	}

	public String getInstructorinstitution() {
		return instructorinstitution;
	}

	public void setInstructorinstitution(String instructorinstitution) {
		this.instructorinstitution = instructorinstitution;
	}

	public String getEnrolledcount() {
		return enrolledcount;
	}

	public void setEnrolledcount(String enrolledcount) {
		this.enrolledcount = enrolledcount;
	}

	public String getYoutubeurl() {
		return youtubeurl;
	}

	public void setYoutubeurl(String youtubeurl) {
		this.youtubeurl = youtubeurl;
	}

	public String getWebsiteurl() {
		return websiteurl;
	}

	public void setWebsiteurl(String websiteurl) {
		this.websiteurl = websiteurl;
	}

	public String getCoursetype() {
		return coursetype;
	}

	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}

	public String getSkilllevel() {
		return skilllevel;
	}

	public void setSkilllevel(String skilllevel) {
		this.skilllevel = skilllevel;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}