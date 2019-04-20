package TestCases

import Elements.*
import org.openqa.selenium.WebDriver

class HeaderMenuTest(private val driver: WebDriver){

    private val header: Header
    init{
        getToStartPage()
        header = Header(driver)
    }

    private fun getToStartPage(){
        driver.get("http://tumblr.com/dashboard")
    }

    // #3.1
    fun dashboardButtonTest(){
        try{
            header.goToDashboard()
            if(driver.currentUrl != "http://tumblr.com/dashboard")
                throw Exception("wrong page opened")

            println("dashboardButton was SUCCESSFUL")
        }
        catch (e: Exception){
            println("dashboardButtonTest FAILED due to ${e.message}")
        }
    }

    // #3.2
    fun exploreButtonTest(){
        try{
            header.goToExplore()
            if(driver.currentUrl != "http://tumblr.com/explore")
                throw Exception("wrong page opened")

            println("exploreButtonTest was SUCCESSFUL")
        }
        catch (e: Exception){
            println("exploreButtonTest FAILED due to ${e.message}")
        }
    }

    // #3.3
    fun inboxButtonTest(){
        try{
            header.goToInbox()
            if(driver.currentUrl != "http://tumblr.com/inbox")
                throw Exception("wrong page opened")

            println("inboxButtonTest was SUCCESSFUL")
        }
        catch (e: Exception){
            println("inboxButtonTest FAILED due to ${e.message}")
        }
    }

    // #3.4
    fun messageButtonTest(){
        try{
            header.openMessagingPopup()
            header.closePopup()

            println("messageButtonTest was SUCCESSFUL")
        }
        catch (e: Exception){
            println("messageButtonTest FAILED due to ${e.message}")
        }
    }

    // #3.5
    fun activityButtonTest(){
        try{
            header.openActivityPopup()
            header.closePopup()

            println("activityButtonTest was SUCCESSFUL")
        }
        catch (e: Exception){
            println("activityButtonTest FAILED due to ${e.message}")
        }
    }

    // #3.6
    fun accountButtonTest(){
        try{
            header.openAccountPopup()
            header.closePopup()

            println("accountButtonTest was SUCCESSFUL")
        }
        catch (e: Exception){
            println("accountButtonTest FAILED due to ${e.message}")
        }
    }
}