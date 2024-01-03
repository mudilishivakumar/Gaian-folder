package runner;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
		dryRun = false, //to check the mapping is proper between feature file and step def file
		monochrome = true, //display the console output in a proper readable format
		strict = true, //it will check if any step is not defined in step definition file
		glue = {"stepDefinitions"},
		plugin = {
				"pretty",
				"usage:target/cucumber-reports/cucumber_report_usage.json",
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
				"json:target/cucumber-reports/cucumber.json"}
		//, tags = {"@all"}
)
public class TestRunner {
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("config/report.xml"));
	}

}
