package elements.popups

import Utils.pressEscKey
import Utils.waitForPresence
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class OtherUserPopup(private val driver: WebDriver){

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[2]/div/ul/li[1]/a")
    var userSettingsAsk: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[2]/div/ul/li[2]/a")
    var userSettingsSendMsg: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[2]/div/ul/li[3]/a")
    var userSettingsArchive: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[2]/div/ul/li[4]/a")
    var userSettingsReport: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[2]/div/ul/li[5]/a")
    var userSettingsBlock: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/a")
    var userSettingsBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/button")
    var followBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/button")
    var userProfile: WebElement? = null

    init{
        PageFactory.initElements(driver, this)

        waitForPresence(driver, 20, "tumblelog_popover")
    }

    fun follow(){
        followBtn!!.click()
    }

    fun askUser(){
        userSettingsBtn!!.click()
        userSettingsAsk!!.click()
    }

    fun sendMsgToUser() : DialogPopup{
        userSettingsBtn!!.click()
        userSettingsSendMsg!!.click()
        return DialogPopup(driver)
    }

    fun isFollowed(): Boolean{
        return followBtn!!.text == "Unfollow"
    }

    fun closePopup(){
        pressEscKey(driver)
    }
}