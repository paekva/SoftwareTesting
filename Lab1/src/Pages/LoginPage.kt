package Pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class LoginPage(private val driver: WebDriver){

    @FindBy(id="signup_determine_email")
    private var email: WebElement? = null

    @FindBy(id="signup_password")
    private var password: WebElement? = null

    @FindBy(id="signup_forms_submit")
    private var submitBtn: WebElement? = null

    @FindBy(xpath= "//*[@id=\"signup_magiclink\"]/div[2]/a")
    private var loginWithPswrdBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"signup_form_errors\"]/li")
    private val errorMsg: WebElement? = null

    init {
        if (!driver.currentUrl.contains("login"))
            throw IllegalStateException("This is not the page you are expected")

        PageFactory.initElements(driver, this)
    }

    fun fillInEmail(userEmail: String): LoginPage{
        email!!.clear()
        email!!.sendKeys(userEmail)
        submitBtn!!.click()

        return this
    }

    fun fillInPassword(userPassword: String): LoginPage{
        val wait = WebDriverWait(driver, 5)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(loginWithPswrdBtn)).click()

        password!!.clear()
        password!!.sendKeys(userPassword)

        return this
    }

    fun login(): HomePage {
        submitBtn!!.click()

        return HomePage(driver)
    }

    fun getError(): WebElement? {
        return errorMsg
    }
}

