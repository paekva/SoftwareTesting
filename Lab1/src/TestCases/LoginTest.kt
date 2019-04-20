package TestCases

import Pages.LoginPage
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

    private fun printSuccessMsg(msg: String){
        println("\u001B[32m $msg was SUCCESSFUL \u001B[0m")
    }

    private fun printErrorMsg(msg: String){
        println("\u001B[35m $msg \u001B[0m")
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
            printErrorMsg("loginWithCorrectCredentials FAILED due to ${e.message}")
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
            printErrorMsg("loginWithUnregisteredEmail FAILED due to ${e.message}")
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
            printErrorMsg("loginWithIncorrectFormatOfEmail FAILED due to ${e.message}")
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
            printErrorMsg("loginWithEmptyEmail FAILED due to ${e.message}")
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
            printErrorMsg("loginWithIncorrectPassword FAILED due to ${e.message}")
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
            printErrorMsg("loginWithEmptyPassword FAILED due to ${e.message}")
        }
    }
}