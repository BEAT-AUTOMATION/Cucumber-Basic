package Runner;


import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import resources.base;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/login.feature",
        glue = "src/test/java/StepDefinition/MyStepdefs.java"
)

public class Runner {
    base base = new base();


//    @Before
//    public void LaunchBrowser() throws IOException {
//        base.initializeDriver();
//    }

    @After
    public void  tearDown() {
    base.closeDriver();
    }
}