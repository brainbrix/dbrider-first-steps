package com.example.repository.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.annotation.PersistenceLayerTest;
import com.example.entity.Category;
import com.example.exception.DbResultNotFoundException;
import com.example.repository.CategoryRepository;
import com.github.database.rider.core.api.dataset.DataSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@PersistenceLayerTest
class CategoryRepositoryImplExistsTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DataSet("dataset/quiz.yaml")
    void categoryExist() {
        //GIVEN
        long existingId = 1L;

        //WHEN
        Category c = categoryRepository.findByIdMandatory(existingId);

        //THEN
        assertThat(c.getId()).isEqualTo(existingId);
    }

    @Test
    @DataSet("dataset/quiz.yaml")
    void categoryNotExist() {
        //GIVEN
        long notExistId = 99L;

        //WHEN
        DbResultNotFoundException exception = assertThrows(DbResultNotFoundException.class,
                () -> categoryRepository.findByIdMandatory(notExistId));

        //THEN
        assertThat(exception.getMessage()).isEqualTo("Entity [Category] with id [99] was not found in DB");
    }

}