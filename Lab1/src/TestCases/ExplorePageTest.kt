package TestCases

import Pages.ExplorePage
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class ExplorePageTest(private val driver: WebDriver, private val explorePage: ExplorePage){

    fun runAllTests(){
        printInfoMsg("\tEXPLORE PAGE tests")

        /*filterMenuTest()
        popularRequestsTest()
        popularBlogsTest()*/
        recommendedPostTest()
    }

    private fun recommendedPostTest(){
        try{
            printInfoMsg("\tRECOMMENDED POST tests")
            val postTest = PostTest(driver, explorePage.recommendedPost!!)
            postTest.runOtherUserPostTests()
            printSuccessMsg("recommendedPostTest")
        }
        catch(e: Exception){
            printErrorMsg("recommendedPostTest", e.message)
        }
    }

    private fun filterMenuTest(){
        printInfoMsg("\tFILTER MENU tests")

        explorePage.openRecommendedBtn()
        checkUrl("recommended-for-you", "recommendedTest")

        explorePage.openChoiceBtn()
        checkUrl("staff-picks", "choiceTest")

        explorePage.openTextBtn()
        checkUrl("text", "textTest")

        explorePage.openPhotoBtn()
        checkUrl("photos", "photoTest")

        explorePage.openGifBtn()
        checkUrl("gifs", "gifTest")

        explorePage.openQuoteBtn()
        checkUrl("quotes", "quoteTest")

        explorePage.openChatBtn()
        checkUrl("chats", "chatTest")

        explorePage.openAudioBtn()
        checkUrl("audio", "audioTest")

        explorePage.openVideoBtn()
        checkUrl("video", "videoTest")

        explorePage.openQuestionBtn()
        checkUrl("asks", "questionTest")

        explorePage.openPopularBtn()
        checkUrl("trending", "popularTest")
    }

    private fun checkUrl(url: String, testName: String){
        if(driver.currentUrl != "https://www.tumblr.com/explore/$url")
            printErrorMsg(testName, "incorrect url")
        else printSuccessMsg(testName)
    }

    private fun popularBlogsTest(){
        try{
            val usersList = explorePage.similarBlogsList
            usersList!!.openRecommendedUserProfile(usersList.firstRecommendedUser!!)
            usersList.closeUserPopover(explorePage.similarBlogsListHeader,usersList.thirdRecommendedUser!!)

            usersList.openRecommendedUserProfile(usersList.secondRecommendedUser!!)
            usersList.closeUserPopover(explorePage.similarBlogsListHeader,usersList.thirdRecommendedUser!!)

            usersList.openRecommendedUserProfile(usersList.thirdRecommendedUser!!)
            usersList.closeUserPopover(explorePage.similarBlogsListHeader,usersList.thirdRecommendedUser!!)

            printSuccessMsg("popularBlogsTest")
        }
        catch(e: Exception){
            printErrorMsg("popularBlogsTest", e.message)
        }
    }

    private fun popularRequestsTest(){
        try{
            val searchPage = explorePage.discoverTagSearch()

            if(searchPage.getSearchResultHeader() != explorePage.discoverTagText())
                throw Exception("supposed to be a search result page")

            driver.navigate().back()

            printSuccessMsg("popularRequestsTest")
        }
        catch(e: Exception){
            printErrorMsg("popularRequestsTest", e.message)
        }
    }
}