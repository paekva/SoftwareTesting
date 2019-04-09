package Pages

import Elements.Header
import Elements.Post
import Elements.UserList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class HomePage(driver: WebDriver) {

    private var header: Header? = null
    var posts: ArrayList<Post> = arrayListOf()
    private var createPostPanel: List<WebElement>? = null
    private val userRecommendations: UserList? = null

    init{
        header = Header(driver)

        val postElements = driver.findElements(By.className("post"))
        println('3')
        postElements.forEach {
            posts.add(Post(driver, it))
        }
    }
}
