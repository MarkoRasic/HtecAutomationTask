package htecautomation.sandboxUI.utility.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * class for storing test data
 */
public class TestDataConstants {

    public static final String EMAIL = "markorasic62@gmail.com";
    public static final String PASSWORD = "Sifra123!";
    public static List<String> setTitleFieldsToList = new ArrayList<>();

    public static Integer noOfUseCases;

    public static Integer getNoOfUseCases() {
        return noOfUseCases;
    }

    public static void setNoOfUseCases(Integer noOfUseCases) {
        TestDataConstants.noOfUseCases = noOfUseCases;
    }



}
