package org.sid.elearningspringboot.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Wishlist")

//lombok 

@Data 
@AllArgsConstructor 
@NoArgsConstructor

//classe des favories 

public class Wishlist {
	@Id
	private String coursename;
	
	private String courseid;
	private String likeduser;
	private String likedusertype;
	private String instructorname;
	private String instructorinstitution;
	private String enrolledcount;
	private String coursetype;
	private String websiteurl;
	private String skilllevel;
	private String language;
	private String description;
	

	public String getCoursename() 
	{
		return coursename;
	}

	public void setCoursename(String coursename) 
	{
		this.coursename = coursename;
	}

	public String getCourseid() 
	{
		return courseid;
	}

	public void setCourseid(String courseid) 
	{
		this.courseid = courseid;
	}
	
	public String getLikeduser() 
	{
		return likeduser;
	}

	public void setLikeduser(String likeduser) 
	{
		this.likeduser = likeduser;
	}

	public String getLikedusertype() 
	{
		return likedusertype;
	}

	public void setLikedusertype(String likedusertype) 
	{
		this.likedusertype = likedusertype;
	}

	public String getInstructorname() 
	{
		return instructorname;
	}

	public void setInstructorname(String instructorname) 
	{
		this.instructorname = instructorname;
	}

	public String getInstructorinstitution() 
	{
		return instructorinstitution;
	}

	public void setInstructorinstitution(String instructorinstitution) 
	{
		this.instructorinstitution = instructorinstitution;
	}

	public String getEnrolledcount() 
	{
		return enrolledcount;
	}

	public void setEnrolledcount(String enrolledcount) 
	{
		this.enrolledcount = enrolledcount;
	}

	public String getCoursetype() 
	{
		return coursetype;
	}

	public void setCoursetype(String coursetype) 
	{
		this.coursetype = coursetype;
	}
	
	public String getWebsiteurl() 
	{
		return websiteurl;
	}

	public void setWebsiteurl(String websiteurl) 
	{
		this.websiteurl = websiteurl;
	}

	public String getSkilllevel() 
	{
		return skilllevel;
	}

	public void setSkilllevel(String skilllevel) 
	{
		this.skilllevel = skilllevel;
	}

	public String getLanguage() 
	{
		return language;
	}

	public void setLanguage(String language) 
	{
		this.language = language;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
}
