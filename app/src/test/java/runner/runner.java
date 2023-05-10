package runner;

import org.junit.AfterClass;
import org.junit.runner.*;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import pages.BasePage;

@RunWith(Cucumber.class)
@CucumberOptions(
    // Donde están los test
    features = "src/test/resources/features",    
    glue = "steps",
    /* plugin= {"json:target/cucumber-reports.json",
                "pretty","html:test-output/AventReport/",
                "com.cucumber.listener.ExtentCucumberFormatter"},*/
    tags = ("@Test")
)

public class runner{
    @AfterClass
    public static void cleanDriver(){
        BasePage.closeBrowser(); // Metodo escrito en BasePage para cerrar el navegador (driver.quit)
    }
}