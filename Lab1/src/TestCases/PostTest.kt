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
        testSettingsBtn(post as CurrentUserPost)
        testReblogPopup()

        printInfoMsg("\tCURRENT USER POST tests FINISHED")
    }

    fun runOtherUserPostTests(){
        printInfoMsg("RECOMMENDED POST tests")
        testLikeBtn(post as UserPost)
        testAuthorPopup()
        testAuthorProfile()
        printInfoMsg("RECOMMENDED POST tests FINISHED")
    }

    private fun testSharePopup(){
        try{
            printInfoMsg("\nPOST test: SHARE POPUP test")

            val popup = post.openSharePopup()
            SharePopupTest(driver, post, popup)
                .runAllTests()

            printInfoMsg("\tPOST test: SHARE POPUP test FINISHED")
        }
        catch(e:Exception){
            printErrorMsg("testSharePopup",e.message)
        }
    }

    private fun testReplyPopup(){
        try{
            printInfoMsg("\nPOST test: REPLY POPUP test")

            val popup = post.openReplyPopup()
            ReplayPopupTest(driver, post, popup)
                .runAllTests()

            post.closePopup()
            printInfoMsg("\tPOST test: REPLY POPUP test FINISHED")
        }
        catch(e:Exception){
            printErrorMsg("testReplyPopup",e.message)
        }
    }

    private fun testReblogPopup(){
        try{
            post.openReblogPopup()
            ReblogPopupTest(driver).runAllTests()
            post.closePopup()
        }
        catch(e:Exception){
            printErrorMsg("testReblogPopup",e.message)
        }
    }

    private fun testLikeBtn(post: UserPost){
        try{
            post.likePost()

            printSuccessMsg("testLikeBtn")
        }
        catch(e:Exception){
            printErrorMsg("testLikeBtn", e.message)
        }
    }

    private fun testSettingsBtn(post: CurrentUserPost){
        try{
            post
                .openSettingsPopup()
                .delete()

            printSuccessMsg("testSettingsBtn")
        }
        catch(e:Exception){
            printErrorMsg("testSettingsBtn", e.message)
        }
    }

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