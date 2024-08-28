package tests.US_004;

import org.testng.annotations.Test;
import utilities.ExtentReport;
import utilities.TestMethods;

import java.io.IOException;

public class TC_003 extends ExtentReport {

    @Test
    public void sepettenSil() throws IOException {
        TestMethods test = new TestMethods();

        /*REPORT*/
        extentTest = extent.createTest("Sepetten Ürün Silme",
                "Kullanıcı sepete eklenen ürünler ile sayfadaki ürünlerin ayni oldugunu test eder.");

        //url` ye git
        //Sayfaya gittigini dogrula
        //Top Selling Products bolumune git
        //View All Products butonunun tiklanabilirligini test et
        //View All Products butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/trending/all-products" oldugunu test et
        //Kategoride 0'dan fazla urun bulundugunu dogrula
        //Listedeki ilk urunun uzerine git
        //Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et
        //Add to cart butonuna tikla
        //Product Added To Cart! Yazisinin ciktigini dogrula
        //Your Cart butonunun gorunurlugunu test et
        //Your Cart butonunun sayisininin 1 oldugunu dogrula
        //Your cart butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
        //Sepette urun bulundugunu test et
        TC_002 test2 = new TC_002();
        test2.runValidation = false;
        test2.urunKontrolu();

        //Urun kutusunun kosesindeki X butonunun tiklanabilirligini test et
        //X butonuna tikla
        //Are you sure penceresinin acildigini dogrula
        //Yes, remove it! Butonuna tikla
        //Wait for it... Yazisi kaybolana kadar bekle - invisibleELement
        //Shopping cart is empty yazisi gorunur oldugunu test et
        test.sepettenUrunSil();
    }
}
