package PostTests

import Authentication
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class PostTest(private val driver: ChromeDriver){
    private val auth = Authentication(driver)

    private fun beforeTest(){
        val loginUrl = "https://www.tumblr.com/login"
        driver.get(loginUrl)
        auth.login("paekva@yandex.ru", "rfnz98grf")
    }

    fun test(){
        try {
            beforeTest()
        }
        catch(e: Exception){
            println("Some LOGIN problem. Check network connection or site availability")
            return
        }

        val url = "https://www.tumblr.com/explore/trending"
        driver.get(url)

        try {
            val postContainer = driver.findElement(By.className("posts-holder"))

            println("Performing post tests:")
            // testPostStructure(postContainer)
            // testAuthorPostInfo(postContainer)
            testPhotoZoom(postContainer)
            testTags(postContainer)
            testFooter(postContainer)
        }
        catch (e: NoSuchElementException){
            println("No post is available, cannot perform hole set of tests")
            return
        }

        afterTest()
    }

    private fun testPostStructure(postContainer: WebElement){
        try
        {
            val post = postContainer.findElement(By.xpath("//ancestor::article"))
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

            val newPost = Post(post, driver)

            println("PostTest structure test was successful")
        }
        catch(e: NoSuchElementException){
            println("PostTest structure test has failed")
        }
    }

    private fun testAuthorPostInfo(postContainer: WebElement){
        try{
            val postElement = postContainer.findElement(By.xpath("//ancestor::article"))
            val post = Post(postElement, driver)

            post.openAuthorPopup()

            val authorPopup = post.authorPopup
            authorPopup?.findElement(By.className("navigation"))
            authorPopup?.findElement(By.className("info_popover"))
            authorPopup?.findElement(By.className("recent_posts"))

            post.closeAuthorPopup()
            println("Author popup structure test was successful")
        }
        catch(e: Exception){
            println("Author popup structure test has failed")
        }
    }

    private fun testPhotoZoom(postContainer: WebElement){
        try {
            val postElement = postContainer.findElement(By.xpath("//ancestor::article[@class='is_photo']"))
            println("hello")
            val post = Post(postElement, driver)
            println(postElement)

            post.zoomInContent()

            val wait = WebDriverWait(driver, 5)
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.id("tumblr_lightbox_center_image")))

            post.zoomOutContent()
            println("Photo zoom test was successful")
        }
        catch(e:Exception){
            println("Photo zoom test has failed")
        }
    }

    private fun testTags(postContainer: WebElement){
        try {
            val postElement = postContainer.findElement(By.xpath("//ancestor::article"))
            val post = Post(postElement, driver)

            val tag = post.tags[0]
            val tagValues = tag.text.split(' ')

            var url = "https://www.tumblr.com/search/"
            url += tagValues.reduceIndexed{ idx, acc, it ->
                if (idx == 0) "$acc$it" else  "$acc+$it"
            }

            tag.click()
            val wait = WebDriverWait(driver, 10)
            wait.until(ExpectedConditions.urlToBe(url))

            println(tag.text)

            driver.get("https://www.tumblr.com/explore/trending")
            println("Hash tag test was successful")
        }
        catch(e:Exception){
            println("Hash tag test has failed")
        }
    }

    private fun testFooter(postContainer: WebElement){
        try{
            println("Performing footer test ... ")
            val postElement = postContainer.findElement(By.xpath("//ancestor::article"))
            // val footer = post.findElement(By.className("post_footer"))
            // val notesBlock = footer.findElement(By.className("post_notes"))

            val post = Post(postElement, driver)
            post.openSharePopup()
            post.openReplyPopup()

            // testNotesPopUp(notesBlock)

            println("Footer test was successful")
        }
        catch(e:Exception){
            println("Footer test has failed")
        }
    }

    private fun testNotesPopUp(notesBlock: WebElement){
        try{
            val notesCount = notesBlock.findElement(By.tagName("span")).getAttribute("data-count")
            notesBlock.click()

            val wait = WebDriverWait(driver, 10)
            val popUp = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post-activity-popover")))

            var notesCountMsg = popUp.findElement(By.className("primary-message")).text
            notesCountMsg = notesCountMsg.replace("[^0-9]", "")

            if(notesCount !== notesCountMsg){
                println(notesCount)
                println(notesCountMsg)
                throw Exception("Notes count in title and in notes section in post are different")
            }

            popUp.findElement(By.className("main-container"))
            popUp.findElement(By.className("footer-container"))
            println("Pop up notes test was successful")
        }
        catch(e:Exception){
            println("Pop up notes test has failed: ${e.message}")
        }
    }

    private fun afterTest(){
        auth.logout()
    }

    fun postcontrolstest(controlsBlock: WebElement){
        try{
            println("Performing footer controls tests...")
            val shareBtn = controlsBlock.findElement(By.className("share"))
            shareBtnTest(shareBtn)

            val replyBtn = controlsBlock.findElement(By.className("reply"))
            val reblogBtn = controlsBlock.findElement(By.className("reblog"))
            val likeBtn = controlsBlock.findElement(By.className("like"))

        }
        catch(e: Exception){
            println("Footer controls test has failed")
        }
    }

    private fun shareBtnTest(shareBtn: WebElement){
        try{
            shareBtn.click()
            val wait = WebDriverWait(driver, 5)
            val shareBlock = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("popover--messaging-share-post")))

            println("we have a share popup")
            val searchField = shareBlock.findElement(By.className("messaging-share-post-search"))
            println("found 1")
            val mainPart = shareBlock.findElement(By.className("messaging-share-post-main"))
            println("found 2")
            val shareOptionsWrapper = shareBlock.findElement(By.className("messaging-share-post-external-networks-wrapper"))

            println("checked structure")
            val options = shareOptionsWrapper.findElements(By.tagName("a"))
            for(option in options){
                wait.until(ExpectedConditions.elementToBeClickable(option))
            }

            println("before insert")
            // INSERT CHECK
            options[1].click()
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")))
            val holder = driver.findElementByClassName("messaging-share-post-external-network-subview-wrapper")
            val backBtn = holder.findElement(By.className("back-button"))
            backBtn.click()

            // EMAIL SHARE


            println("Footer controls: SHARE BUTTON test was successful")
        }
        catch (e: Exception){
            println("Footer controls: SHARE BUTTON test has failed")
        }
    }
}