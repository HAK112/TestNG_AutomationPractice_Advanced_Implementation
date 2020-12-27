package com.seleniumGrid.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGridParallelTest {
	WebDriver driver;
    String nodeURL,browser;
    
    @Parameters({"Port"})
    @BeforeMethod()
    public void setUp(String Port) throws MalformedURLException
    {         
        if(Port.equalsIgnoreCase("4546"))
        {
        	browser="Chrome";
        	nodeURL = "http://192.168.1.4:4546/wd/hub";
            System.out.println("Chrome Browser Initiated");
            ChromeOptions capabilities = new ChromeOptions();       
            
            driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
            
            driver.get("https://www.apple.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        
        else if(Port.equalsIgnoreCase("5566"))
            {
        	browser="Firefox";
        		nodeURL = "http://192.168.1.4:5566/wd/hub";
                System.out.println("Firefox Browser Initiated");
                FirefoxOptions capabilities1 = new FirefoxOptions();
                
                driver = new RemoteWebDriver(new URL(nodeURL),capabilities1);   
                
                driver.get("https://www.apple.com/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        
        else if(Port.equalsIgnoreCase("4547"))
        {
        	browser="Opera";
        	nodeURL = "http://192.168.1.4:4547/wd/hub";
            System.out.println("Opera Browser Initiated");
            OperaOptions capabilities2 = new OperaOptions();
            
            driver = new RemoteWebDriver(new URL(nodeURL),capabilities2);
            
            driver.get("https://www.apple.com/");
            driver.manage().window().maximize();    
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else if(Port.equalsIgnoreCase("4548"))
        {
        	browser="Edge";
        	nodeURL = "http://192.168.1.4:4548/wd/hub";
            System.out.println("Edge Browser Initiated");
            EdgeOptions capabilities2 = new EdgeOptions();
            
            driver = new RemoteWebDriver(new URL(nodeURL),capabilities2);
            
            driver.get("https://www.apple.com/");
            driver.manage().window().maximize();    
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
    @Test
    public void appleSite() throws InterruptedException
    {
        try
        {
        driver.getTitle();
        System.out.println("Test Completed");
        }
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    
    @AfterMethod()
    public void tearDown()
    {
            driver.quit();
            System.out.println(browser+" Browser Closed");
    }
}
