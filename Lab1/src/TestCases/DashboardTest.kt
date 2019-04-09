package TestCases

import Pages.HomePage
import org.openqa.selenium.WebDriver

class DashboardTest(private val driver: WebDriver){
    private val homePage: HomePage

    init{
        val login = LoginTest(driver)
        login.loginWithCorrectCredentials()

        driver.get("http://tumblr.com/dashboard")
        homePage = HomePage(driver)

        println('1')
    }

    fun postTest(){
        val postTest = PostTest(driver, homePage.posts!![0])
        postTest.testAuthorPostInfo()
    }
}