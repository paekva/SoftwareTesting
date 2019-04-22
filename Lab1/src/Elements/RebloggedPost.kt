package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.PageFactory

class RebloggedPost(private val driver: WebDriver): Post(driver){

    init{
        PageFactory.initElements(driver, this)
    }

    /*var settingsBtn: WebElement? = null
    var userInfoBtnHeader: WebElement? = null
    var userInfoBtnFooter: WebElement? = null
    var authorInfoBtn: WebElement? = null

    var settingsPopup: SettingsPopup? = null
    var userInfoPopup: ThisUserPopup? = null
    var authorInfoPopup: OtherUserPopup? = null

    init{
        println("in userpost class")
        settingsBtn = postElement.findElement(By.xpath("//div[@title=\"Параметры поста\"]"))
        userInfoBtnHeader = postElement.findElement(By.xpath("//div[2]/div[1]/div/a[1]"))
        userInfoBtnFooter = postElement.findElement(By.xpath("//div[2]/div[2]/div/div[2]/div[1]/a[1]"))
        authorInfoBtn = postElement.findElement(By.xpath("//div[2]/div[1]/div/a[2]"))
    }

    fun openSettingsPopup(){
        settingsBtn!!.click()

        val popup = waitForPopupToAppear("popover")

        println("found one")
        settingsPopup = SettingsPopup(popup, driver)
    }

    fun openReblogInfoHeaderPopup(){
        val hover = Actions(driver)
        hover.moveToElement(userInfoBtnHeader).build().perform()

        println("header overed")
        val popup = waitForPopupToAppear("popover")

        userInfoPopup = ThisUserPopup(popup, driver)
    }

    fun openReblogInfoFooterPopup(){
        val hover = Actions(driver)
        hover.moveToElement(userInfoBtnFooter).build().perform()

        println("footer overed")
        val popup = waitForPopupToAppear("tumblelog_popover")

        userInfoPopup = ThisUserPopup(popup, driver)
    }*/
}