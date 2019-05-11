package testCases

import pages.HomePage
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import Utils.waitForURLChange
import org.openqa.selenium.WebDriver

class HomePageTest(private val driver: WebDriver, private val homePage: HomePage){

    fun runAllTests(){
        printInfoMsg("HOME PAGE tests")
        recommendedPanelTest()
        radarPostTest()
        printInfoMsg("HOME PAGE tests FINISHED")
    }

    private fun radarPostTest(){
        printInfoMsg("RADAR POST tests")
        try{
            val userPopup = homePage.openRadarPostUserProfile()
            userPopup.closePopup()
            printSuccessMsg("openRadarPostUserProfile")

            val userProfile = homePage.chooseRadarPost()
            userProfile.closePopup()
            printSuccessMsg("chooseRadarPost")
        }
        catch(e: Exception){
            printErrorMsg("radarPostTest", e.message)
        }
        printInfoMsg("RADAR POST tests FINISHED")
    }

    private fun recommendedPanelTest() {
        printInfoMsg("RECOMMENDED USER tests")
        recommendedUserTest()
        exploreRecommendedTest()
        printInfoMsg("RECOMMENDED USER tests FINISHED")
    }

    private fun recommendedUserTest(){
        try{
            val usersList = homePage.recommendedUserList

            usersList!!.openRecommendedUserProfile(usersList.thirdRecommendedUser!!)
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            printSuccessMsg("openRecommendedUserProfileTest")

            usersList.subscribeToRecommendedUser()
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            printSuccessMsg("openSubscribeToRecommendedUserTest")
        }
        catch(e: Exception){
            printErrorMsg("recommendedUserTest", e.message)
        }
    }

    private fun exploreRecommendedTest(){
        try{
            homePage.recommendedUserList!!.exploreTumblr()
            waitForURLChange(driver, 20,"https://www.tumblr.com/explore/trending")
            driver.navigate().back()
            printSuccessMsg("exploreRecommendedTest")
        }
        catch(e: Exception){
            printErrorMsg("exploreRecommendedTest", "incorrect url")
        }
    }

}