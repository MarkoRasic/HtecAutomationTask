package htecautomation.sandboxUI.utility.helper;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

import java.security.SecureRandom;
import java.util.Random;

/**
 * utility class for various helper methods
 */
public class HtecUiUtils extends PageObject {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    /**
     * method used to create locator based on given string
     * @param listItem string used to create locator
     */
    public void createLocator(String listItem) {
        String newLocator = "//*[contains(text(), '"+listItem+"')]";
        find(By.xpath(newLocator)).click();
    }

    /**
     * method used to confirm that element is visible
     * @param listItem string that will be used to create locator
     */
    public void confirmThatIsVisible(String listItem) {
        String newLocator = "//*[contains(text(), '"+listItem+"')]";
        find(By.xpath(newLocator)).isVisible();
    }

    /**
     * method which generates random string inside boundaries given
     * @return randomized string value
     */
    public static String randomString(){
        Random random = new Random();
        int len = random.nextInt(25) + 5;
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
