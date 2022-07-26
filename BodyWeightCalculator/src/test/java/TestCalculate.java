import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.awt.SystemColor.text;

public class TestCalculate {
    WebDriver driver;
    private static final String baseUrl = "https://healthunify.com/bmicalculator/";

    @BeforeMethod
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }


    private void InputWeightHeight(String weight, String height) {
        driver.findElement(By.name("wg")).sendKeys(weight);
        driver.findElement(By.name("ht")).sendKeys(height);
        driver.findElement(By.cssSelector("[value=Calculate]")).click();
    }

    private String results() {
        return driver.findElement(By.className("content")).getAttribute("value");
    }

    @Test
    public void NormalWeight() {
        InputWeightHeight("80", "191");
        System.out.println(results());
    }

    @Test
    public void UnderWeight() {
        InputWeightHeight("70", "196");
        System.out.println(results());
    }

    @Test
    public void Starvation() {
        InputWeightHeight("55", "200");
        System.out.println(results());
    }

    @Test
    public void Overweight() {
        InputWeightHeight("90", "180");
        System.out.println(results());

    }

    @Test
    public void Obese() {
        InputWeightHeight("100", "180");
        System.out.println(results());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
