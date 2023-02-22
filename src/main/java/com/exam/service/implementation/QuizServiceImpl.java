package com.exam.service.implementation;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.repository.QuizRepo;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepo quizRepo;
	@Override
	public Quiz addQuiz(Quiz quiz) {
		return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.quizRepo.findAll());
	}

	@Override
	public Quiz getQuiz(Long qId) {
		// TODO Auto-generated method stub
		return this.quizRepo.findById(qId).get();
	}

	@Override
	public void deleteQuiz(Long qId) {
		// TODO Auto-generated method stub
		this.quizRepo.deleteQuiz(qId);
	}
	public List<Quiz> getQuizzesOfCategory(Category category){
		return this.quizRepo.findBycategory(category);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		return this.quizRepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category category) {
		return this.quizRepo.findByCategoryAndActive(category, true);
	}
}
