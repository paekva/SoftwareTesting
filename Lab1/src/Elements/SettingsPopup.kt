package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
class SettingsPopup(private val popupElement: WebElement, private val driver: WebDriver){

    var changeBtn: WebElement? = null
    var deleteBtn: WebElement? = null

    var changePopup: ReblogPopup? = null

    init{
        changeBtn = popupElement.findElement(By.xpath("//ul/li[1]/a"))
        deleteBtn = popupElement.findElement(By.xpath("//ul/li[2]/a"))
    }
}
