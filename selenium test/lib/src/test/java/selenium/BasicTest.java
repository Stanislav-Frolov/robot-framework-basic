package selenium;

import java.io.IOException;
import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class BasicTest extends TestCase {

	private WebDriver driver;

	@BeforeClass
	public static void createAndStartService() throws IOException {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/browser/driver/chromedriver.exe");
	}

	@AfterClass
	public static void createAndStopService() {

	}

	@Before
	public void createDriver() {
		driver = new ChromeDriver();
	}

	@After
	public void quitDriver() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	public void testGoogleSearch() throws InterruptedException {

		driver.manage().window().maximize();

		driver.navigate().to("https://store.steampowered.com/");

		By loginButtonLocator = By.xpath("//a[@class= \"global_action_link\" and text()=\"login\"]");

		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));

		findElement(loginButtonLocator).click();

		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("login_btn_ctn")));

		findElement(By.linkText("Join Steam")).click();

		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("join_form")));

		driver.findElement(By.name("email")).sendKeys("email.com");
		driver.findElement(By.name("reenter_email")).sendKeys("email.com");

		Select countrySelect = new Select(driver.findElement(By.xpath("//select[@id=\"country\"]")));
		countrySelect.selectByVisibleText("Norway");

		System.out.println("select country " + countrySelect.getFirstSelectedOption().getText());

		WebElement acceptButton = findElement(By.xpath("//input[@type=\"checkbox\" and @name='i_agree_check']"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", acceptButton);

		acceptButton.click();

	}

	private WebElement findElement(By condition) throws InterruptedException {
		Thread.sleep(2000);
		return driver.findElement(condition);
	}

}
