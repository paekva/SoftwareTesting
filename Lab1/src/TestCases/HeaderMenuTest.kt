package TestCases

import Elements.*
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class HeaderMenuTest(private val driver: WebDriver){

    private val header: Header = Header(driver)

    // #3.1
    fun dashboardButtonTest(){
        try{
            header.goToDashboard()
            printSuccessMsg("dashboardButton")
        }
        catch (e: Exception){
            printErrorMsg("dashboardButtonTest", "cannot find dashboard page")
        }
    }

    // #3.2
    fun exploreButtonTest(){
        try{
            header.goToExplore()
            printSuccessMsg("exploreButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("exploreButtonTest", "cannot find explore page")
        }
    }

    // #3.3
    fun inboxButtonTest(){
        try{
            header.goToInbox()
            printSuccessMsg("inboxButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("inboxButtonTest", "cannot find inbox page")
        }
    }

    // #3.4
    fun messageButtonTest(){
        try{
            header.openMessagingPopup()
            header.closeMessagingPopup()

            printSuccessMsg("messageButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("messageButtonTest", e.message)
        }
    }

    // #3.5
    fun activityButtonTest(){
        try{
            header.openActivityPopup()
            header.closeActivityPopup()

            printSuccessMsg("activityButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("activityButtonTest", e.message)
        }
    }

    // #3.6
    fun accountButtonTest(){
        try{
            header.openAccountPopup()
            header.closeAccountPopup()

            printSuccessMsg("accountButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("accountButtonTest", e.message)
        }
    }

    // #3.7
    fun createPostButtonTest(){
        try{
            header.openCreatePostPopup()
            header.closeCreatePostPopup()

            printSuccessMsg("createPostButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("createPostButtonTest", e.message)
        }
    }

    // #3.8
    fun searchFieldTest(){
        try{
            val searchText = "searchText"
            val resultPopup = header.fillInSearchField(searchText)

            if(resultPopup == null)
                throw Exception("no search popup")

            header.search()

            if(driver.currentUrl != "https://www.tumblr.com/search/$searchText")
                throw Exception("no search result page")

            printSuccessMsg("searchFieldTest")
        }
        catch (e: Exception){
            printErrorMsg("searchFieldTest", e.message)
        }
    }

    // #3.8
    fun logoButtonTest(){
        try{
            header.goToDashboardByLogo()

            if(driver.currentUrl != "https://www.tumblr.com/dashboard")
                throw Exception("wrong page opened")

            printSuccessMsg("logoButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("logoButtonTest", e.message)
        }
    }
}