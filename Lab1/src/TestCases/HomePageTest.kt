package TestCases

import Pages.HomePage
import Pages.LoginPage
import org.openqa.selenium.WebDriver

class HomePageTest(private val driver: WebDriver){
    private var homePage: HomePage? = null

    init{
        beforeTest()

        println("finish login")
    }

    private fun beforeTest(){
        val login = LoginPage(driver)
        homePage = login.fillInEmail("").fillInPassword("").login()

        getToStartPage()
    }

    private fun getToStartPage(){
        driver.get("http://tumblr.com/dashboard")
    }


    /*fun postTest(){
        val postTest = PostTest(driver, homePage.posts[0])

        postTest.testSharePopup()
        postTest.testReplyPopup()
        postTest.testReblogPopup()
        // postTest.testSettingsPopup()
        postTest.testUserPopup()
        testRecommendedUserList()
    }

    fun testRecommendedUserList(){
        try{
            val result = homePage.openUserPopupFromRecommendedList(homePage.userRecommendations!!.list[0].url)
            println(result)
            println("other user popup END")
        }
        catch(e: Exception){
            println()
        }
    }*/
}