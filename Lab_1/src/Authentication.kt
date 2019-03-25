import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Authentication(private val driver: ChromeDriver){

    private fun beforeLoginTest() {
        val mainPageUrl = "https://www.tumblr.com/"
        driver.get(mainPageUrl)

        driver.findElement(By.id("signup_login_button")).click()

        val loginPageUrl = "https://www.tumblr.com/login"
        if ( driver.currentUrl != loginPageUrl )
            throw Exception("Cannot get to login page")
    }

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
        val okBtn = okBtnText.findElement(By.xpath(".."))
        okBtn.click()
    }

    fun testAuthentication(){
        testCorrectCredentials()
        logout()
        testWrongCredentials()
    }

    fun testLogout(){
        try {
            val mainPageUrl = "https://www.tumblr.com/login"
            logout()

            if (driver.currentUrl != mainPageUrl)
                throw Exception("Logout test failed")

            println("Logout test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }

    private fun testCorrectCredentials(){
        beforeLoginTest()

        try {
            login("paekva@yandex.ru", "rfnz98grf")

            val authUserPageUrl = "https://www.tumblr.com/dashboard"
            if (driver.currentUrl != authUserPageUrl)
                throw Exception("Login test failed")

            println("Login test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }

    private fun testWrongCredentials(){
        try {
            val wait = WebDriverWait(driver, 5)

            try {
                val unregistredEmail = "wrong@Email.Input"
                beforeLoginTest()

                login(unregistredEmail, "rfnz98grf")
            }
            catch (e: Exception){
                wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("error")))
            }

            try {
                val incorrectEmail = "wrongEmailInput"
                beforeLoginTest()

                login(incorrectEmail, "rfnz98grf")
            }
            catch (e: Exception){
                wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("error")))
            }

            val incorrectPassword = "incorrectPassword"
            beforeLoginTest()

            login("paekva@yandex.ru", incorrectPassword)

            if(driver.currentUrl != "https://www.tumblr.com/login")
                throw Exception()

            // wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("error")))

        }
        catch(e: Exception){
            println("Test for Validation of Login fields has failed")
        }
    }
}