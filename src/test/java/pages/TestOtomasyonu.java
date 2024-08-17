package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class TestOtomasyonu {

    public TestOtomasyonu(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//*[text()='Account']")
    public WebElement account;

    @FindBy (xpath = "//*[@class='heading-xs my-2 text-center']")
    public WebElement logInFormHead;

    @FindBy (id = "email")
    public WebElement logInEmail;

    @FindBy (id = "password")
    public WebElement logInPassword;

    @FindBy (id = "submitlogin")
    public WebElement signIn;

    @FindBy (xpath = "//*[@class='sign-up ']")
    public WebElement signUpLink;

    @FindBy (id = "firstName")
    public WebElement signupfirstName;

    @FindBy (id = "lastName")
    public WebElement signuplastName;

    @FindBy (id = "signupemail")
    public WebElement signupemail;

    @FindBy (id = "signuppassword")
    public WebElement signuppassword;

    @FindBy (id = "comfirmPassword")
    public WebElement confirmPassword;

    @FindBy (xpath = "//*[@class='fa fa-check']")
    public WebElement successBox;

    @FindBy (id = "btn-submit-form")
    public WebElement signUpButton;

    @FindBy (className = "text-danger")
    public List<WebElement> required;

    @FindBy (xpath = "//span[text()='Confirm password does not match']")
    public WebElement confirmPasswordRequired;

}
