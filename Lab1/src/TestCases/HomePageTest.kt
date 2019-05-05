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
        recommendedUserTest()
        radarPostTest()
        printInfoMsg("HOME PAGE tests FINISHED")
    }

    private fun radarPostTest(){
        printInfoMsg("\tRADAR POST tests")
        try{
            val userPopup = homePage.openRadarPostUserProfile()
            userPopup.closePopup()
            printSuccessMsg("\topenRadarPostUserProfile")

            val userProfile = homePage.chooseRadarPost()
            userProfile.closePopup()
            printSuccessMsg("\tchooseRadarPost")
        }
        catch(e: Exception){
            printErrorMsg("radarPostTest", e.message)
        }
        printInfoMsg("\tRADAR POST tests FINISHED")
    }

    private fun recommendedUserTest() {
        printInfoMsg("\tRECOMMENDED USER tests")
        openRecommendedUserProfileTest()
        exploreRecommendedTest()
        printInfoMsg("\tRECOMMENDED USER tests FINISHED")
    }

    private fun openRecommendedUserProfileTest(){
        try{
            val usersList = homePage.recommendedUserList

            /*usersList!!.openRecommendedUserProfile(usersList.firstRecommendedUser!!)
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            usersList.openRecommendedUserProfile(usersList.secondRecommendedUser!!)
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)*/

            usersList!!.openRecommendedUserProfile(usersList.thirdRecommendedUser!!)
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            usersList.subscribeToRecommendedUser()
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            printSuccessMsg("\topenRecommendedUserProfileTest")
        }
        catch(e: Exception){
            printErrorMsg("openRecommendedUserProfileTest", e.message)
        }
    }

    private fun exploreRecommendedTest(){
        try{
            homePage.recommendedUserList!!.exploreTumblr()
            waitForURLChange(driver, 10,"https://www.tumblr.com/explore/trending")
            driver.navigate().back()
            printSuccessMsg("\texploreRecommendedTest")
        }
        catch(e: Exception){
            printErrorMsg("exploreRecommendedTest", "incorrect url")
        }
    }

}