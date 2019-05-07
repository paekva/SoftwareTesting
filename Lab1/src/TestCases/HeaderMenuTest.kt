package testCases

import elements.*
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class HeaderMenuTest(private val driver: WebDriver){

    private val header: Header = Header(driver)

    fun runAllTests(){
        printInfoMsg("HEADER tests\n")

        createPostButtonTest()
        accountButtonTest()  // OK
        activityButtonTest()
        messageButtonTest()
        inboxButtonTest()
        exploreButtonTest()
        dashboardButtonTest()
        searchFieldTest()
        logoButtonTest()

        printInfoMsg("\nHEADER tests FINISHED")
    }

    // #3.1
    private fun dashboardButtonTest(){
        try{
            val homePage = header.goToHomePage()
            HomePageTest(driver, homePage).runAllTests()
        }
        catch (e: Exception){
            printErrorMsg("dashboardButtonTest", "cannot find dashboard page")
        }
    }

    // #3.2
    private fun exploreButtonTest(){
        try{
            val explorePage = header.goToExplorePage()
            ExplorePageTest(driver, explorePage).runAllTests()
        }
        catch (e: Exception){
            printErrorMsg("exploreButtonTest", "cannot find explore page")
        }
    }

    // #3.3
    private fun inboxButtonTest(){
        try{
            header.goToInboxPage()

            if(driver.currentUrl != "https://www.tumblr.com/inbox")
                throw Exception("wrong page opened")

            printSuccessMsg("inboxButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("inboxButtonTest", e.message)
        }
    }

    // #3.4
    private fun messageButtonTest(){
        try{
            val messagePopup = header.openMessagingPopup()
            MessagingPopupTests(driver, header, messagePopup)
                .runAllTests()
            messagePopup.close()

            dialogPopupTest(driver)
        }
        catch (e: Exception){
            printErrorMsg("messageButtonTest", e.message)
        }
    }

    private fun dialogPopupTest(driver: WebDriver){
        printInfoMsg("DIALOG tests")
        DialogPopupTests(driver).runAllTests()
        printInfoMsg("DIALOG tests FINISHED")
    }

    // #3.5
    private fun activityButtonTest(){
        try{
            val activityPopup = header.openActivityPopup()

            /* TODO: not open as I don't have relevant data in account
            ActivityPopupTest(driver, header, activityPopup)
                .runAllTests()*/

            activityPopup.close()
            printSuccessMsg("activityButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("activityButtonTest", e.message)
        }
    }

    // #3.6
    private fun accountButtonTest(){
        try{
            val accountPopup = header.openAccountPopup()
            AccountPopupTest(driver, header, accountPopup)
                .runAllTests()
            accountPopup.close()
        }
        catch (e: Exception){
            printErrorMsg("accountButtonTest", e.message)
        }
    }

    // #3.7
    private fun createPostButtonTest(){
        try{
            val createPostPopup = header.openCreatePostPopup()

            CreatePostTest(driver, header, createPostPopup)
                .runAllTests()

            createPostPopup.close()
        }
        catch (e: Exception){
            printErrorMsg("createPostButtonTest", e.message)
        }
    }

    // #3.8
    private fun searchFieldTest(){
        try{
            val searchText = "searchText"
            header
                .fillInSearchField(searchText)
                .search()

            if(driver.currentUrl != "https://www.tumblr.com/search/$searchText")
                throw Exception("no search result page")

            printSuccessMsg("searchFieldTest")
        }
        catch (e: Exception){
            printErrorMsg("searchFieldTest", e.message)
        }
    }

    // #3.8
    private fun logoButtonTest(){
        try{
            header.goToHomePageByLogo()

            if(driver.currentUrl != "https://www.tumblr.com/dashboard")
                throw Exception("wrong page opened")

            printSuccessMsg("logoButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("logoButtonTest", e.message)
        }
    }
}