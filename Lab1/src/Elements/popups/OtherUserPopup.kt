package Elements.popups

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class OtherUserPopup(private val driver: WebDriver): AuthorPopup(driver){

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/a")
    var userAccountBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'tumblelog_popover')]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/button")
    var followBtn: WebElement? = null

    init{
        PageFactory.initElements(driver, this)

        val wait = WebDriverWait(driver, 20)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'tumblelog_popover')]")))
    }

    fun closePopup(){
        val action = Actions(driver)
        action.sendKeys(Keys.ESCAPE).perform()
    }
}