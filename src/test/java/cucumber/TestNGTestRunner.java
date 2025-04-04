package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//get the feature files
@CucumberOptions(features="src/test/java/cucumber", glue="cattleyanadora.stepDefinitions",
monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	 //tags = "@Regression" use this if you want only this feature file to test
}
