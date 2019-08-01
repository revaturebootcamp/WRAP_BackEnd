package com.revature.runners;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith (Cucumber.class)
@CucumberOptions (
    features = { "features/loginAccount.feature" },
    glue = { "com.revature.gluecode" }
)
public class WrapLoginRunner {

}
