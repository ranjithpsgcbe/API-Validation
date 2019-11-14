package com.api.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = { "src/test/resources/features/api/api.feature" }, glue = {
		"com.api.apisteps" })
public class APITest {
}
