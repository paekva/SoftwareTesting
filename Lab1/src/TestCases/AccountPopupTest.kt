package testCases

import elements.CurrentUserPost
import elements.Header
import elements.popups.AccountPopup
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import Utils.waitForURLChange
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
            waitForURLChange(driver, 20,  "https://www.tumblr.com/likes")
            printSuccessMsg("likesListTest")
        }
        catch (e: Exception){
            printErrorMsg("likesListTest", "incorrect url - supposed to be on likes page")
        }
    }

    private fun settingsTest() {
        try{
            popup.openSettings()
            waitForURLChange(driver, 20,  "https://www.tumblr.com/settings/account")
            footerTests()
            printSuccessMsg("settingsTest")
        }
        catch (e: Exception){
            printErrorMsg("settingsTest", "incorrect url - supposed to be on settings page")
        }
    }

    private fun footerTests(){
        val footerTest = FooterMenuTest(driver)
        footerTest.runAllTests()
    }

    private fun referenceTest() {
        try{
            popup.openReference()
            waitForURLChange(driver, 20,  "https://tumblr.zendesk.com/hc/ru")
            driver.navigate().back()
            printSuccessMsg("referenceTest")
        }
        catch (e: Exception){
            printErrorMsg("referenceTest", "incorrect url - supposed to be on reference page")
        }
    }

    private fun usersProfileTest() {
        try{
            popup.openUsersProfile()
            waitForURLChange(driver, 20,  "https://www.tumblr.com/blog/katerinpaivol")
            printSuccessMsg("usersProfileTest")
        }
        catch (e: Exception){
            printErrorMsg("usersProfileTest", "incorrect url - supposed to be on user page")
        }
    }

    private fun postsListTest() {
        try{
            popup.openPostsList()
            waitForURLChange(driver, 20,  "https://www.tumblr.com/blog/katerinpaivol")
            currentUserPostTest()

            printSuccessMsg("postsListTest")
        }
        catch (e: Exception){
            printErrorMsg("postsListTest", "incorrect url - supposed to be on posts page")
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
            waitForURLChange(driver, 20,  "https://www.tumblr.com/blog/katerinpaivol/followers")
            printSuccessMsg("readersListTest")
        }
        catch (e: Exception){
            printErrorMsg("readersListTest", "incorrect url - supposed to be on readers page")
        }
    }

    private fun activityTest() {
        try{
            popup.openActivity()
            waitForURLChange(driver, 20, "https://www.tumblr.com/blog/katerinpaivol/activity")
            printSuccessMsg("activityTest")
        }
        catch (e: Exception){
            printErrorMsg("activityTest", "incorrect url - supposed to be on activity page")
        }
    }

    private fun markedPostsTest() {
        try{
            popup.openMarkedPosts()
            waitForURLChange(driver, 20, "https://www.tumblr.com/blog/katerinpaivol/review")
            printSuccessMsg("markedPostsTest")
        }
        catch (e: Exception){
            printErrorMsg("markedPostsTest", "incorrect url - supposed to be on review page")
        }
    }

    private fun appearanceSettingsTest() {
        try{
            popup.openAppearanceSettings()
            waitForURLChange(driver, 20,"https://www.tumblr.com/settings/blog/katerinpaivol")
            printSuccessMsg("appearanceSettingsTest")
        }
        catch (e: Exception){
            printErrorMsg("appearanceSettingsTest", "wrong page is opened")
        }
    }
}