package utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TestMethods extends ExtentReport {

    protected String kategoriXPath(int i) {
        return "(//*[@class='has-sub'])[" + i + "]";
    }
    SoftAssert softAssert = new SoftAssert();
    TestOtomasyonu to = new TestOtomasyonu();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    public String[][] kategorilerLinkler = new String[][]{
            {"Electronics", "https://testotomasyonu.com/category/7/products"},
            {"Men Fashion", "https://testotomasyonu.com/category/1/products"},
            {"Women Fashion", "https://testotomasyonu.com/category/2/products"},
            {"Shoes", "https://testotomasyonu.com/category/3/products"},
            {"Furniture", "https://testotomasyonu.com/category/8/products"},
            {"Travel", "https://testotomasyonu.com/category/5/products"},
            {"Kids Wear", "https://testotomasyonu.com/category/6/products"},
            {"Grocery", "https://testotomasyonu.com/category/4/products"}};

    public void sayfaDogrula() throws IOException {

        //url` ye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        /*REPORT*/
        extentTest.info("Kullanıcı anasayfaya gider");

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title));
        /*REPORT*/
        extentTest.pass("Kullanıcı başarılı bir şekilde anasayfaya gittiğini doğrular",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
    }
    public String kategoriKontroluRaporlu(int i) throws IOException {
        /*REPORT*/
        extentTest.info("Kullanıcı kategori kontrolüne başlar.");

        String xpath = kategoriXPath(i+1);
        String kategori = kategorilerLinkler[i][0];
        String expUrl = kategorilerLinkler[i][1];

        /*REPORT*/
        extentTest.info("-----------"+ kategori + " KATEGORİ TESTİ-----------");

        //Sayfada aranacak kategorinin tiklanabilirligini test et
        WebElement kategoriE = Driver.getDriver().findElement(By.xpath(xpath)); //webelement 1den başlıyor
        wait.until(ExpectedConditions.elementToBeClickable(kategoriE));
        softAssert.assertTrue(kategoriE.isEnabled());
        /*REPORT*/
        extentTest.pass(kategori + " kategorisi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(kategoriE)).build());

        //Kategoriye tikla
        /*REPORT*/
        extentTest.info("Kullanıcı " + kategori + " kategorisine tıklar.");
        kategoriE.click();

        //Sayfa url'sini test et
        String actUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actUrl, expUrl);
        /*REPORT*/
        extentTest.pass(kategori + " kategorisi tıklandığında kategori sayfasına geçtiği doğrulanır.");

        wait.until(ExpectedConditions.visibilityOf(to.kategoriBasligi));
        //Acilan sayfa başlığının kategori ismi oldugunu test et
        softAssert.assertEquals(to.kategoriBasligi.getText(), kategori);
        /*REPORT*/
        extentTest.pass(kategori + " kategori sayfası başlığında kategori adı göründüğü doğrulanır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        return kategori;
    }
    public String kategoriKontroluRaporsuz(int i) throws IOException {

        String xpath = kategoriXPath(i+1);
        String kategori = kategorilerLinkler[i][0];
        String expUrl = kategorilerLinkler[i][1];

        //Sayfada aranacak kategorinin tiklanabilirligini test et
        WebElement kategoriE = Driver.getDriver().findElement(By.xpath(xpath)); //webelement 1den başlıyor
        wait.until(ExpectedConditions.elementToBeClickable(kategoriE));
        softAssert.assertTrue(kategoriE.isEnabled());

        //Kategoriye tikla
        kategoriE.click();

        //Sayfa url'sini test et
        String actUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actUrl, expUrl);

        wait.until(ExpectedConditions.visibilityOf(to.kategoriBasligi)); //ekran resmi çekilebilmesi için bekleme koyuldu
        //Acilan sayfa başlığının kategori ismi oldugunu test et
        softAssert.assertEquals(to.kategoriBasligi.getText(), kategori);
        /*REPORT*/
        extentTest.pass(kategori + " kategorisine geçilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        return kategori;
    }
}
