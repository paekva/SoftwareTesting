import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class RegistrationPage(private val driver: WebDriver) {

    @FindBy(id = "signup_username")
    private val login: WebElement? = null

    @FindBy(id = "signup_password")
    private val password: WebElement? = null

    @FindBy(id = "signup_email")
    private val email: WebElement? = null

    @FindBy(id = "signup_forms_submit")
    private val submitBtn: WebElement? = null

    @FindBy(className = "error")
    private val registerError: WebElement? = null

    init {
        if (!driver.currentUrl.contains("register")) {
            throw IllegalStateException(
                    "This is not the page you are expected"
            )
        }

        driver.findElement(By.id("signup_forms_submit")).click()
        val wait = WebDriverWait(driver, 30)
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.id("signup_email")))

        PageFactory.initElements(driver, this)
    }

    private fun registerUser(user: User) {

        login!!.clear()
        password!!.clear()
        email!!.clear()

        login.sendKeys(user.login)
        password.sendKeys(user.password)
        email.sendKeys(user.email)

        submitBtn!!.click()
    }

    fun registerUserSuccess(user: User): HomePage {
        registerUser(user)
        return HomePage(driver)
    }

    fun registerUserError(user: User): RegistrationPage {
        registerUser(user)
        return RegistrationPage(driver)
    }

    fun checkErrorMessage(errorMessage: String): RegistrationPage {
        if(!registerError!!.isDisplayed || registerError.text != errorMessage)
            throw Exception()

        return this
    }
}