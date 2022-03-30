package com.example.repository.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.annotation.PersistenceLayerTest;
import com.example.entity.Category;
import com.example.exception.DbResultNotFoundException;
import com.example.repository.CategoryRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@PersistenceLayerTest
class CategoryRepositoryImplExpectDatasetTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DataSet("dataset/quiz.yaml")
    @ExpectedDataSet(value = "dataset/quizExpectAfterDelete.yaml")
    void categoryExpectAfterDelete() {
        //GIVEN
        long existingId = 1L;

        //WHEN
        Category c = categoryRepository.findByIdMandatory(existingId);
        categoryRepository.delete(c);

        //THEN
        // See @ExpectedDataSet-Annotation
    }

    @Test
    @DataSet("dataset/quiz.yaml")
    @ExpectedDataSet("dataset/quizExpectWithRegex.yaml")
    void categoryCreateWithExpectRegex() {
        //GIVEN
        // See @DataSet annotation

        //WHEN
        Category c = new Category();
        c.setFullName( "NewCategory" );
        categoryRepository.save( c );

        //THEN
        // See @ExpectedDataSet annotation
    }

    @Test
    @DataSet("dataset/quiz.yaml")
    @ExpectedDataSet("dataset/quizExpectWithScript.yaml")
    void categoryCreateAndCheckWithScript() {
        //GIVEN
        // See @DataSet annotation

        //WHEN
        Category c = new Category();
        c.setFullName( "NewCategory" );
        categoryRepository.save( c );

        //THEN
        // See @ExpectedDataSet annotation
    }

}