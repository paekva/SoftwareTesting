package elements

import elements.popups.OtherUserPopup
import pages.ExplorePage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class UsersList(val driver: WebDriver){

    @FindBy(xpath="//*[contains(@class,'follow_list')]/li[2]/a[1]")
    var firstRecommendedUser: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'follow_list')]/li[3]/a[1]")
    var secondRecommendedUser: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'follow_list')]/li[4]/a[1]")
    var thirdRecommendedUser: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'follow_list')]/li[4]/a[2]")
    var thirdRecommendedUserSubscribeBtn: WebElement? = null

    @FindBy(xpath="//a[contains(@class,'explore_link')]")
    var exploreTumblrLink: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun openRecommendedUserProfile(user: WebElement): OtherUserPopup
    {
        val action = Actions(driver)
        action.moveToElement(user).build().perform()

        return OtherUserPopup(driver)
    }

    fun subscribeToRecommendedUser(): OtherUserPopup
    {
        thirdRecommendedUserSubscribeBtn!!.click()
        return OtherUserPopup(driver)
    }

    fun exploreTumblr(): ExplorePage
    {
        exploreTumblrLink!!.click()
        return ExplorePage(driver)
    }

    fun closeUserPopover(to: WebElement?, user: WebElement){
        val action = Actions(driver)
        action.moveToElement(to).build().perform()

        val wait = WebDriverWait(driver, 10)
        wait.until(ExpectedConditions.elementToBeClickable(user))
    }
}