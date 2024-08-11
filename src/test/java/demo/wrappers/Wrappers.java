package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;  


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static void enterText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void radioButton(ChromeDriver driver,String radioButtonText){
        try {
            WebElement element = driver.findElement(By.xpath("//span[contains(@class, 'OvPDhc') and contains(text(),'"+ radioButtonText +"')]/../../..//div[@class='YEVVod']"));
            element.click();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkBox(ChromeDriver driver,String checkBoxText){
        try {
            WebElement element = driver.findElement(By.xpath("//span[contains(@class, 'n5vBHf') and contains(text(),'"+ checkBoxText +"')]/../../preceding-sibling::div[contains(@id,'i')]"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dropDownClick(ChromeDriver driver,String dropDowntext){
        try {
            WebElement element = driver.findElement(By.xpath("//div[contains(@class,'QXL7Te')]//div[@data-value='"+ dropDowntext +"']"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void clickOnElement(ChromeDriver driver,WebElement element){
        try {
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();        }
    }
    public static String getDateSevenDaysAgo(){
        LocalDate currentDate = LocalDate.now();
        //subtract 7 days
        LocalDate dateMinus7Days = currentDate.minusDays(7);
        //Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //Format the currentDate
        String formattedDate = dateMinus7Days.format(formatter);
        return formattedDate;
    }
    public static String getCurrentTime(){
        //Get currentTime
        LocalTime currentTime = LocalTime.now();

        //Define the disired format(eg. HH:MM)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        //Format the current time
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }

    public static String getEpochTimeAsString(){

        //Create Epoch Time String
        Long epochTime = System.currentTimeMillis()/1000;
        String epochTimeString = String.valueOf(epochTime);

        return epochTimeString;
    }

}
