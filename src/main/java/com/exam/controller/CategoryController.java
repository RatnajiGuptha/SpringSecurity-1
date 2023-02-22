package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService catService;
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category c=this.catService.addCategory(category);
		return ResponseEntity.ok(c);
	}
	@GetMapping("/{id}")
	public Category getCategory(@PathVariable("id") Long cId) {
		return this.catService.getCategory(cId);
	}
	@GetMapping("/categories")
	public ResponseEntity<?> getCategories(){
		return ResponseEntity.ok(this.catService.getCategories());
	}
	@PutMapping("/update")
	public Category updateCategory(@RequestBody Category category) {
		return this.catService.updateCategory(category);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteCategory(@PathVariable("id") Long cId) {
		this.catService.deleteCategory(cId);
	}
}
