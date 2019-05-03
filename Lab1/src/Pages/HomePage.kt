package Pages

import Elements.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement


class HomePage(val driver: WebDriver) {
    var rebloggedPost: RebloggedPost? = RebloggedPost(driver)
    var recommendedPost: UserPost? = UserPost(driver)

    private var createPostPanel: List<WebElement>? = null
    var userRecommendations: UserList? = null

    init{   }
}
