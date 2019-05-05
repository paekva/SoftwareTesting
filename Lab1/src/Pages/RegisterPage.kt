package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class RegisterPage(private val driver: WebDriver){

    @FindBy(id= "signup_email")
    private var email: WebElement? = null

    @FindBy(id="signup_password")
    private var password: WebElement? = null

    @FindBy(id="signup_username")
    private var name: WebElement? = null

    @FindBy(id="signup_forms_submit")
    private var submitBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"signup_form_errors\"]/li")
    private val errorMsg: WebElement? = null

    init {
        if (!driver.currentUrl.contains("register"))
            throw IllegalStateException("This is not the page you are expected")

        PageFactory.initElements(driver, this)
        println("here 2")

        driver.findElement(By.xpath("//*[@id=\"signup_forms_submit\"]")).click()
        println("here 3")
    }

    fun fillInEmail(userEmail: String): RegisterPage{
        println(email)
        println("here 6")
        email!!.sendKeys("")
        email!!.sendKeys(userEmail)

        return this
    }

    fun fillInPassword(userPassword: String): RegisterPage{
        println(password)
        println("here 7")
        password!!.sendKeys("")
        password!!.sendKeys(userPassword)

        return this
    }

    fun fillInName(userName: String): RegisterPage{
        println(name)
        println("here 8")
        name!!.sendKeys("")
        name!!.sendKeys(userName)

        return this
    }

    fun register(): HomePage {
        println(submitBtn)
        println("here 9")
        submitBtn!!.click()

        println("here 10")
        return HomePage(driver)
    }

    fun getError(): WebElement? {
        return errorMsg
    }
}


