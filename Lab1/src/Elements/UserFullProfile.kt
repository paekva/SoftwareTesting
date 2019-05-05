package elements

import org.openqa.selenium.WebDriver

class UserFullProfile(private val driver: WebDriver){
    fun closePopup(){
        driver.navigate().back()
    }
}