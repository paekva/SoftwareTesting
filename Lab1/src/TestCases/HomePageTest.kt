package TestCases

import Pages.HomePage
import Utils.printInfoMsg
import org.openqa.selenium.WebDriver

class HomePageTest(private val driver: WebDriver, private val homePage: HomePage){

    fun runAllTests(){
        printInfoMsg("\tHOME PAGE tests")

        currentUserPostTest()
        otherUserPostTest()
        reblogPostTest()
        radarSpaceTest()
        recommendedAccountsTest()

        printInfoMsg("\tHOME PAGE tests FINISHED")
    }

    private fun currentUserPostTest(){
        printInfoMsg("\tCURRENT USER POST tests")
        val postTest = PostTest(driver, homePage)
        postTest.runCurrentUserPostTests()
    }

    private fun otherUserPostTest(){
        printInfoMsg("\tOTHER USER POST tests")
        val postTest = PostTest(driver, homePage)
        postTest.runOtherUserPostTests()
    }

    private fun reblogPostTest(){
        printInfoMsg("\tREBLOG POST tests")
        val postTest = PostTest(driver, homePage)
        postTest.runReblogPostTests()
    }

    private fun radarSpaceTest(){}

    private fun recommendedAccountsTest(){}
}