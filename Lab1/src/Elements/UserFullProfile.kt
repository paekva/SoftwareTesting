package Elements

import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

class UserFullProfile(private val driver: WebDriver){
    fun closePopup(){
        driver.navigate().back()
    }
}