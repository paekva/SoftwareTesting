package Elements

import Elements.popups.AuthorPopup
import Elements.popups.OtherUserPopup
import Elements.popups.SettingsPopup
import Elements.popups.ThisUserPopup
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class RebloggedPost(private val driver: WebDriver): Post(driver){

    @FindBy(xpath = "//*[contains(@class,'is_reblog')]/div/div[4]/div[2]/div/div[3]")
    private var settingsBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class,'post_header')]/div/a[2]")
    private var author: WebElement? = null

    @FindBy(xpath = "//*[contains(@class,'post_header')]/div/a[1]")
    private var user: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun openSettingsPopup(): SettingsPopup {
        settingsBtn!!.click()

        return SettingsPopup(driver)
    }

    fun openUserPopup(): ThisUserPopup {
        // user!!.click()
        val action = Actions(driver)
        action.moveToElement(user).build().perform()

        return ThisUserPopup(driver)
    }

    fun openAuthorPopup(): OtherUserPopup {
        // author!!.click()

        val action = Actions(driver)
        action.moveToElement(author).build().perform()

        return OtherUserPopup(driver)
    }

    fun openUserProfile() {
        user!!.click()
    }

    fun openAuthorProfile() {
        author!!.click()
    }
}