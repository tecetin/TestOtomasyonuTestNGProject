package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    private Driver(){

    }
    static WebDriver driver;
    public static WebDriver getDriver(){

        if (driver==null){
            switch (ConfigReader.getProperty("browser")){
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void closeDriver(){
        if(driver!=null){
            driver.close();
            driver = null;
        }
    }
    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }
}
