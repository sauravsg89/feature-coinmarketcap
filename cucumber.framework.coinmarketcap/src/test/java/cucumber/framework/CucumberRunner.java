package cucumber.framework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
				"pretty",
				"html:target/cucumber-reports/cucumber-pretty",
		},
		features = {"classpath:features"},
		glue = {"stepdefs"},
		tags= "@FUNCTIONAL"
//		tags= "@GUI"
//		tags= "@API"
//		tags= "@BETASK1"
//		tags= "@FETASK1"
		)
public class CucumberRunner {

}
