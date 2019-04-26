package Pages

import Elements.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement


class HomePage(val driver: WebDriver) {

    var header: Header = Header(driver)
    var rebloggedPost: RebloggedPost? = RebloggedPost(driver)
    var recommendedPost: UserPost? = UserPost(driver)

    private var createPostPanel: List<WebElement>? = null
    var userRecommendations: UserList? = null

    init{   }

    /*fun openUserPopupFromRecommendedList(user: WebElement) : OtherUserPopup{
        val hover = Actions(driver)
        hover.moveToElement(user).build().perform()

        val popup = waitForPopupToAppear("popover")

        return OtherUserPopup(popup, driver)
    }

    private fun waitForPopupToAppear(popover: String): WebElement{
        val wait = WebDriverWait(driver, 30)
        val popup = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className(popover)))
        return popup
    }*/
}
