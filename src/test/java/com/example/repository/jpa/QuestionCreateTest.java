package com.example.repository.jpa;

import java.util.List;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.annotation.PersistenceLayerTest;
import com.example.entity.Question;
import com.github.database.rider.core.api.dataset.DataSet;

import static org.assertj.core.api.Assertions.assertThat;

@PersistenceLayerTest
public class QuestionCreateTest {

    @Autowired
    private QuestionRepositoryImpl questionRepository;

    @Autowired
    private CategoryRepositoryImpl categoryRepository;


    @Test
    @DataSet("dataset/questionCategory.yaml")
    void createQuestionsTest() {
        //GIVEN
        long categoryId = 1L;
        Question q = new Question();
        q.setText("This is a sample question");
        Category c = categoryRepository.findByIdMandatory(categoryId);
        q.setCategory(c);

        //WHEN
        questionRepository.save(q);

        //THEN
        List<Question> listOfQuestions = questionRepository.getAllQuestionsForCategoryById(categoryId);
        assertThat(listOfQuestions.size()).isEqualTo(2);
    }
}
