package com.example.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.entity.Question;
import com.example.repository.QuestionRepository;

@Repository
public class QuestionRepositoryImpl extends BaseRepositoryImpl<Question, Long> implements QuestionRepository {

    public QuestionRepositoryImpl(EntityManager em) {
        super(Question.class, em);
    }

    public List<Question> getAllQuestionsForCategoryById(long id) {
        TypedQuery<Question> query = em.createQuery("SELECT q from Question q " +
                        "where (q.category.id = :id)", Question.class);
        query.setParameter("id", id);
        return query.getResultList();

    }

}