package utilities;


import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviderUtil {

    @DataProvider(name = "listedenData")
    public static Object[][] aranacakKelimelerDataProvider() {

        String[][] aranacakUrunler = {{""},{""}};

        return aranacakUrunler;
    }

}
