package elements

import elements.popups.*
import Utils.pressEscKey
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

abstract class Post(private val driver: WebDriver){
    abstract var shareBtn: WebElement?
    abstract var replyBtn: WebElement?
    abstract var reblogBtn: WebElement?
    abstract var author: WebElement?

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

    fun openAuthorPopup(): OtherUserPopup {
        val action = Actions(driver)
        action.moveToElement(author).build().perform()
        return OtherUserPopup(driver)
    }

    fun openAuthorProfile() : UserFullProfile {
        author!!.click()
        return UserFullProfile(driver)
    }


    fun closePopup(){
        pressEscKey(driver)
    }

    private fun waitForPopupToAppear(popover: String): WebElement{

        val wait = WebDriverWait(driver, 30)
        val popup = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className(popover)))
        return popup
    }
}