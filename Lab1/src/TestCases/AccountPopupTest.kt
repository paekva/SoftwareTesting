package testCases

import elements.CurrentUserPost
import elements.Header
import elements.popups.AccountPopup
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class AccountPopupTest(private val driver: WebDriver, private val header: Header, private val popup: AccountPopup){

    fun runAllTests(){
        printInfoMsg("ACCOUNT POPUP TEST")
        likesListTest()

        header.openAccountPopup()
        settingsTest()

        header.openAccountPopup()
        referenceTest()

        header.openAccountPopup()
        usersProfileTest()

        header.openAccountPopup()
        postsListTest()

        header.openAccountPopup()
        readersListTest()

        header.openAccountPopup()
        activityTest()

        header.openAccountPopup()
        markedPostsTest()

        header.openAccountPopup()
        appearanceSettingsTest()
    }

    private fun likesListTest() {
        try{
            popup.openLikesList()

            if(driver.currentUrl != "https://www.tumblr.com/likes")
                throw Exception("incorrect url - supposed to be on likes page")
            
            printSuccessMsg("likesListTest")
        }
        catch (e: Exception){
            printErrorMsg("likesListTest", e.message)
        }
    }

    private fun settingsTest() {
        try{
            popup.openSettings()

            if(driver.currentUrl != "https://www.tumblr.com/settings/account")
                throw Exception("incorrect url - supposed to be on settings page")

            printSuccessMsg("settingsTest")
        }
        catch (e: Exception){
            printErrorMsg("settingsTest", e.message)
        }
    }

    private fun referenceTest() {
        try{
            popup.openReference()

            if(driver.currentUrl != "https://tumblr.zendesk.com/hc/ru")
                throw Exception("incorrect url - supposed to be on reference page")

            driver.get("https://www.tumblr.com/dashboard")
            printSuccessMsg("referenceTest")
        }
        catch (e: Exception){
            printErrorMsg("referenceTest", e.message)
        }
    }

    private fun usersProfileTest() {
        try{
            popup.openUsersProfile()
            if(driver.currentUrl != "https://www.tumblr.com/blog/katerinpaivol")
                throw Exception("incorrect url - supposed to be on user page")

            printSuccessMsg("usersProfileTest")
        }
        catch (e: Exception){
            printErrorMsg("usersProfileTest", e.message)
        }
    }

    private fun postsListTest() {
        try{
            popup.openPostsList()

            if(driver.currentUrl != "https://www.tumblr.com/blog/katerinpaivol")
                throw Exception("incorrect url - supposed to be on posts page")

            currentUserPostTest()

            printSuccessMsg("postsListTest")
        }
        catch (e: Exception){
            printErrorMsg("postsListTest", e.message)
        }
    }

    private fun currentUserPostTest(){
        printInfoMsg("\tCURRENT USER POST tests")

        val postTest = PostTest(driver, CurrentUserPost(driver))
        postTest.runCurrentUserPostTests()
    }

    private fun readersListTest() {
        try{
            popup.openReadersList()

            if(driver.currentUrl != "https://www.tumblr.com/blog/katerinpaivol/followers")
                throw Exception("incorrect url - supposed to be on readers page")

            printSuccessMsg("readersListTest")
        }
        catch (e: Exception){
            printErrorMsg("readersListTest", e.message)
        }
    }

    private fun activityTest() {
        try{
            popup.openActivity()

            if(driver.currentUrl != "https://www.tumblr.com/blog/katerinpaivol/activity")
                throw Exception("incorrect url - supposed to be on activity page")

            printSuccessMsg("activityTest")
        }
        catch (e: Exception){
            printErrorMsg("activityTest", e.message)
        }
    }

    private fun markedPostsTest() {
        try{
            popup.openMarkedPosts()

            if(driver.currentUrl != "https://www.tumblr.com/blog/katerinpaivol/review")
                throw Exception("incorrect url - supposed to be on review page")

            printSuccessMsg("markedPostsTest")
        }
        catch (e: Exception){
            printErrorMsg("markedPostsTest", e.message)
        }
    }

    private fun appearanceSettingsTest() {
        try{
            popup.openAppearanceSettings()

            if(driver.currentUrl != "https://www.tumblr.com/settings/blog/katerinpaivol")
                throw Exception("incorrect url - supposed to be on appearance settings page")

            printSuccessMsg("appearanceSettingsTest")
        }
        catch (e: Exception){
            printErrorMsg("appearanceSettingsTest", e.message)
        }
    }
}