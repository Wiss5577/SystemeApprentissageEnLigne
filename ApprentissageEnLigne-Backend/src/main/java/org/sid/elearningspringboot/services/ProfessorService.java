package org.sid.elearningspringboot.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sid.elearningspringboot.models.Course;
import org.sid.elearningspringboot.models.Professor;
import org.sid.elearningspringboot.repositories.ProfessorRepository;

@Service
public class ProfessorService 
{
	@Autowired
	private ProfessorRepository professorRepo;
	
	public Professor saveProfessor(Professor professor)
	{
		return professorRepo.save(professor);
	}
	
	public Professor addNewProfessor(Professor professor)
	{
		return professorRepo.save(professor);
	}
	
	public Professor updateProfessorProfile(Professor professor)
	{
		return professorRepo.save(professor);
	}
	
	public List<Professor> getAllProfessors()
	{
		return (List<Professor>)professorRepo.findAll();
	}
	
	public List<Professor> getProfessorListByEmail(String email) 
	{
		return (List<Professor>)professorRepo.findProfessorListByEmail(email);
	}
	
	public Professor fetchProfessorByEmail(String email)
	{
		return professorRepo.findByEmail(email);
	}
	
	public Professor fetchProfessorByProfessorname(String professorname)
	{
		return professorRepo.findByProfessorname(professorname);
	}
	
	public Professor fetchProfessorByEmailAndPassword(String email, String password)
	{
		return professorRepo.findByEmailAndPassword(email, password);
	}
	
	public Professor fetchProfileByEmail(String email)
	{
		return professorRepo.findProfileByEmail(email);
	}

	public void updateStatus(String email) 
	{
		professorRepo.updateStatus(email);
	}

	public void rejectStatus(String email) 
	{
		professorRepo.rejectStatus(email);
	}
	public void SendRequest(String email) 
	{
		professorRepo.SendRequest(email);
	}

	public List<Professor> getProfessorsByEmail(String email)
	{
		return professorRepo.findProfessorListByEmail(email);
	}

	
}