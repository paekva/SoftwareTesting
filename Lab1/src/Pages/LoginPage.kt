package Pages

import Utils.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class LoginPage(private val driver: WebDriver){

    private var email: WebElement? = null
    private var password: WebElement? = null
    private var submitBtn: WebElement? = null
    private val registerError: WebElement? = null

    init {
        if (!driver.currentUrl.contains("login"))
            throw IllegalStateException("This is not the page you are expected")

        email = driver.findElement(By.xpath("//*[@id=\"signup_determine_email\"]"))
        submitBtn = driver.findElement(By.xpath("//*[@id=\"signup_forms_submit\"]"))
    }

    fun loginFail(user: UserCredentials): LoginPage {
        login(user)
        return LoginPage(driver)
    }

    fun login(user: UserCredentials) {
        email!!.sendKeys(user.email)
        submitBtn!!.click()

        val wait = WebDriverWait(driver, 5)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.linkText("Войти с паролем"))).click()

        password = driver.findElement(By.xpath("//*[@id=\"signup_password\"]"))
        password!!.sendKeys(user.password)

        submitBtn!!.click()
    }

}

