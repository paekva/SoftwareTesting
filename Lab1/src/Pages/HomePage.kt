package Pages

import Elements.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait


class HomePage(val driver: WebDriver) {

    var header: Header = Header(driver)
   //  var myPost: Post = Post(driver)
    var rebloggedPost: RebloggedPost? = RebloggedPost(driver)

    private var createPostPanel: List<WebElement>? = null
    var userRecommendations: UserList? = null

    init{
        /*val userListContainer = driver.findElement(By.className("user_list"))
        userRecommendations = UserList(driver, userListContainer)

        val postElement = driver.findElement(By.className("is_reblog"))

        val js = driver as JavascriptExecutor
        js.executeScript("arguments[0].scrollIntoView();", postElement)

        posts.add(RebloggedPost(driver, postElement))*/
    }

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
