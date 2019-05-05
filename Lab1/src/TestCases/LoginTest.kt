package testCases

import pages.LoginPage
import Utils.*
import org.openqa.selenium.WebDriver

class LoginTest(private val driver: WebDriver){
    private val loginPage: LoginPage
    private val userCredentials: UserCredentialsFactory
    init{
        getToStartPage()

        loginPage = LoginPage(driver)
        userCredentials = UserCredentialsFactory()
    }

    private fun getToStartPage(){
        driver.get("http://tumblr.com/login")
    }

    // #1.1
    fun loginWithCorrectCredentials(){
        try{
            getToStartPage()

            loginPage
                .fillInEmail(userCredentials.getCorrectEmail())
                .fillInPassword(userCredentials.getCorrectPassword())
                .login()

            printSuccessMsg("loginWithCorrectCredentials")
        }
        catch(e: Exception){
            printErrorMsg("loginWithCorrectCredentials", e.message)
        }
    }

    // #1.2
    fun loginWithUnregisteredEmail() {
        try{
            val error = loginPage
                .fillInEmail(userCredentials.getUnregisteredEmail())
                .getError()

            if(error == null)
                throw Exception("no error message displayed")

            printSuccessMsg("loginWithUnregisteredEmail")
        }
        catch (e: Exception){
            printErrorMsg("loginWithUnregisteredEmail", e.message)
        }
    }


    // #1.3
    fun loginWithIncorrectFormatOfEmail(){
        try{
            val error = loginPage
                .fillInEmail(userCredentials.getIncorrectFormatEmail())
                .getError()

            if(error == null)
                throw Exception("loginWithIncorrectFormatOfEmail failed")

            printSuccessMsg("loginWithIncorrectFormatOfEmail")
        }
        catch(e: Exception){
            printErrorMsg("loginWithIncorrectFormatOfEmail", e.message)
        }
    }


    // #1.4
    fun loginWithEmptyEmail() {
        try{
            val error = loginPage
                .fillInEmail(userCredentials.getEmptyString())
                .getError()

            if(error == null)
                throw Exception("loginWithEmptyEmail failed")

            printSuccessMsg("loginWithEmptyEmail")
        }
        catch(e: Exception){
            printErrorMsg("loginWithEmptyEmail", e.message)
        }
    }


    // #1.5
    fun loginWithIncorrectPassword() {
        try{
            getToStartPage()
            val error = loginPage
                .fillInEmail(userCredentials.getCorrectEmail())
                .fillInPassword(userCredentials.getIncorrectPassword())
                .getError()

            if(error == null)
                throw Exception("loginWithIncorrectPassword failed")

            printSuccessMsg("loginWithIncorrectPassword")
        }
        catch (e: Exception){
            printErrorMsg("loginWithIncorrectPassword", e.message)
        }
    }


    // #1.6
    fun loginWithEmptyPassword() {
        try{
            getToStartPage()
            val error = loginPage
                .fillInEmail(userCredentials.getCorrectEmail())
                .fillInPassword(userCredentials.getEmptyString())
                .getError()

            if(error == null)
                throw Exception("loginWithEmptyPassword failed")

            printSuccessMsg("loginWithEmptyPassword")
        }
        catch(e: Exception){
            printErrorMsg("loginWithEmptyPassword", e.message)
        }
    }
}