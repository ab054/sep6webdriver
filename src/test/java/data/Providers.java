package data;

import org.testng.annotations.DataProvider;

public class Providers {

    public static final String STRINGS_FOR_QUERY = "strings for query";

    @DataProvider(name = STRINGS_FOR_QUERY)
    public static Object[][] createData1() {
        return new Object[][]{
                {"Portnov Computer School"},
                {"Portnov School"},
        };
    }

}
