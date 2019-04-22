package Elements.popups

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class ReplyPopup(private val popupElement: WebElement, private val driver: WebDriver) {
    var header: WebElement? = null
    var notes: WebElement? = null
    var textField: WebElement? = null
    var sendBtn: WebElement? = null

    init{
        header = popupElement.findElement(By.className("popover-inner"))
        notes = popupElement.findElement(By.className("post-activity-notes"))
        textField = popupElement.findElement(By.className("text-input"))
        sendBtn = popupElement.findElement(By.className("submit"))
    }
}
