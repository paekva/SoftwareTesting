package PostTests

import PageTests.Login
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class PostTest(private val driver: ChromeDriver){
    private val auth = Login(driver)

    fun beforeTest(){
        val loginUrl = "https://www.tumblr.com/login"
        driver.get(loginUrl)
        auth.login("paekva@yandex.ru", "rfnz98grf")
    }

    fun testPostStructure(postContainer: WebElement){
        try
        {
            val postElement = postContainer.findElement(By.xpath("//ancestor::article"))
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

            val post = Post(postElement, driver)

            println("PostTest structure test was successful")
        }
        catch(e: NoSuchElementException){
            println("PostTest structure test has failed")
        }
    }

    fun testAuthorPostInfo(postContainer: WebElement){
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

    fun testPhotoZoom(postContainer: WebElement){
        try {
            val postElement = postContainer.findElement(By.xpath("//article[contains(@class,\"is_photo\")]"))
            val post = Post(postElement, driver)

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

    fun testTags(postContainer: WebElement){
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

            driver.get("https://www.tumblr.com/explore/trending")
            println("Hash tag test was successful")
        }
        catch(e:Exception){
            println("Hash tag test has failed: ${e.message}")
        }
    }

    fun testFooter(postContainer: WebElement){
        try{
            println("Performing footer test ... ")
            val postElement = postContainer.findElement(By.xpath("//ancestor::article"))
            val post = Post(postElement, driver)

            testNotesSection(post)
            testReplyPopup(post)
            // post.openSharePopup()

            println("Footer test was successful")
        }
        catch(e:Exception){
            println("Footer test has failed")
        }
    }

    private fun testNotesSection(post: Post){
        try{
            post.notes.click()

            val notesPopOver = driver.findElement(By.className("post-activity-popover"))
            testReplyPopupStructure(notesPopOver)

            post.closePopup()
            println("Notes test was successful")
        }
        catch(e: Exception){
            println("Notes test has failed")
        }
    }

    private fun testReplyPopup(post: Post){
        try{
            post.openReplyPopup()
            val replyPopup = post.replyPopup

            if(replyPopup == null) throw Exception("Unable to perform test without reply popup")

            testReplyPopupStructure(replyPopup)

            post.closePopup()
            println("Reply pop up test was successful")
        }
        catch(e:Exception){
            println("Reply pop up test has failed: ${e.message}")
        }
    }

    private fun testReplyPopupStructure(replyPopup: WebElement){
        val notesCount = replyPopup.findElement(By.tagName("span")).getAttribute("data-count")
        replyPopup.click()

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
    }

    fun afterTest(){
        auth.logout()
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