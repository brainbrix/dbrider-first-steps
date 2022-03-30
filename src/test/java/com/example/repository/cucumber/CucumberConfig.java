package com.example.repository.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/test.feature"},
        glue = { "com.example.repository.cucumber" }

)
public class CucumberConfig {

}