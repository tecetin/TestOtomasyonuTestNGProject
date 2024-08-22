package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.FuzzyMap;
import org.testng.IResultMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import javax.naming.Name;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ExtentReport {
    protected static ExtentReports extent;
    protected static ExtentTest extentTest;
    protected static ExtentHtmlReporter extentHtmlReporter;

    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        extent = new ExtentReports(); // Raporlamayi baslatir

        //rapor oluştuktan sonra raporunuz nereye eklensin
        String tarih = new SimpleDateFormat("_dd_MM_yy-HH_mm_ss").format(new Date());
        String dosyaYolu = System.getProperty("user.dir") + "/target/extentReport/Report" + tarih + ".html";

        //oluşturmak istediğimiz raporu (html formatında) başlatıyoruz, dosya yolunu belirliyoruz.
        extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
        extent.attachReporter(extentHtmlReporter);

        // Bilgileri buraya ekle
        extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extent.setSystemInfo("Tester", ConfigReader.getProperty("testerName"));
        extentHtmlReporter.config().setDocumentTitle("Extent Report");
        extentHtmlReporter.config().setReportName("TestNG Reports");
        extentHtmlReporter.config().setTheme(Theme.DARK);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) { // eğer testin sonucu başarısızsa
            String screenshotLocation = ReusableMethods.sayfaSSBase64();
            extentTest.fail(result.getName() + " testi başarısız oldu.");
            extentTest.fail(result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String((screenshotLocation)).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) { // Eğer test başarılı olduysa
            extentTest.pass("Test Case başarılı: " + result.getName());
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {

        // Raporu bitirir ve dosyaya kaydeder
        if (extent != null) {
            extent.flush();
        }
    }
}
