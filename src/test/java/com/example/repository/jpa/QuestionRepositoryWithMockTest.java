package com.example.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionRepositoryWithMockTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private QuestionRepositoryImpl subjectToTest;

   // @Test
    void testGetAllQuestionsForCategoryById() {

        //GIVEN
        long id = 1L;
        Query query = Mockito.mock(Query.class);
        when(entityManager.createQuery(anyString())).thenReturn(query);

        //WHEN
        subjectToTest.getAllQuestionsForCategoryById(id);

        //THEN
        verify(entityManager).createQuery(anyString());
        verify(query).setParameter("id", id);
        verify(query).getResultList();
    }
}
