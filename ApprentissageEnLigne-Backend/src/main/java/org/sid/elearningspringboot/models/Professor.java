package org.sid.elearningspringboot.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Professor")

//lombok 

@Data 
@AllArgsConstructor 
@NoArgsConstructor

//classe des profs 

public class Professor {
	//id : email string ! 
	@Id
	private String email;
	
	private String professorname;
	private String professorid;
	private String degreecompleted;
	private String institutionname;
	private String department;
	private String experience;
	private String mobile;
	private String gender;
	private String password;
	private String status;	
	private String univ;
    private String pic;
	
	//Normalement  relation entre prof et course ( prof 1..n ---teach--- 1..n course)
	
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getProfessorname() 
	{
		return professorname;
	}

	public void setProfessorname(String professorname) 
	{
		this.professorname = professorname;
	}

	public String getProfessorid() 
	{
		return professorid;
	}

	public void setProfessorid(String professorid) 
	{
		this.professorid = professorid;
	}

	public String getDegreecompleted() 
	{
		return degreecompleted;
	}

	public void setDegreecompleted(String degreecompleted) 
	{
		this.degreecompleted = degreecompleted;
	}

	public String getInstitutionname() 
	{
		return institutionname;
	}

	public void setInstitutionname(String institutionname) 
	{
		this.institutionname = institutionname;
	}

	public String getDepartment() 
	{
		return department;
	}

	public void setDepartment(String department) 
	{
		this.department = department;
	}

	public String getExperience() 
	{
		return experience;
	}

	public void setExperience(String experience) 
	{
		this.experience = experience;
	}

	public String getMobile() 
	{
		return mobile;
	}

	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getUniv() {
		return univ;
	}

	public void setUniv(String univ) {
		this.univ = univ;
	}
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
}