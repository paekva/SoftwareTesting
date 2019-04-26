package Elements

import Elements.popups.ReblogPopup
import Elements.popups.ReplyPopup
import Elements.popups.SharePopup
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.JavascriptExecutor



abstract class Post(private val driver: WebDriver){

    @FindBy(xpath="//*[contains(@class,'post')]/div/div[4]/div[2]/div/div[1]")
    var shareBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'post')]/div/div[4]/div[2]/div/div[2]")
    var replyBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'post')]/div/div[4]/div[2]/div/a")
    var reblogBtn: WebElement? = null

    fun openSharePopup(): SharePopup {
        shareBtn!!.click()
        waitForPopupToAppear("popover--messaging-share-post")

        return SharePopup(driver)
    }

    fun openReplyPopup(): ReplyPopup {
        replyBtn!!.click()

        waitForPopupToAppear("post-activity-popover")

        return ReplyPopup(driver)
    }

    fun openReblogPopup(): ReblogPopup {
        reblogBtn!!.click()

        waitForPopupToAppear("post-form")

        return ReblogPopup(driver)
    }

    fun closePopup(){
        val action = Actions(driver)
        action.sendKeys(Keys.ESCAPE).perform()
    }

    private fun waitForPopupToAppear(popover: String): WebElement{
        val wait = WebDriverWait(driver, 30)
        val popup = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className(popover)))
        return popup
    }
}