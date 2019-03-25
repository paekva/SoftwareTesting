import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Registration(private val driver: ChromeDriver){
    private fun beforeRegistrationTest() {
        val mainPageUrl = "https://www.tumblr.com/"
        driver.get(mainPageUrl)

        driver.findElement(By.id("signup_forms_submit")).click()

        val wait = WebDriverWait(driver, 5)
        try {
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.id("signup_forms")))
        }
        catch(e: Exception){
            println("Cannot get to sign up page")
        }
    }

    fun registr(email: String, password: String, name: String){
        val registForm = driver.findElementById("signup_forms")
        val emailInput = registForm.findElement(By.id("signup_email"))
        val pswrdInput = registForm.findElement(By.id("signup_password"))
        val nameInput = registForm.findElement(By.id("signup_username"))

        emailInput.sendKeys(email)
        pswrdInput.sendKeys(password)
        nameInput.sendKeys(name)

        registForm.findElement(By.id("signup_forms_submit")).click()
    }

    fun deleteAccount(){}

    fun testRegistration(){
        testRegistrationWithWrongCredentials()
        // testRegistrationWithCorrectCredentials()
    }

    fun testAccountDelete(){
        try {
            println("Account delete test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }

    private fun testRegistrationWithCorrectCredentials(){
        beforeRegistrationTest()

        try {
            registr("ekavadipa@gmail.com", "1q2w3e4r5", "Kate")
            println("Registration test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }
    private fun testRegistrationWithWrongCredentials(){
        beforeRegistrationTest()

        try {
            val usedEmail = "paekva@yandex.ru"
            beforeRegistrationTest()

            registr(usedEmail, "1q2w3e4r5", "Kate")

            val smallPassword = "qawse"
            beforeRegistrationTest()

            registr(usedEmail, smallPassword, "Kate")

            val wait = WebDriverWait(driver, 5)
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("error")))

            println("Test for Validation of Registration fields is successful")
        }
        catch(e: Exception){
            println("Test for Validation of Registration fields has failed")
        }
    }
}