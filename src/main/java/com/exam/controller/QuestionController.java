package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;



@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService quesService;
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		System.out.println(question.getAnswer());
		System.out.println(question.getContent());
		System.out.println(question.getOption1());
		return ResponseEntity.ok(this.quesService.addQuestion(question));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.quesService.updateQuestion(question));
	}
	
	//get all questions of a specific quiz
	@GetMapping("/quiz/{id}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("id") Long qid){
		Quiz q=this.quizService.getQuiz(qid);
		Set<Question> ques= q.getQuestions();
		List<Question> list=new ArrayList<Question>(ques);
		if(list.size() > Integer.parseInt(q.getNumberOfQuestions())) {
			list=list.subList(0, Integer.parseInt(q.getNumberOfQuestions()+1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public Question getQuestion(@PathVariable("id") Long quizid) {
		return this.quesService.getQuestion(quizid);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteQuestion(@PathVariable("id") Long id) {
		this.quesService.deleteQuestion(id);
	}
	
	@PostMapping("/evaluate-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		double marksObtained=0;
		int correctAnswer=0;
		int Attempted=0;
		for(Question q:questions){
			Question question=this.quesService.get(q.getQuesid());
			if(question.getAnswer().equals(q.getGivenAnswer())) {
				correctAnswer++;
				double marks=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksObtained+=marks;
			}
			if(q.getGivenAnswer()!=null || !q.getGivenAnswer().equals("") ){
			     Attempted++;
		}
	}
		Map<String, Object> map = new HashMap<>();
		map.put("marksObtained", marksObtained);
		map.put("correctAnswer", correctAnswer);
		map.put("Attempted", Attempted);
		return ResponseEntity.ok(map);
	}
}
