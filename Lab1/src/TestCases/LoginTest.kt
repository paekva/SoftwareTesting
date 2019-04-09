package TestCases

import Pages.HomePage
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

    fun loginWithCorrectCredentials(): HomePage{
        val user = userCredentials.getCorrectCredentials()
        loginPage.login(user)

        if(!driver.currentUrl.contains("dashboard"))
            throw Exception("TEST: Login with correct credentials - FAILED")

        return HomePage(driver)
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