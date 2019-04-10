package TestCases

import Pages.HomePage
import org.openqa.selenium.WebDriver

class DashboardTest(private val driver: WebDriver){
    private val homePage: HomePage

    init{
        val login = LoginTest(driver)
        homePage = login.loginWithCorrectCredentials()
        println("finish login")
    }

    fun postTest(){
        val postTest = PostTest(driver, homePage.posts[0])

        postTest.testSharePopup()
        println("in the start")
        postTest.testReplyPopup()
        println("in the middle")
        // postTest.testReblogPopup()
        println("settings started")
        postTest.testSettingsPopup()
    }
}