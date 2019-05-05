package pages

import elements.*
import elements.popups.OtherUserPopup
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory


class HomePage(val driver: WebDriver) : IPage{
    var currentUserPost: CurrentUserPost? = null
    var recommendedUserList: UsersList? = null

    @FindBy(xpath="//*[contains(@class,'recommended_tumblelogs')]/li[1]")
    var recommendationsListHeader: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'radar_footer')]/a[1]")
    var radarPostUserProfile: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'radar_footer')]/a[2]")
    var radarPostUserSubscribeBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'radar_post')]")
    var radarPostContent: WebElement? = null

    init{
        currentUserPost = CurrentUserPost(driver)
        recommendedUserList = UsersList(driver)

        PageFactory.initElements(driver, this)
    }

    fun openRadarPostUserProfile(): OtherUserPopup
    {
        val action = Actions(driver)
        action.moveToElement(radarPostUserProfile).build().perform()

        return OtherUserPopup(driver)
    }

    fun subscribeToRadarPostUser(): OtherUserPopup
    {
        radarPostUserSubscribeBtn!!.click()
        return OtherUserPopup(driver)
    }

    fun chooseRadarPost(): UserFullProfile
    {
        radarPostContent!!.click()
        return UserFullProfile(driver)
    }
}
