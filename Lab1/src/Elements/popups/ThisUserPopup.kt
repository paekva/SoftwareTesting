package Elements.popups

import Elements.popups.AuthorPopup
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class ThisUserPopup(private val popupElement: WebElement, private val driver: WebDriver): AuthorPopup(popupElement, driver){

    var headerImage: WebElement? = null
    var editAppearenceBtn: WebElement? = null

    init{
        headerImage = popupElement.findElement(By.xpath("//div/div[1]/div[1]/div[1]/div[1]"))
        // editAppearenceBtn = popupElement.findElement(By.xpath("//div/div[1]/div[1]/div[1]/div[2]/div/button"))
    }
}