package com.exam.service.implementation;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Category;
import com.exam.repository.CategoryRepo;
import com.exam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepo catrepo;

	@Override
	public Category addCategory(Category category) {
		return this.catrepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.catrepo.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<>(this.catrepo.findAll());
	}

	@Override
	public Category getCategory(Long cId) {
		return this.catrepo.findById(cId).get();
	}

	@Override
	public void deleteCategory(Long cId) {
		Category c=new Category();
		c.setCid(cId);
		this.catrepo.delete(c);
	}

}
