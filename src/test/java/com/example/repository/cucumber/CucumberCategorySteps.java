package com.example.repository.cucumber;

import com.example.annotation.EnableSqlLogging;
import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import com.example.repository.jpa.CategoryRepositoryImpl;
import com.github.database.rider.core.api.dataset.DataSetExecutor;
import com.github.database.rider.core.configuration.DataSetConfig;
import com.github.database.rider.core.connection.ConnectionHolderImpl;
import com.github.database.rider.core.dataset.DataSetExecutorImpl;
import com.github.database.rider.junit5.api.DBRider;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.github.database.rider.junit5.util.EntityManagerProvider.em;
import static com.github.database.rider.junit5.util.EntityManagerProvider.tx;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DBRider
@DataJpaTest(showSql = false)
@EnableSqlLogging
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = NONE)
//@ContextConfiguration(initializers = DatabaseContainerInitializer.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CucumberCategorySteps {

    EntityManagerProvider entityManagerProvider = EntityManagerProvider.newInstance("test");
    DataSetExecutor dbunitExecutor;

    private CategoryRepository categoryRepository;
    private Category category;

    @Before
    public void setUp(){
        dbunitExecutor = DataSetExecutorImpl
                .instance(new ConnectionHolderImpl(entityManagerProvider.connection()));
        em().clear();
    }


    @Given("^we have a list of categories$")
    public void given() {
        categoryRepository = new CategoryRepositoryImpl( em());
        dbunitExecutor = DataSetExecutorImpl
                .instance(new ConnectionHolderImpl(entityManagerProvider.connection()));
        em().clear(); // Clear jpa caches
        dbunitExecutor.createDataSet(new DataSetConfig("dataset/quiz.yaml"));
    }

    @When("^we search categories by id \"(\\d+)\"$")
    public void we_search_contacts_by_id_(Long id) throws Throwable {
        category = categoryRepository.getOne(id);
        assertNotNull( category );
    }


    @Then("^we should find categories ([^\"]*)$")
    public void we_should_find_result_contacts(String name) throws Throwable {
        assertEquals(name,category.getFullName());
    }


/*
    @When("^we delete contact by id (\\d+) 2$")
    public void we_delete_contact_by_id(long id) throws Throwable {
        tx().begin();
       // em().remove(em().find(Contact.class,id));
        tx().commit();
    }

    @Then("^we should not find contact (\\d+) 2$")
    public void we_should_not_find_contacts_in_database(long id) throws Throwable {
      //  assertNull(em().find(Contact.class,id));
    }

 */
}