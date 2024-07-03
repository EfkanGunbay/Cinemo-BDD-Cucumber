package com.cinemo.runners;

import com.cinemo.utilities.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.util.concurrent.TimeUnit;


@CucumberOptions(
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json"},
        glue = "com/cinemo/step_definitions",
        features = "src/test/resources/Features",
        publish = true,
        tags = "@Reg"



)
public class CukesRunner extends AbstractTestNGCucumberTests {

    public void setUp(){
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Driver.getDriver().get(ConfigReader.getProperty("url_live"));
    }




}