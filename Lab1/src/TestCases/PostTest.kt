package testCases

import elements.CurrentUserPost
import elements.Post
import elements.UserPost
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class PostTest(private val driver: WebDriver, private val post: Post){

    fun runCurrentUserPostTests(){
        testSharePopup()
        testReplyPopup()
        //testReblogPopup()
        testSettingsChangeBtn(post as CurrentUserPost)
    }

    fun runOtherUserPostTests(){
        testSharePopup()
        testLikeBtn(post as UserPost)
        //testReblogPopup()
    }

    // #4.2
    private fun testSharePopup(){
        try{
            val popup = post.openSharePopup()
            val shareTests = SharePopupTest(driver, post, popup)
            shareTests.runAllTests()

            printSuccessMsg("testSharePopup")
        }
        catch(e:Exception){
            printErrorMsg("testSharePopup",e.message)
        }
    }

    // #4.3
    private fun testReplyPopup(){
        try{
            val replay = ReplayPopupTest(driver)

            post.openReplyPopup()
            replay.testEmptyInput()
            post.closePopup()

            post.openReplyPopup()
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
            val replay = ReblogPopupTest(driver)

            post.openReblogPopup()
            replay.testAuthorLabel()
            replay.testChangeFieldToHTMLFormat()
            replay.testChangeFieldToTextFormat()
            replay.testReblogPublicationSettings()
            // replay.testReblogCancel()
            // replay.testReblogSubmit()
            post.closePopup()

            printSuccessMsg("testReblogPopup")
        }
        catch(e:Exception){
            printErrorMsg("testReblogPopup",e.message)
        }
    }

    // #4.5
    private fun testLikeBtn(post: UserPost){
        try{
            post.likePost()
            if(!post.isPostLiked())
                throw Exception("post should be liked")

            post.likePost()
            if(post.isPostLiked())
                throw Exception("post should not be liked")

            printSuccessMsg("testLikeBtn")
        }
        catch(e:Exception){
            printErrorMsg("testLikeBtn", e.message)
        }
    }

    // #4.6
    private fun testSettingsChangeBtn(post: CurrentUserPost){
        try{
            val settings = post.openSettingsPopup()
            val reblogPopup = settings.openChangePopup()

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

    /*
    // #4.1.1
    private fun testAuthorPopup(){
        try{
            val post = homePage.currentUserPost
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
            val post = homePage.currentUserPost
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
            val post = homePage.currentUserPost
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
            val post = homePage.currentUserPost
            post!!.openUserProfile()
            post.closePopup()

            printSuccessMsg("testUserProfile")
        }
        catch(e:Exception){
            printErrorMsg("testUserProfile",e.message)
        }
    }

    */
}