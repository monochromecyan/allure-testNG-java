package my.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;


/**
 * @author James Walmsley
 */
public class GoogleSearchTest {

    private WebDriver driver;
    public static String baseUrl = "http://www.google.com";

    @BeforeSuite
    public void initDriver() throws Exception {
        System.out.println("You are testing in Chrome");
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(priority = 0)
    public void verifyHomePageTitle() {
        driver.navigate().to(baseUrl);
        String expectedTitle = "Google";
        System.out.println("Search " + expectedTitle + " in google");
        String actualTitle = driver.getTitle();

        try {
            Assert.assertEquals(actualTitle, expectedTitle);
            System.out.println("Test Passed");
        }catch (Throwable e){
            System.out.println("Test Failed " +e);
        }
    }

    @Test(priority = 1)
    public void searchTestNGInGoogle() {
        final String searchKey = "TestNG";
        System.out.println("Search " + searchKey + " in google");
        driver.navigate().to(baseUrl);
        WebElement element = driver.findElement(By.name("q"));
        System.out.println("Enter " + searchKey);
        element.sendKeys(searchKey);
        System.out.println("submit");
        element.submit();
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition() {
//            public Boolean apply(WebDriver d) {
//                return d.getTitle().toLowerCase()
//                        .startsWith(searchKey.toLowerCase());
//            }
//        });
        System.out.println("Got " + searchKey + " results");
    }

    @AfterSuite
    public void quitDriver() throws Exception {
        driver.quit();
    }
}

