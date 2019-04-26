package Elements.popups

import Elements.popups.AuthorPopup
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class ThisUserPopup(private val driver: WebDriver): AuthorPopup(driver){

    var headerImage: WebElement? = null
    var editAppearenceBtn: WebElement? = null

    init{}
}