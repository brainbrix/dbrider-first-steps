package com.example.repository.jpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.annotation.PersistenceLayerTest;
import com.example.entity.Question;
import com.github.database.rider.core.api.dataset.DataSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@PersistenceLayerTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepositoryImpl questionRepository;

    @Test
   // @DataSet("dataset/quiz.yaml")
    void categoryExist() {
        //GIVEN
        long existingId = 1L;

        //WHEN
        List<Question> listOfQuestions = questionRepository.getAllQuestionsForCategoryById(1L);

        //THEN
        assertThat(listOfQuestions.size()).isEqualTo(0);
    }


    @Test
    @DataSet("dataset/questionCategory.yaml")
    void questionsInCategoryTest() {
        //GIVEN
        long existingId = 1L;

        //WHEN
        List<Question> listOfQuestions = questionRepository.getAllQuestionsForCategoryById(existingId);

        //THEN
        assertThat(listOfQuestions.size()).isEqualTo(1);
    }

}
