package demo;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Assert;
import dev.failsafe.internal.util.Durations;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testCase01() throws InterruptedException{
        //Navigate to google form
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        System.out.println("Google Form is Opening:");
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameinputTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'oJeWuf')]//input[@type='text'])[1]")));
        Wrappers.enterText(nameinputTextBox, "Crio Learner");
        Thread.sleep(5000);

        WebElement practicingAutomationTextArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[contains(@class, 'tL9Q4c')]")));
        String practingAutomationText = "I want to be the best QA Engineer!";
        //Create epoch time as string
        String epochTimeString = Wrappers.getEpochTimeAsString();
        Wrappers.enterText(practicingAutomationTextArea, practingAutomationText +" "+epochTimeString);

        //Select the radio button as per automation exp
        Wrappers.radioButton(driver, "0 - 2");
        System.out.println("Radio Button selected as per my exp");
        Thread.sleep(3000);
        
        //Select the checkBox for skilSet
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");
        Thread.sleep(3000);
        System.out.println("CheckBox selected as per option i provided");

        //Click on dropdown
        WebElement dropDoWebElement = driver.findElement(By.xpath("//div[@jsname='LgbsSe']"));
        Wrappers.clickOnElement(driver, dropDoWebElement);
        wait.until(ExpectedConditions.elementToBeClickable(dropDoWebElement));
        Wrappers.dropDownClick(driver, "Mr");
        Thread.sleep(3000);


        //Enter 7 days ago date
        WebElement dateInputBox = driver.findElement(By.xpath("//input[@type='date']"));
        String sevenDaysAgodate = Wrappers.getDateSevenDaysAgo();
        Wrappers.enterText(dateInputBox, sevenDaysAgodate);
        Thread.sleep(3000);

        //Enter current time in HH:MM
        String currentTime = Wrappers.getCurrentTime();
        String [] currentTimeInHHMM = currentTime.split(":");
        String HH = currentTimeInHHMM[0];
        String MM = currentTimeInHHMM[1];

        WebElement HHElement = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement MMElement = driver.findElement(By.xpath("//input[@aria-label='Minute']"));

        Wrappers.enterText(HHElement, HH);
        Thread.sleep(3000);
        Wrappers.enterText(MMElement, MM);
        Thread.sleep(3000);

        //submit the google form
        WebElement submitElement = driver.findElement(By.xpath("//span[contains(text(),'Submit')]"));
        Wrappers.clickOnElement(driver, submitElement);

        //Validate the success message
        WebElement successmsgElement =driver.findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']")));
        Assert.isTrue(successmsgElement.isDisplayed(),successmsgElement.getText());
        System.out.println(successmsgElement.getText());
        Thread.sleep(3000);

    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}