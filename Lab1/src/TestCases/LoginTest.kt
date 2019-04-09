package TestCases

import Pages.LoginPage
import Utils.*
import org.openqa.selenium.WebDriver

class LoginTest(private val driver: WebDriver){
    private val loginPage: LoginPage
    private val userCredentials: UserCredentialsFactory

    init{
        driver.get("http://tumblr.com/login")

        loginPage = LoginPage(driver)
        userCredentials = UserCredentialsFactory()
    }

    fun loginWithCorrectCredentials(){
        val user = userCredentials.getCorrectCredentials()
        val result = loginPage.loginSuccess(user)

        /*if(!result.getUrl().contains("dashboard"))
            throw Exception("TEST: Login with correct credentials - FAILED")*/
    }

    /*fun loginWithIncorrectEmailCredentials(){
        var user = userCredentials.getWrongEmailCredentials()
        var result = loginPage.loginFail(user)

        if(!result.getUrl().contains("login") && result.error != null)
            throw Exception("TEST: Login with incorrect credentials - FAILED")
    }

    fun loginWithIncorrectPasswordCredentials(){
        var user = userCredentials.getWrongPasswordCredentials()
        var result = loginPage.loginFail(user)

        if(!result.getUrl().contains("login") && result.error != null)
            throw Exception("TEST: Login with incorrect credentials - FAILED")
    }*/
}