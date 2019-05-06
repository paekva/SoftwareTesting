package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class RegisterPage(private val driver: WebDriver){

    @FindBy(id= "signup_email")
    private var email: WebElement? = null

    @FindBy(id="signup_password")
    private var password: WebElement? = null

    @FindBy(id="signup_username")
    private var name: WebElement? = null

    @FindBy(id="signup_forms_submit")
    private var submitBtn: WebElement? = null

    @FindBy(id="signup_age")
    private var age: WebElement? = null

    @FindBy(id="signup_tos")
    private var rules: WebElement? = null

    @FindBy(className="recaptcha-checkbox-checkmark")
    private var recapcha: WebElement? = null

    @FindBy(xpath = "//*[@id=\"signup_form_errors\"]/li")
    private val errorMsg: WebElement? = null

    init {
        if (!driver.currentUrl.contains("regist"))
            throw IllegalStateException("This is not the page you are expected")

        PageFactory.initElements(driver, this)
    }

    fun fillInEmail(userEmail: String): RegisterPage{
        email!!.sendKeys("")
        email!!.sendKeys(userEmail)

        return this
    }

    fun fillInPassword(userPassword: String): RegisterPage{
        password!!.sendKeys("")
        password!!.sendKeys(userPassword)

        return this
    }

    fun fillInName(userName: String): RegisterPage{
        name!!.sendKeys("")
        name!!.sendKeys(userName)

        return this
    }

    fun fillInAge(ageStr: String): RegisterPage{
        age!!.sendKeys("")
        age!!.sendKeys(ageStr)

        return this
    }

    fun checkInRules(): RegisterPage{
        rules!!.click()

        return this
    }

    fun forward(): RegisterPage {
        submitBtn!!.click()

        val wait = WebDriverWait(driver, 20)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.id("signup_age")))
        return this
    }

    fun regist() {
        submitBtn!!.click()
    }

    fun doRecapcha(): RegisterPage  {
        recapcha!!.click()
        return this
    }

    fun getError(): WebElement? {
        return errorMsg
    }
}


