import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Authentication(private val email: String, private val password: String, private val driver: ChromeDriver){

    private fun beforeLoginTest() {
        driver.findElement(By.id("signup_login_button")).click()

        val loginPageUrl = "https://www.tumblr.com/login"

        if ( driver.currentUrl != loginPageUrl )
            throw Exception("Cannot get to login page")
    }

    public fun login(){

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

    public fun logout(){
        val accountBtn = driver.findElement(By.xpath("//button[@title='Учетная запись']"))
        accountBtn.click()

        val wait = WebDriverWait(driver, 5)
        val logoutBtn = wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.id("logout_button")))
        logoutBtn.click()

        val okBtnText = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='ОК']")))
        val okBtn = okBtnText.findElement(By.xpath(".."))
        okBtn.click()
    }

    fun testLogin(){
        beforeLoginTest()

        login()

        val authUserPageUrl = "https://www.tumblr.com/dashboard"
        if(driver.currentUrl != authUserPageUrl)
            throw Exception("Login test failed")

        println("Login test complited successfully")
    }

    fun testLogout(){
        logout()

        val mainPageUrl = "https://www.tumblr.com/login"
        if(driver.currentUrl != mainPageUrl)
            throw Exception("Logout test failed")

        println("Logout test complited successfully")
    }
}