package book.store.api.v1.hooks;

import book.store.api.v1.steps.CommonSteps;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class Hooks {
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Failure Response", "application/json",
                    new ByteArrayInputStream(CommonSteps.latestResponse.asByteArray()), ".json");
        }
    }
}
