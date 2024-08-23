package tests.US_003;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TC_002 extends ExtentReport {
    SoftAssert softAssert = new SoftAssert();
    TestOtomasyonu to = new TestOtomasyonu();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));


    @Test
    public void kategoriTesti2() throws IOException {
        //url` ye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        /*REPORT*/
        extentTest = extent.createTest("Sayfa Kategori Testi",
                "Kullanıcı Anasayfada bulunan kategorilerin arama kutusu kategorilerinde de bulunduğunu doğrular");

        //url` ye git
        /*REPORT*/
        extentTest.info("Kullanıcı anasayfaya gider");

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title));
        /*REPORT*/
        extentTest.pass("Kullanıcı başarılı bir şekilde anasayfaya gittiğini doğrular");

        //Anasayfada kategorilerin gorunurlugunu test et
        softAssert.assertFalse(to.kategoriListe.isEmpty());
        /*REPORT*/
        extentTest.pass("Kullanıcı anasayfada kategorilerin göründüğünü doğrular",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Anasayfa kategori isimlerini liste olarak kaydet
        List<String> kategorilerAnasayfa = ReusableMethods.toStringList(to.kategoriListe);
        int AnasayfaListSize = to.kategoriListe.size();

        //Arama kutusundaki Select Category butonunun gorunurlugunu test et
        softAssert.assertTrue(to.selectCategory.isDisplayed());

        //Select Category butonuna tikla
        /*REPORT*/
        extentTest.info("Kullanıcı Select Category listesine tıklar.");
        to.selectCategory.click();

        //Kategori listesinin gorunur oldugunu test et
        softAssert.assertTrue(to.selectCategoryListOpen.isDisplayed());
        /*REPORT*/
        extentTest.pass("Kullanıcı arama kutusundaki Select Category listesinde kategorilerin göründüğünü doğrular",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Kategori listesindeki isimleri liste olarak kaydet
        List<String> kategoriList = ReusableMethods.toStringList(to.selectCategoryListe);
        int AramaListSize = to.selectCategoryListe.size() - 1;  //Select category başlığını almamak için -1 den başladı

        //Anasayfa kategori listesi ile Arama kutusu kategori listesi uzunluklarinin ayni oldugunu dogrula
        softAssert.assertEquals(AnasayfaListSize, AramaListSize);

        //Anasayfa kategori listesindeki kategorinin Arama kutusu kategori listesinde oldugunu dogrula
        for (int i = 0; i < AnasayfaListSize; i++) {
            String kategori = kategorilerAnasayfa.get(i);
            softAssert.assertEquals(kategori, kategoriList.get(i));
            /*REPORT*/
            extentTest.pass("Kullanıcı anasayfadaki " + kategori +" kategorisinin Select Category listesinde de bulunmaktadır.");
        }
    }
}
