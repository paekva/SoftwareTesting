package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class UserPost(private val driver: WebDriver, private val postElement: WebElement): Post(driver, postElement){

    var settingsBtn: WebElement? = null
    var reblogInfoBtnHeader: WebElement? = null
    var reblogInfoBtnFooter: WebElement? = null
    var userInfoBtn: WebElement? = null

    var settingsPopup: SettingsPopup? = null
    var reblogInfoPopup: AuthorPopup? = null
    var userInfoPopup: AuthorPopup? = null

    init{
        println("in userpost class")
        settingsBtn = postElement.findElement(By.xpath("//div[@title=\"Параметры поста\"]"))
        reblogInfoBtnHeader = postElement.findElement(By.xpath("//div[2]/div[1]/div/a[1]"))
        reblogInfoBtnFooter = postElement.findElement(By.xpath("//div[2]/div[2]/div/div[2]/div[1]/a[1]"))
        userInfoBtn = postElement.findElement(By.xpath("//div[2]/div[1]/div/a[2]"))
    }

    fun openSettingsPopup(){
        settingsBtn!!.click()

        val popup = waitForPopupToAppear("popover")

        settingsPopup = SettingsPopup(popup, driver)
    }
}