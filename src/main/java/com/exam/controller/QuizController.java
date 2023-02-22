package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	@GetMapping("/quizzes")
	public ResponseEntity<?> getQuizzes(){
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	@GetMapping("/{id}")
	public Quiz getQuiz(@PathVariable("id") Long Id) {
		return this.quizService.getQuiz(Id);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteQuiz(@PathVariable("id") Long id) {
		this.quizService.deleteQuiz(id);
	}
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid){
		Category cat=new Category();
		cat.setCid(cid);
		return this.quizService.getQuizzesOfCategory(cat);
	}
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes(){
		return this.quizService.getActiveQuizzes();
	}
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid){
		Category cat=new Category();
		cat.setCid(cid);
		return this.quizService.getActiveQuizzesOfCategory(cat);
	}
}
