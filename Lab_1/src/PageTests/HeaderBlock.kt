package PageTests

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

class HeaderBlock(driver: ChromeDriver){
    val searchField: WebElement
    /*val tabHome: WebElement
    val tabDiscover: WebElement
    val tabInbox: WebElement
    val tabMessaging: WebElement
    val tabActivity: WebElement
    val tabAccount: WebElement
    val postContentBtn: WebElement */

    init{
        val header = driver.findElement(By.className("l-header"))
        searchField = header.findElement(By.id("search_field"))
        //postContentBtn = header.findElement(By.tagName("button"))

        /*val tabsContainer = header.findElement(By.id("tabs_outer_container"))
        tabHome = tabsContainer.findElement(By.id("home_button"))
        tabDiscover = tabsContainer.findElement(By.id("discover_button"))
        tabInbox = tabsContainer.findElement(By.className("inbox_button"))
        tabMessaging = tabsContainer.findElement(By.className("messaging_button"))
        tabActivity = tabsContainer.findElement(By.className("activity_button"))
        tabAccount = tabsContainer.findElement(By.className("account_button"))
        */
    }

    fun sendSearchRequest(){
        searchField.findElement(By.id("search_form")).submit()
    }
}