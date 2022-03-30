package com.example.repository.jpa;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category, Long> implements CategoryRepository {
    public CategoryRepositoryImpl(EntityManager em) {
        super(Category.class, em);
    }
}
