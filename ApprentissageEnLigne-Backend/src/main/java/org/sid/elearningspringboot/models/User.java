package org.sid.elearningspringboot.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Créer table User
@Entity
@Table(name="User")

//en utilisant lombok on peut générer les constucteurs sans arg et avec arg  et getters setters .. :)

@Data 
@AllArgsConstructor 
@NoArgsConstructor

//classe de la liste des utilisateurs de l'app

public class User{
	//id : email string ! 
	@Id
	private String email;
	private String username;
	private String userid;
	private String mobile;
	private String gender;
	private String profession;
	private String address;
	private String univ;
	private String password;


	//rls entre user et insc (user 1---lancer--- 1..n insc)
	
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getUserid() 
	{
		return userid;
	}

	public void setUserid(String userid) 
	{
		this.userid = userid;
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

	public String getProfession() 
	{
		return profession;
	}

	public void setProfession(String profession) 
	{
		this.profession = profession;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getUniv() {
		return univ;
	}

	public void setUniv(String univ) {
		this.univ = univ;
	}


	
}