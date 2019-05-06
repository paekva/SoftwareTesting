package testCases

import elements.Header
import elements.popups.ActivityPopup
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class ActivityPopupTest(private val driver: WebDriver, private val header: Header, private val popup: ActivityPopup) {
    fun runAllTests(){
        printInfoMsg("ACTIVITY POPUP tests")
        allResultsTest()
        oneResultTest()
    }

    private fun allResultsTest() {
        try {
            popup.seeAllResults()

            if(driver.currentUrl != "https://www.tumblr.com/blog/katerinpaivol/activity")
                throw Exception("supposed to be on activity page")

            waitForCloseOfPopovers()
            header.openActivityPopup()
            printSuccessMsg("allResultsTest")
        }
        catch(e: Exception){
            printErrorMsg("allResultsTest", e.message)
        }
    }

    private fun oneResultTest(){
        try{
            val userPopup = popup.seeOneResult()
            userPopup.closePopup()

            waitForCloseOfPopovers()
            header.openActivityPopup()
            printSuccessMsg("oneResultTest")
        }
        catch(e: Exception){
            printErrorMsg("oneResultTest", e.message)
        }
    }

    private fun waitForCloseOfPopovers(){
        val wait = WebDriverWait(driver, 60)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"activity_button\"]/a")))
    }
}