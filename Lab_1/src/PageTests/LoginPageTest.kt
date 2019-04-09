package PageTests

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

class LoginPageTest(private val driver: ChromeDriver){

    private val auth: Login = Login(driver)

    fun testAuthentication(){
        // testWrongCredentials()
        testCorrectCredentials()
    }

    fun testLogout(){
        try {
            val mainPageUrl = "https://www.tumblr.com/login"
            auth.logout()

            if (driver.currentUrl != mainPageUrl)
                throw Exception("Logout test failed")

            println("Logout test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }


    private fun beforeLoginTest() {
        /*val mainPageUrl = "https://www.tumblr.com/"
        driver.get(mainPageUrl)

        driver.findElement(By.id("signup_login_button")).click()*/

        val loginPageUrl = "https://www.tumblr.com/login"
        driver.get(loginPageUrl)
        if ( driver.currentUrl != loginPageUrl )
            throw Exception("Cannot get to login page")
    }

    private fun testCorrectCredentials(){
        try {
            beforeLoginTest()
            auth.login("paekva@yandex.ru", "rfnz98grf")

            val authUserPageUrl = "https://www.tumblr.com/dashboard"
            if (driver.currentUrl != authUserPageUrl)
                throw Exception("PageTests.Login test failed")

            println("PageTests.Login test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }

    private fun testWrongCredentials(){
        try {
            val wait = WebDriverWait(driver, 5)
            val unregistredEmail = "wrongInput@mail.ru"
            val incorrectEmail = "wrongEmailInput"
            val incorrectPassword = "incorrectPassword"

            try{
                beforeLoginTest()
                auth.login(unregistredEmail, "rfnz98grf")
            }
            catch(e: Exception){
                wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("error")))
                println("Unregisted email test was successful")
            }

            try{
                beforeLoginTest()
                auth.login(incorrectEmail, "rfnz98grf")
            }
            catch(e: Exception){
                wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("error")))
                println("incorrectEmail email test was successful")
            }

            /*try{
                beforeLoginTest()
                auth.login("paekva@yandex.ru", incorrectPassword)
            }
            catch(e: Exception){
                // wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("error")))
                println("Incorrect password input test was successful")
            }*/
        }
        catch(e: Exception){
            println("Test for Validation of PageTests.Login fields has failed: ${e.message}")
        }
    }
}