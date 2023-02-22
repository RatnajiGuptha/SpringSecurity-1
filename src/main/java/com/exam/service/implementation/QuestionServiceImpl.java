package com.exam.service.implementation;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repository.QuestionRepo;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepo.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.questionRepo.findAll());
	}

	@Override
	public Question getQuestion(Long qId) {
		// TODO Auto-generated method stub
		return this.questionRepo.findById(qId).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepo.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long qId) {
		// TODO Auto-generated method stub
		this.questionRepo.deleteById(qId);
		
	}

	@Override
	public Question get(Long questionId) {
		// TODO Auto-generated method stub
		return this.questionRepo.getOne(questionId);
	}
	
}
