package tests.US_002;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_001 extends accountBase {

    //Dinamik xpath
    private String bilgiXpath(String configKey) {
        String bilgi = "";
        switch (configKey) {
            case "firstName":
                bilgi = ConfigReader.getProperty("firstName");
                break;
            case "lastName":
                bilgi = ConfigReader.getProperty("lastName");
                break;
            case "email":
                bilgi = ConfigReader.getProperty("email");
                break;
        }
        return "//input[@value='" + bilgi + "']";
    }

    @Test
    public void accountTest01() {

        //Kullanici First Name gorunurlugunu test et
        WebElement firstName = Driver.getDriver().findElement(By.xpath(bilgiXpath("firstName")));
        Assert.assertTrue(firstName.isDisplayed());

        //Kullanici Last Name gorunurlugunu test et
        WebElement lastName = Driver.getDriver().findElement(By.xpath(bilgiXpath("lastName")));
        Assert.assertTrue(lastName.isDisplayed());

        //Kullanici email gorunurlugunu test et
        WebElement email = Driver.getDriver().findElement(By.xpath(bilgiXpath("email")));
        Assert.assertTrue(email.isDisplayed());

    }
}

