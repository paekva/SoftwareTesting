package PageTests

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Login(private val driver: ChromeDriver){

    fun login(email: String, password: String){
        val emailInput = driver.findElement(By.id("signup_determine_email"))
        val forwardBtn = driver.findElement(By.id("signup_forms_submit"))
        emailInput.clear()
        emailInput.sendKeys(email)

        forwardBtn.click()

        val wait = WebDriverWait(driver, 5)
        val element = wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.linkText("Войти с паролем")))
        element.click()

        val pswdInput = driver.findElement(By.id("signup_password"))
        pswdInput.sendKeys(password)

        val loginBtn = driver.findElement(By.id("signup_forms_submit"))
        loginBtn.click()
    }

    fun logout(){
        val accountBtn = driver.findElement(By.xpath("//button[@title='Учетная запись']"))
        accountBtn.click()

        val wait = WebDriverWait(driver, 5)
        val logoutBtn = wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.id("logout_button")))
        logoutBtn.click()

        val okBtnText = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='ОК']")))
        val okBtn = okBtnText.findElement(By.xpath(""))
        okBtn.click()
    }
}