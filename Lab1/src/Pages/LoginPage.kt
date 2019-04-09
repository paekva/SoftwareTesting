package Pages

import Utils.*
import org.openqa.selenium.WebDriver

class LoginPage(private val driver: WebDriver){


    fun loginSuccess(user: UserCredentials): HomePage {
        login(user)
        return HomePage(driver)
    }

    fun loginFail(user: UserCredentials): LoginPage {
        login(user)
        return LoginPage(driver)
    }

    private fun login(user: UserCredentials): Any {

    }

}

