package elements

import elements.popups.*
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class CurrentUserPost(private val driver: WebDriver): Post(driver){

    @FindBy(xpath="//*[contains(@class,'is_mine')]/div[2]/div[3]/div[2]/div/div[1]")
    override var shareBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'is_mine')]/div[2]/div[3]/div[2]/div/div[2]")
    override var replyBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'is_mine')]/div[2]/div[3]/div[2]/div/a")
    override var reblogBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class,'is_mine')]/div[2]/div[3]/div[2]/div/div[3]")
    private var settingsBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class,'post_header')]/div/a[2]")
    private var author: WebElement? = null

    @FindBy(xpath = "//*[contains(@class,'post_header')]/div/a[1]")
    private var user: WebElement? = null

    init{ PageFactory.initElements(driver, this) }

    fun openSettingsPopup(): SettingsPopup {
        settingsBtn!!.click()

        return SettingsPopup(driver)
    }

    fun openUserPopup(): CurrentUserPopup {
        val action = Actions(driver)
        action.moveToElement(user).build().perform()

        return CurrentUserPopup(driver)
    }

    fun openAuthorPopup(): OtherUserPopup {
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