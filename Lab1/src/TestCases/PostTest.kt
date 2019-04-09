package TestCases

import Elements.Post
import org.openqa.selenium.WebDriver

class PostTest(private val driver: WebDriver, private val post: Post){

    fun testAuthorPostInfo(){
        post.openAuthorPopup()

        if(post.authorPopup == null)
            throw Exception()

        post.closePopup()
    }
}