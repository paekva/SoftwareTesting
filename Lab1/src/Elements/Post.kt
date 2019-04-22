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

abstract class Post(private val driver: WebDriver){

    @FindBy(xpath="//*[contains(@class,'is_reblog')]/div/div[4]/div[2]/div/div[1]")
    var shareBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'is_reblog')]/div/div[4]/div[2]/div/div[2]")
    var replyBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'is_reblog')]/div/div[4]/div[2]/div/a")
    var reblogBtn: WebElement? = null

    fun openSharePopup(): SharePopup {
        shareBtn!!.click()

        val popup = waitForPopupToAppear("popover--messaging-share-post")

        return SharePopup(driver)
    }

    fun openReplyPopup(): ReplyPopup {
        replyBtn!!.click()

        val popup = waitForPopupToAppear("post-activity-popover")

        return ReplyPopup(popup, driver)
    }

    fun openReblogPopup(): ReblogPopup {
        reblogBtn!!.click()

        val popup = waitForPopupToAppear("post-form")

        return ReblogPopup(popup, driver)
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