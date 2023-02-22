package com.exam.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long>{
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM quiz WHERE qid = ?1",nativeQuery = true)
	public void deleteQuiz(Long qid);
	public List<Quiz> findBycategory(Category category);
	public List<Quiz> findByActive(Boolean active);
	public List<Quiz> findByCategoryAndActive(Category category,Boolean active);
}
