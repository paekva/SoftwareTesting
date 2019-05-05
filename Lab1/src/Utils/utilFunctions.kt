package Utils

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

fun printSuccessMsg(testName: String){
    println("\u001B[32m $testName was SUCCESSFUL \u001B[0m ")
}

fun printErrorMsg(testName: String, msg: String?){
    println("\u001B[41m $testName FAILED due to \u001B[0m \n\t $msg \n")
}

fun printInfoMsg(msg: String?){
    println("\u001B[36m $msg  \u001B[0m \n")
}

fun pressEscKey(driver: WebDriver){
    val action = Actions(driver)
    action.sendKeys(Keys.ESCAPE).perform()
}

fun waitForPresence(driver: WebDriver, time: Long, element: String){
    val wait = WebDriverWait(driver, time)
    wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className(element)))
}

fun waitToBeClickable(driver: WebDriver, time: Long, element: String){
    val wait = WebDriverWait(driver, time)
    wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className(element)))
}