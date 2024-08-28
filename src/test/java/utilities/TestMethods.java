package utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMethods extends ExtentReport {

    public String[][] kategorilerLinkler = new String[][]{
            {"Electronics", "https://testotomasyonu.com/category/7/products"},
            {"Men Fashion", "https://testotomasyonu.com/category/1/products"},
            {"Women Fashion", "https://testotomasyonu.com/category/2/products"},
            {"Shoes", "https://testotomasyonu.com/category/3/products"},
            {"Furniture", "https://testotomasyonu.com/category/8/products"},
            {"Travel", "https://testotomasyonu.com/category/5/products"},
            {"Kids Wear", "https://testotomasyonu.com/category/6/products"},
            {"Grocery", "https://testotomasyonu.com/category/4/products"}};
    SoftAssert softAssert = new SoftAssert();
    TestOtomasyonu to = new TestOtomasyonu();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    List<String> urunIsimleri = new ArrayList<>();
    Actions actions = new Actions(Driver.getDriver());

    private String kategoriXPath(int i) {
        return "(//*[@class='has-sub'])[" + i + "]";
    }

    private String urunXPath(int i) {
        return "//section[2]/div[2]/div/div/ul/li[" + i + "]/div/a";
    }

    private String floatAddtoCart(int i) {
        return "//li[" + i + "]/div/div[2]/div[1]";
    }

    private String izgaradakiUrunIsmi(int i) {
        return "//ul/li[" + i + "]/div/div[3]/div/a";
    }

    private String urunSilmeButonu(int i) {
        return "//*[@id='shop-listing']/div[1]/div[1]/div/div[" + i + "]/button";
    }

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

    public void kategoriKontroluRaporlu(int i) throws IOException {
        /*REPORT*/
        extentTest.info("Kullanıcı kategori kontrolüne başlar.");

        String xpath = kategoriXPath(i + 1);
        String kategori = kategorilerLinkler[i][0];
        String expUrl = kategorilerLinkler[i][1];

        /*REPORT*/
        extentTest.info("-----------" + kategori + " KATEGORİ TESTİ-----------");

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

    }

    public void kategoriKontroluRaporsuz(int i) throws IOException {

        String xpath = kategoriXPath(i + 1);
        String kategori = kategorilerLinkler[i][0];
        String expUrl = kategorilerLinkler[i][1];

        //Sayfada aranacak kategorinin tiklanabilirligini test et
        WebElement kategoriE = Driver.getDriver().findElement(By.xpath(xpath)); //webelement 1den başlıyor
        ReusableMethods.scroll(kategoriE);
        softAssert.assertTrue(kategoriE.isEnabled());

        //Kategoriye tikla
        wait.until(ExpectedConditions.elementToBeClickable(kategoriE)).click();

        //Sayfa url'sini test et
        String actUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actUrl, expUrl);

        wait.until(ExpectedConditions.visibilityOf(to.kategoriBasligi)); //ekran resmi çekilebilmesi için bekleme koyuldu
        //Acilan sayfa başlığının kategori ismi oldugunu test et
        softAssert.assertEquals(to.kategoriBasligi.getText(), kategori);
        /*REPORT*/
        extentTest.pass(kategori + " kategorisine geçilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

    }

    public List<String> sepeteUrunEkle(int indeks) throws IOException {

        WebElement urun = Driver.getDriver().findElement(By.xpath(urunXPath(indeks)));
        wait.until(ExpectedConditions.visibilityOf(urun));
        softAssert.assertTrue(urun.isDisplayed());
        /*REPORT*/
        extentTest.info("Kullanıcı " + indeks + ". ürünü seçmiştir.");

        //Js ile ürüne scroll ile gidip tıklanır
        ReusableMethods.scroll(urun);
        ReusableMethods.click(urun);

        //Sayfa url'sinin https://testotomasyonu.com/product/" icerdigini test et
        String expUrl = "https://testotomasyonu.com/product/";
        String actUrl = Driver.getDriver().getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOf(to.addToCart));
        Assert.assertTrue(actUrl.contains(expUrl));

        //urun Ismi kaydedilir
        String isim = to.urunSayfasindaBulunanIsim.getText();
        urunIsimleri.add(isim);

        //Add to cart butonuna tikla
        ReusableMethods.click(to.addToCart);
        /*REPORT*/
        extentTest.info("Kullanıcı sepete ekle butonuna tıklar.");

        //Product Added To Cart! Yazisinin ciktigini dogrula
        softAssert.assertTrue(to.addedMessage.isDisplayed());
        /*REPORT*/
        extentTest.pass("Ürün sepete eklenmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String
                        (ReusableMethods.WEResmiBase64(to.addedMessage)).build());

        return urunIsimleri;
    }

    public String sepeteEkleIkonuIleUrunEkle(int indeks) throws IOException {

        WebElement urun = Driver.getDriver().findElement(By.xpath(urunXPath(indeks)));
        wait.until(ExpectedConditions.visibilityOf(urun));
        softAssert.assertTrue(urun.isDisplayed());
        /*REPORT*/
        extentTest.info("Kullanıcı " + indeks + ". ürünü seçmiştir.");

        //Ürünün üzerinde mouse ile durularak geçici butonun çıkması sağlanır ve tıklanır
        actions.moveToElement(urun).perform();

        //Urün ismi kaydedilir
        String isim = Driver.getDriver().findElement(By.xpath(izgaradakiUrunIsmi(indeks))).getText();

        //Ürün üzerine mouse ile gidince görünen add to cart butonu işlemleri
        WebElement floatButton = Driver.getDriver().findElement(By.xpath(floatAddtoCart(indeks)));
        wait.until(ExpectedConditions.elementToBeClickable(floatButton));
        actions.click(floatButton).perform();
        /*REPORT*/
        extentTest.info("Kullanıcı geçici buton olan sepete ekle butonuna tıklar.");

        //Product Added To Cart! Yazisinin ciktigini dogrula
        softAssert.assertTrue(to.addedMessage.isDisplayed());
        /*REPORT*/
        extentTest.pass("Ürün sepete eklenmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String
                        (ReusableMethods.WEResmiBase64(to.addedMessage)).build());

        return isim;
    }

    public void URLTestEt(String expUrl) {
        softAssert.assertEquals(Driver.getDriver().getCurrentUrl(), expUrl);
        extentTest.pass("URL testi tamamlanmıştır.");

    }

    public int kategorideUrunVarMi() {
        int sonucAdedi = 0;
        try {
            wait.until(ExpectedConditions.visibilityOf(to.bulunanUrunSayisi));
            sonucAdedi = Integer.parseInt(to.bulunanUrunSayisi.getText().replaceAll("\\D", ""));

            softAssert.assertTrue(sonucAdedi > 0);
            /*REPORT*/
            extentTest.info("Kategori içerisinde ürün bulunmaktadır.");
        } catch (Exception e) {
            /*REPORT*/
            extentTest.fail("Kategori içerisinde ürün bulunmamaktadır.");
        }
        return sonucAdedi;
    }

    public int sepetteUrunVarMi() {
        //sepete git
        wait.until(ExpectedConditions.elementToBeClickable(to.cart));
        ReusableMethods.click(to.cart);

        URLTestEt("https://testotomasyonu.com/shoppingCart");

        List<WebElement> sepetIcerigi = Driver.getDriver().findElements(By.xpath("//div/div[3]//input"));
        int urunSayisi = sepetIcerigi.size();

        softAssert.assertTrue(urunSayisi > 0, "Sepette ürün bulunmamaktadır.");
        /*REPORT*/
        extentTest.pass("Sepete gidilerek sayfada eklenen ürünün bulunduğu test edilmiştir.");

        return urunSayisi;
    }

    public Map<String, Integer> sepetteBulunanUrunler() throws IOException {

        int urunSayisi = sepetteUrunVarMi();

        int urunMiktari = 0;
        String isim;

        Map<String, Integer> urunAdiveQuantity = new HashMap<>();

        for (int i = 2; i < urunSayisi + 2; i++) { //ilk ürünün indeksi 2dir.
            String xpathQuantity = "//div[" + i + "]/div[3]//input";
            String xpathName = "//div[" + i + "]/div[2]/a";

            WebElement urunQuantity = Driver.getDriver().findElement(By.xpath(xpathQuantity));
            urunMiktari = Integer.parseInt(urunQuantity.getAttribute("value"));

            WebElement urunIsim = Driver.getDriver().findElement(By.xpath(xpathName));
            isim = urunIsim.getText();

            urunAdiveQuantity.put(isim, urunMiktari);
        }

        int toplam = 0;
        for (int value : urunAdiveQuantity.values()) {
            toplam += value;
        }
        /*REPORT*/
        extentTest.info("Sepette " + urunMiktari + " farklı ürün bulunmakta ve eklenen ürün miktarı " + toplam + ".",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        return urunAdiveQuantity;
    }

    public void sepettenUrunSil() throws IOException {
        int urunSayisi = sepetteUrunVarMi();
        boolean sepetBos = true;

        while (sepetBos) {

            for (int i = 2; i < urunSayisi + 2; i++) {

                //Urun kutusunun kosesindeki X butonunun tiklanabilirligini test et
                WebElement xButton = Driver.getDriver().findElement(By.xpath(urunSilmeButonu(i)));

                //X butonuna tikla
                ReusableMethods.click(xButton);

                //Are you sure penceresinin acildigini dogrula
                //Yes, remove it! Butonuna tikla
                softAssert.assertTrue(to.yesRemoveIt.isDisplayed());
                ReusableMethods.click(to.yesRemoveIt);

                //Wait for it... Yazisi kaybolana kadar bekle - invisibleELement
                wait.until(ExpectedConditions.visibilityOf(to.waitForIt));
            }

            wait.until(ExpectedConditions.invisibilityOf(to.waitForIt));
            sepetBos = !to.shoppingCartIsEmpty.isDisplayed();
            //--------yazı kaybolmasını beklemek zaman alıyor görünür olması yeterli
        }

        //Shopping cart is empty yazisi gorunur oldugunu test et
        Assert.assertTrue(to.shoppingCartIsEmpty.isDisplayed());
        /*REPORT*/
        extentTest.pass("Sepetteki ürünler boşaltılmıştır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
    }
}
