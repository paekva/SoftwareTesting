package Elements.popups

import Elements.popups.AuthorPopup
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class OtherUserPopup(private val driver: WebDriver): AuthorPopup(driver){

    var userAccountBtn: WebElement? = null
    var followBtn: WebElement? = null

    init{}
}