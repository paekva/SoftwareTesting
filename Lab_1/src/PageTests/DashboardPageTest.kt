package PageTests

import org.openqa.selenium.chrome.ChromeDriver


class DashboardPageTest(private val driver: ChromeDriver){

    private fun beforeTest(){
        val dashboadUrl = "https://www.tumblr.com/dashboard"
        driver.get(dashboadUrl)
    }

    fun performTest(){
        beforeTest()
    }
}