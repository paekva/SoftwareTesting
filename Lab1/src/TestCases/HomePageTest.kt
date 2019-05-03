package TestCases

import Pages.HomePage
import Utils.printInfoMsg
import org.openqa.selenium.WebDriver

class HomePageTest(private val driver: WebDriver, private val homePage: HomePage){

    fun runAllTests(){
        beforeTests()
        currentUserPostTest()
        otherUserPostTest()
        reblogPostTest()
        radarSpaceTest()
        recommendedAccountsTest()
        afterTests()
    }

    private fun currentUserPostTest(){
        printInfoMsg("\tCURRENT USER POST tests")
        val postTest = PostTest(driver)
        postTest.executeAllTests()
    }

    private fun otherUserPostTest(){
        printInfoMsg("\tOTHER USER POST tests")
        val postTest = PostTest(driver)
        postTest.executeAllTests()
    }

    private fun reblogPostTest(){
        printInfoMsg("\tREBLOG POST tests")
        val postTest = PostTest(driver)
        postTest.executeAllTests()
    }

    private fun radarSpaceTest(){}

    private fun recommendedAccountsTest(){}

    private fun beforeTests(){
        driver.get("https://www.tumblr.com")
        printInfoMsg("\tHOME PAGE tests")
    }

    private fun afterTests(){
        driver.get("https://www.tumblr.com")
    }
}