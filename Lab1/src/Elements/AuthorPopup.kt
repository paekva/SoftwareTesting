package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

abstract class AuthorPopup(private val popupElement: WebElement, private val driver: WebDriver) {
    var avatar: WebElement? = null
    var info: WebElement? = null
    var recentPosts: List<WebElement>? = null

    init{
        avatar = popupElement.findElement(By.xpath("//div[1]/div[1]/div[3]"))
        info = popupElement.findElement(By.xpath("//div[1]/div[1]/div[4]"))
        recentPosts = popupElement.findElements(By.xpath("//div/div[2]"))
    }
}
