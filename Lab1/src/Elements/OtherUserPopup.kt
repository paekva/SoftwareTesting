package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class OtherUserPopup(private val popupElement: WebElement, private val driver: WebDriver): AuthorPopup(popupElement, driver){

    var userAccountBtn: WebElement? = null
    var followBtn: WebElement? = null

    init{
        userAccountBtn = popupElement.findElement(By.xpath("//div[1]/div[1]/div[1]/div[2]/div/a"))
        followBtn = popupElement.findElement(By.xpath("//div/div[1]/div[1]/div[1]/div[2]/div/button[2]"))
    }
}