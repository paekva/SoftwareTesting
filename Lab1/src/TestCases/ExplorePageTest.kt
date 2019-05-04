package TestCases

import Pages.ExplorePage
import Utils.printInfoMsg
import org.openqa.selenium.WebDriver

class ExplorePageTest(private val driver: WebDriver, private val explorePage: ExplorePage){

    fun runAllTests(){
        beforeTests()
        filterMenuTest()
        popularBlogsTest()
        popularRequestsTest()
        afterTests()
    }

    private fun filterMenuTest(){}

    private fun popularBlogsTest(){}

    private fun popularRequestsTest(){}

    private fun beforeTests(){
        driver.get("https://www.tumblr.com")
        printInfoMsg("\tEXPLORE PAGE tests")
    }

    private fun afterTests(){
        driver.get("https://www.tumblr.com")
    }
}