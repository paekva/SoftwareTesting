package TestCases

import Pages.HomePage
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class PostTest(private val driver: WebDriver){
    private var homePage: HomePage = HomePage(driver)

    fun executeAllTests(){
        // testSharePopup()
        // testReblogPopup()
        testReplyPopup()
    }

    // #4.1


    // #4.2
    private fun testSharePopup(){
        try{
            val post = homePage.rebloggedPost
            val shareTests = SharePopupTest(driver)

            post!!.openSharePopup()
            shareTests.testShareByCopyPermLink()
            post.closePopup()

            post.openSharePopup()
            shareTests.testShareByEmbed()
            post.closePopup()

            post.openSharePopup()
            shareTests.testShareByEmail_EmptyInput()
            post.closePopup()

            post.openSharePopup()
            shareTests.testShareByEmail_IncorrectEmail()
            post.closePopup()

            post.openSharePopup()
            shareTests.testShareByEmail_CorrectInput()
            post.closePopup()

            post!!.openSharePopup()
            shareTests.testShareComplain()
            post.closePopup()

            /*post.openSharePopup()
            shareTests.testShareByPermLink()
            post.closePopup()*/

            printSuccessMsg("testSharePopup")
        }
        catch(e:Exception){
            printErrorMsg("testSharePopup",e.message)
        }
    }

    // #4.3
    private fun testReplyPopup(){
        try{
            val post = homePage.rebloggedPost
            val replay = ReplayPopupTest(driver)

            post!!.openReplyPopup()
            replay.testEmptyInput()
            post.closePopup()

            post!!.openReplyPopup()
            replay.testCorrectInput()
            post.closePopup()

            printSuccessMsg("testReplyPopup")
        }
        catch(e:Exception){
            printErrorMsg("testReplyPopup",e.message)
        }
    }

    // #4.4
    private fun testReblogPopup(){
        try{
            homePage.rebloggedPost!!.openReblogPopup()
            printSuccessMsg("testReblogPopup")
        }
        catch(e:Exception){
            printErrorMsg("testReblogPopup",e.message)
        }
    }

    /*
    private fun checkFieldsAndBtnsInSharePopup(sharePopup: SharePopup){
        sharePopup.searchField!!.sendKeys("searching")
    }

    private fun checkFieldsAndBtnsInReplyPopup(replyPopup: ReplyPopup){
        replyPopup.textField!!.sendKeys("hello")
    }

    private fun checkFieldsAndBtnsInReblogPopup(reblogPopup: ReblogPopup){
        val center = driver.findElement(By.className("v-center-outer"))
        val wait = WebDriverWait(driver, 30)

        reblogPopup.rightControl!!.click()
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("pop-menu")))
        center.click()

        reblogPopup.leftControl!!.click()
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("pop-menu")))
        center.click()

        reblogPopup.textField!!.sendKeys("hello")

        reblogPopup.dropdownBtn!!.click()
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("pop-menu")))
        center.click()
    }

    */
}