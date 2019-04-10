package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class SharePopup(private val popupElement: WebElement, private val driver: WebDriver) {

    var searchField: WebElement? = null
    var sharedList: WebElement? = null
    var externalSources: WebElement? = null

    init{
        searchField = popupElement.findElement(By.xpath("//div/div/div[1]/div/div/input"))
        sharedList = popupElement.findElement(By.className("messaging-share-post-results"))
        externalSources = popupElement.findElement(By.className("messaging-share-post-external-networks-wrapper"))
    }
}
