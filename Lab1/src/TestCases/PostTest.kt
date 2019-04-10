package TestCases

import Elements.*
import Elements.ReblogPopup
import Elements.ReplyPopup
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class PostTest(private val driver: WebDriver, private val post: UserPost){

    fun testSharePopup(){
        try{
            post.openSharePopup()
            val sharePopup = post.sharePopup

            sharePopup?: throw Exception("Unable to perform test without reply popup")

            checkFieldsAndBtnsInSharePopup(sharePopup)

            println("Share pop up test was successful")
        }
        catch(e:Exception){
            println("Share pop up test has failed: ${e.message}")
        }
    }

    fun testReplyPopup(){
        try{
            post.openReplyPopup()

            val replyPopup = post.replyPopup
            replyPopup?: throw Exception("Unable to perform test without reply popup")

            checkFieldsAndBtnsInReplyPopup(replyPopup)
            println("Reply pop up test was successful")
        }
        catch(e:Exception){
            println("Reply pop up test has failed: ${e.message}")
        }
    }

    fun testReblogPopup(){
        try{
            post.openReblogPopup()

            val reblogPopup = post.reblogPopup
            reblogPopup?: throw Exception("Unable to perform test without reply popup")

            checkFieldsAndBtnsInReblogPopup(reblogPopup)

            reblogPopup!!.closePopup()
            println("Reblog pop up test was successful")
        }
        catch(e:Exception){
            println("Reblog pop up test has failed: ${e.message}")
        }
    }

    fun testSettingsPopup(){
        post.openSettingsPopup()

        val settingsPopup = post.settingsPopup
        settingsPopup?: throw Exception("Unable to perform test without settings popup")

        val center = driver.findElement(By.className("v-center-outer"))

        settingsPopup.changeBtn!!.click()
        settingsPopup.changePopup?: throw Exception("Test settings popup failed")
        settingsPopup.changePopup!!.closePopup()

        center.click()
    }

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
}