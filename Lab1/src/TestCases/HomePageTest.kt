package TestCases

import Pages.HomePage
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class HomePageTest(private val driver: WebDriver, private val homePage: HomePage){

    fun runAllTests(){
        printInfoMsg("\tHOME PAGE tests")

        // currentUserPostTest()
        // otherUserPostTest()
        recommendedUserTest()
        radarPostTest()

        printInfoMsg("\tHOME PAGE tests FINISHED")
    }

    private fun currentUserPostTest(){
        printInfoMsg("\tCURRENT USER POST tests")
        val postTest = PostTest(driver, homePage)
        postTest.runCurrentUserPostTests()
    }

    private fun otherUserPostTest(){
        printInfoMsg("\tOTHER USER POST tests")
        val postTest = PostTest(driver, homePage)
        postTest.runOtherUserPostTests()
    }

    private fun radarPostTest(){
        printInfoMsg("\tRADAR POST tests")
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
    }

    private fun recommendedUserTest() {
        printInfoMsg("\tRECOMMENDED USER tests")

        openRecommendedUserProfileTest()
        exploreRecommendedTest()
    }

    private fun openRecommendedUserProfileTest(){
        try{
            val usersList = homePage.recommendedUserList
            usersList!!.openRecommendedUserProfile(usersList.firstRecommendedUser!!)
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            usersList.openRecommendedUserProfile(usersList.secondRecommendedUser!!)
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            usersList.openRecommendedUserProfile(usersList.thirdRecommendedUser!!)
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            usersList.subscribeToRecommendedUser()
            usersList.closeUserPopover(homePage.recommendationsListHeader,usersList.thirdRecommendedUser!!)

            printSuccessMsg("openRecommendedUserProfileTest")
        }
        catch(e: Exception){
            printErrorMsg("openRecommendedUserProfileTest", e.message)
        }
    }

    private fun exploreRecommendedTest(){
        try{
            homePage.recommendedUserList!!.exploreTumblr()
            if(driver.currentUrl != "https://www.tumblr.com/explore/trending"){
                throw Exception("incorrect url")
            }
            driver.navigate().back()
            printSuccessMsg("exploreRecommendedTest")
        }
        catch(e: Exception){
            printErrorMsg("exploreRecommendedTest", e.message)
        }
    }

}