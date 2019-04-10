package Pages

import Elements.Header
import Elements.UserPost
import Elements.UserList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor



class HomePage(driver: WebDriver) {

    private var header: Header? = null
    var posts: ArrayList<UserPost> = arrayListOf()
    private var createPostPanel: List<WebElement>? = null
    private val userRecommendations: UserList? = null

    init{
        header = Header(driver)

        val postElement = driver.findElement(By.className("post"))

        val js = driver as JavascriptExecutor
        js.executeScript("arguments[0].scrollIntoView();", postElement)

        posts.add(UserPost(driver, postElement))

    }
}
