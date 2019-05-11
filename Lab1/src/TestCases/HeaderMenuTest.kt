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

        activityButtonTest()
        messageButtonTest()
        inboxButtonTest()
        exploreButtonTest()
        dashboardButtonTest()
        searchFieldTest()
        logoButtonTest()
        createPostButtonTest()
        accountButtonTest()

        printInfoMsg("\nHEADER tests FINISHED")
    }

    private fun dashboardButtonTest(){
        try{
            val homePage = header.goToHomePage()
            HomePageTest(driver, homePage).runAllTests()
        }
        catch (e: Exception){
            printErrorMsg("dashboardButtonTest", "cannot find dashboard page")
        }
    }

    private fun exploreButtonTest(){
        try{
            val explorePage = header.goToExplorePage()
            ExplorePageTest(driver, explorePage).runAllTests()
        }
        catch (e: Exception){
            printErrorMsg("exploreButtonTest", "cannot find explore page")
        }
    }

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
        DialogPopupTests(driver).runAllTests()
    }

    private fun activityButtonTest(){
        try{
            val activityPopup = header.openActivityPopup()

            ActivityPopupTest(driver, header, activityPopup)
                .runAllTests()

            activityPopup.close()
            printSuccessMsg("activityButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("activityButtonTest", e.message)
        }
    }

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