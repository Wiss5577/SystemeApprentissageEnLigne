package org.sid.elearningspringboot.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.sid.elearningspringboot.models.Chapter;

public interface ChapterRepository extends CrudRepository<Chapter, Integer>
{
	public List<Chapter> findByCoursename(String Coursename);
	
}