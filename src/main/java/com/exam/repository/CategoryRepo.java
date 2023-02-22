package com.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.model.exam.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

}
