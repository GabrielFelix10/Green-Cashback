package behavior;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author gfaraujo
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/behavior/calculateCashback.feature", plugin = {"pretty", "html:target/cucumber"})
public class CashBackRunner {
}
