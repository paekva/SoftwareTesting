package testCases

import pages.RegisterPage
import Utils.UserCredentialsFactory
import org.openqa.selenium.WebDriver

class RegistTest(private val driver: WebDriver){
    private val registPage: RegisterPage
    private val userCredentials: UserCredentialsFactory

    init{
        getToStartPage()

        println("here 1")
        registPage = RegisterPage(driver)
        userCredentials = UserCredentialsFactory()
    }

    private fun getToStartPage(){
        driver.get("http://tumblr.com/register")
    }

    // #2.1
    fun registWithEmptyFields(): RegisterPage {
        getToStartPage()

        println("here 4")
        registPage
            .fillInPassword(userCredentials.getEmptyString())
            .fillInEmail(userCredentials.getEmptyString())
            .fillInName(userCredentials.getEmptyString())
            .register()

        println("here 5")
        val error = registPage.getError()

        if(error == null)
            throw Exception("registWithEmptyFields failed")

        println("registWithEmptyFields was SUCCESSFUL")
        return registPage
    }
}