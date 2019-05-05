package elements.popups

import elements.UserFullProfile
import Utils.waitForPresence
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class CurrentUserPopup(private val driver: WebDriver){

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/button")
    var editAppearence: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/button")
    var userProfile: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
        waitForPresence(driver, 20, "tumblelog_popover")
    }

    fun editAppearence(): UserFullProfile{
        editAppearence!!.click()
        return UserFullProfile(driver)
    }

    fun openUserProfile(): UserFullProfile{
        userProfile!!.click()
        return UserFullProfile(driver)
    }
}