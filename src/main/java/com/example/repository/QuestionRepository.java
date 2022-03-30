package com.example.repository;

import java.util.List;

import com.example.entity.Question;

public interface QuestionRepository extends BaseRepository<Question, Long>{
    List<Question> getAllQuestionsForCategoryById(long id);
}
