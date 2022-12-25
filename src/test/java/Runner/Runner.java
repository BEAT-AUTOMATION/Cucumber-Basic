package Runner;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import resources.base;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/BookStore.feature",
        glue = "src/test/java/StepDefinition/MyStepdefs.java"
)

public class Runner {
    base base = new base();


    @Before
    public WebDriver LaunchBrowser() throws IOException {
        WebDriver driver = base.initializeDriver();
        return driver;
    }

    @After
    public void tearDown() {
        base.closeDriver();
    }
}