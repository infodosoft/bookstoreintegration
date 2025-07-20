package book.store.api.v1.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "classpath:features",
        glue = "book.store.api.v1.steps",
        tags = "@AllAPIs",
        plugin={"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:report/reports.html",
                "json:report/cucumber.json"
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}