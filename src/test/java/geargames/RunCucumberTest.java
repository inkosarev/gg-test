package geargames;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/geargames", // Путь к файлам .feature
        glue = "geargames"                        // Пакет с StepDefinitions
)
public class RunCucumberTest {
}