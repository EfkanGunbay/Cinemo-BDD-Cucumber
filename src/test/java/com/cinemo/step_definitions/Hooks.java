package com.cinemo.step_definitions;

import com.cinemo.utilities.ConfigReader;
import com.cinemo.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){

        //ChromeDriver driver = new ChromeDriver(options);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        Driver.getDriver().manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        Driver.getDriver().manage().deleteAllCookies();

    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){

            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

       Driver.closeDriver();

    }



}