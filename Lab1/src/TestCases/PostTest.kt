package TestCases

import Pages.HomePage
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class PostTest(private val driver: WebDriver){
    private var homePage: HomePage = HomePage(driver)

    fun executeAllTests(){
        testAuthorPopup()
        testUserPopup()
        testAuthorProfile()
        testUserProfile()
        testSharePopup()
        testReplyPopup()
        testReblogPopup()
        testSettingsChangeBtn()
        testLikeBtn()
    }

    // #4.1.1
    private fun testAuthorPopup(){
        try{
            val post = homePage.rebloggedPost
            post!!.openAuthorPopup()
            post.closePopup()

            printSuccessMsg("testAuthorPopup")
        }
        catch(e:Exception){
            printErrorMsg("testAuthorPopup",e.message)
        }
    }

    // #4.1.2
    private fun testUserPopup(){
        try{
            val post = homePage.rebloggedPost
            post!!.openUserPopup()
            post.closePopup()

            printSuccessMsg("testUserPopup")
        }
        catch(e:Exception){
            printErrorMsg("testUserPopup",e.message)
        }
    }

    // #4.1.3
    private fun testAuthorProfile(){
        try{
            val post = homePage.rebloggedPost
            post!!.openAuthorProfile()
            post.closePopup()

            printSuccessMsg("testAuthorProfile")
        }
        catch(e:Exception){
            printErrorMsg("testAuthorProfile",e.message)
        }
    }

    // #4.1.4
    private fun testUserProfile(){
        try{
            val post = homePage.rebloggedPost
            post!!.openUserProfile()
            post.closePopup()

            printSuccessMsg("testUserProfile")
        }
        catch(e:Exception){
            printErrorMsg("testUserProfile",e.message)
        }
    }

    // #4.2
    private fun testSharePopup(){
        try{
            val post = homePage.rebloggedPost
            val shareTests = SharePopupTest(driver)

            post!!.openSharePopup()
            shareTests.testShareByCopyPermLink()
            post.closePopup()

            post.openSharePopup()
            shareTests.testShareByEmbed()
            post.closePopup()

            post.openSharePopup()
            shareTests.testShareByEmail_EmptyInput()
            shareTests.testShareByEmail_IncorrectEmail()
            shareTests.testShareByEmail_CorrectInput()
            post.closePopup()

            /*post.openSharePopup()
            shareTests.testShareComplain()
            post.closePopup()

            post.openSharePopup()
            shareTests.testShareByPermLink()
            post.closePopup()*/

            printSuccessMsg("testSharePopup")
        }
        catch(e:Exception){
            printErrorMsg("testSharePopup",e.message)
        }
    }

    // #4.3
    private fun testReplyPopup(){
        try{
            val post = homePage.rebloggedPost
            val replay = ReplayPopupTest(driver)

            post!!.openReplyPopup()
            replay.testEmptyInput()
            post.closePopup()

            post!!.openReplyPopup()
            replay.testCorrectInput()
            post.closePopup()

            printSuccessMsg("testReplyPopup")
        }
        catch(e:Exception){
            printErrorMsg("testReplyPopup",e.message)
        }
    }

    // #4.4
    private fun testReblogPopup(){
        try{
            println()
            val post = homePage.rebloggedPost
            val replay = ReblogPopupTest(driver)

            post!!.openReblogPopup()
            replay.testAuthorLabel()
            replay.testChangeFieldToHTMLFormat()
            replay.testChangeFieldToTextFormat()
            replay.testReblogPublicationSettings()
            replay.testReblogCancel()
            // replay.testReblogSubmit()

            printSuccessMsg("testReblogPopup")
            println()
        }
        catch(e:Exception){
            printErrorMsg("testReblogPopup",e.message)
        }
    }

    // #4.4
    private fun testLikeBtn(){
        try{
            val post = homePage.recommendedPost
            println(post)

            post!!.likePost()
            if(!post.isPostLiked())
                throw Exception("post should be liked")

            post!!.likePost()
            if(post.isPostLiked())
                throw Exception("post should not be liked")

            printSuccessMsg("testLikeBtn")
        }
        catch(e:Exception){
            printErrorMsg("testLikeBtn", e.message)
        }
    }

    // #4.4
    private fun testSettingsChangeBtn(){
        try{
            val post = homePage.rebloggedPost
            val settings = post!!.openSettingsPopup()
            val reblogPopup = settings.openChangePopup()

            if(reblogPopup == null)
                throw Exception("no reblog popup after click change btn")
            post.closePopup()

            val settings_delete = post.openSettingsPopup()
            settings_delete.delete()
            post.closePopup()

            printSuccessMsg("testSettingsBtn")
        }
        catch(e:Exception){
            printErrorMsg("testSettingsBtn", e.message)
        }
    }
}