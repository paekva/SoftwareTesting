package TestCases

import Utils.printInfoMsg
import org.openqa.selenium.WebDriver

class FooterMenuTest(private val driver: WebDriver) {

    fun runAllTests(){
        beforeTests()
        afterTests()
    }

    private fun beforeTests(){
        driver.get("https://www.tumblr.com")
        printInfoMsg("\tFOOTER tests")
    }

    private fun afterTests(){
        driver.get("https://www.tumblr.com")
    }
}