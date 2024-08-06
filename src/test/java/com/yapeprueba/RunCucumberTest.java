package com.yapeprueba;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(publish = true,features = "src/test/resources/features", glue = "com.yapeprueba.steps")
public class RunCucumberTest {
}
