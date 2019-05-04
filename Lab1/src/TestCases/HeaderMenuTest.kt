package TestCases

import Elements.*
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class HeaderMenuTest(private val driver: WebDriver){

    private val header: Header = Header(driver)

    fun runAllTests(){
        beforeTests()

        /*createPostButtonTest()
        accountButtonTest()
        activityButtonTest() //TODO: check
        messageButtonTest()
        inboxButtonTest()
        exploreButtonTest()*/
        dashboardButtonTest()
        // searchFieldTest()
        // logoButtonTest()

        afterTests()
    }

    private fun beforeTests(){
        driver.get("https://www.tumblr.com")
        printInfoMsg("\tHEADER tests")
    }

    private fun afterTests(){
        driver.get("https://www.tumblr.com")
    }

    // #3.1
    private fun dashboardButtonTest(){
        try{
            val homePage = header.goToDashboard()
            val homePageTest = HomePageTest(driver, homePage)
            homePageTest.runAllTests()

            printSuccessMsg("dashboardButton")
        }
        catch (e: Exception){
            printErrorMsg("dashboardButtonTest", "cannot find dashboard page")
        }
    }

    // #3.2
    private fun exploreButtonTest(){
        try{
            val explorePage = header.goToExplore()
            val explorePageTest = ExplorePageTest(driver, explorePage)
            explorePageTest.runAllTests()

            printSuccessMsg("exploreButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("exploreButtonTest", "cannot find explore page")
        }
    }

    // #3.3
    private fun inboxButtonTest(){
        try{
            header.goToInbox()
            printSuccessMsg("inboxButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("inboxButtonTest", "cannot find inbox page")
        }
    }

    // #3.4
    private fun messageButtonTest(){
        try{
            val messagePopup = header.openMessagingPopup()
            val messagesTest = MessagingPopupTests(driver, header, messagePopup)
            messagesTest.runAllTests()
            header.closeMessagingPopup()

            printSuccessMsg("messageButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("messageButtonTest", e.message)
        }
    }

    // #3.5
    private fun activityButtonTest(){
        try{
            val activityPopup = header.openActivityPopup()
            val accountPopupTest = ActivityPopupTest(driver, header, activityPopup)
            accountPopupTest.runAllTests()
            header.closeActivityPopup()

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
            val accountPopupTest = AccountPopupTest(driver, header, accountPopup)
            accountPopupTest.runAllTests()

            header.closeAccountPopup()

            printSuccessMsg("accountButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("accountButtonTest", e.message)
        }
    }

    // #3.7
    private fun createPostButtonTest(){
        try{
            val createPostPopup = header.openCreatePostPopup()
            val createPost = CreatePostTest(driver, header, createPostPopup)
            createPost.runAllTests()
            header.closeCreatePostPopup()

            printSuccessMsg("createPostButtonTest")
        }
        catch (e: Exception){
            printErrorMsg("createPostButtonTest", e.message)
        }
    }

    // #3.8
    private fun searchFieldTest(){
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
    private fun logoButtonTest(){
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