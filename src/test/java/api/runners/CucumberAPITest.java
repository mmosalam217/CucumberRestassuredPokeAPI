package api.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(tags="", glue="api.definitions",
					plugin= {},
					features= {"src/test/resources/features/pokemon.feature",
							   "src/test/resources/features/game.feature"})
public class CucumberAPITest extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object [][] scenarios(){
		return super.scenarios();
	}
}
