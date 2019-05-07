package testCases

import elements.CurrentUserPost
import elements.Post
import elements.UserPost
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class PostTest(private val driver: WebDriver, private val post: Post){

    fun runCurrentUserPostTests(){
        printInfoMsg("\nCURRENT USER POST tests")
        testSharePopup()
        testReplyPopup()
        testSettingsChangeBtn(post as CurrentUserPost)
        //testReblogPopup()

        printInfoMsg("\nCURRENT USER POST tests FINISHED")
    }

    fun runOtherUserPostTests(){
        testSharePopup()
        testLikeBtn(post as UserPost)
        testAuthorPopup()
        testAuthorProfile()
        //testReblogPopup()
    }

    // #4.2
    private fun testSharePopup(){
        try{
            printInfoMsg("\nPOST test: SHARE POPUP test")

            val popup = post.openSharePopup()
            SharePopupTest(driver, post, popup)
                .runAllTests()

            printInfoMsg("\nPOST test: SHARE POPUP test FINISHED")
        }
        catch(e:Exception){
            printErrorMsg("testSharePopup",e.message)
        }
    }

    // #4.3
    private fun testReplyPopup(){
        try{
            printInfoMsg("\nPOST test: REPLY POPUP test")

            val popup = post.openReplyPopup()
            ReplayPopupTest(driver, post, popup)
                .runAllTests()

            post.closePopup()
            printInfoMsg("\nPOST test: REPLY POPUP test FINISHED")
        }
        catch(e:Exception){
            printErrorMsg("testReplyPopup",e.message)
        }
    }

    // #4.4
    private fun testReblogPopup(){
        try{
            printInfoMsg("\nPOST test: REBLOG POPUP test")
            val replay = ReblogPopupTest(driver)

            post.openReblogPopup()
            replay.testAuthorLabel()
            replay.testChangeFieldToHTMLFormat()
            replay.testChangeFieldToTextFormat()
            replay.testReblogPublicationSettings()
            // replay.testReblogCancel()
            // replay.testReblogSubmit()
            post.closePopup()

            printInfoMsg("\nPOST test: REBLOG POPUP test FINISHED")
        }
        catch(e:Exception){
            printErrorMsg("testReblogPopup",e.message)
        }
    }

    // #4.5
    private fun testLikeBtn(post: UserPost){
        try{
            post.likePost()
            /*if(!post.isPostLiked())
                throw Exception("post should be liked")*/

            post.likePost()
            /*if(post.isPostLiked())
                throw Exception("post should not be liked")*/

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
            /*val reblogPopup = settings.openChangePopup()

            post.closePopup()

            val settings_delete = post.openSettingsPopup()
            settings_delete.delete()
            post.closePopup()*/

            post.closeSettingsPopup()
            printSuccessMsg("testSettingsBtn")
        }
        catch(e:Exception){
            printErrorMsg("testSettingsBtn", e.message)
        }
    }

    // #4.1.1
    private fun testAuthorPopup(){
        try{
            post.openAuthorPopup()
            post.closePopup()

            printSuccessMsg("testAuthorPopup")
        }
        catch(e:Exception){
            printErrorMsg("testAuthorPopup",e.message)
        }
    }

    // #4.1.3
    private fun testAuthorProfile(){
        try{
            post.openAuthorProfile()
            post.closePopup()

            printSuccessMsg("testAuthorProfile")
        }
        catch(e:Exception){
            printErrorMsg("testAuthorProfile",e.message)
        }
    }
}