import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Post(val driver: ChromeDriver){
    private val auth = Authentication("paekva@yandex.ru", "rfnz98grf", driver)

    private fun beforeTest(){
        val loginUrl = "https://www.tumblr.com/login"
        driver.get(loginUrl)
        auth.login()
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
            val post = postContainer.findElement(By.xpath("//ancestor::article"))

            testPostStructure(post)
            testAuthorPostInfo(post)
            testPhotoZoom(post)
            testTags(post)
            testFooter(post)
        }
        catch (e: NoSuchElementException){
            println("No post is available, cannot perform hole set of tests")
            return
        }

        afterTest()
    }

    private fun testPostStructure(post: WebElement){
        try
        {
        val wait = WebDriverWait(driver, 5)
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_header")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_content")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_tags")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_footer")))
        }
        catch(e: NoSuchElementException){
            println("Invalid post structure")
        }
    }

    private fun testAuthorPostInfo(post: WebElement){
        try{
            val author = post.findElement(By.className("tumblelog_info"))
            val hover = Actions(driver)
            hover.moveToElement(author).build().perform()

            val wait = WebDriverWait(driver, 5)
            wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("tumblelog_popover")))
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("navigation")))
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("info_popover")))
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("recent_posts")))

            val header = driver.findElement(By.className("l-header"))
            header.click()
        }
        catch(e: NoSuchElementException){
            println("Invalid author popup structure")
        }

    }

    private fun testPhotoZoom(post: WebElement){
        try {
            val img = post.findElement(By.className("photo"))
            img.click()
            val wait = WebDriverWait(driver, 5)
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.id("tumblr_lightbox_center_image")))

            img.findElement(By.xpath("//following::input[1]")).click()
        }
        catch(e:Exception){
            println("Post test: photo zoom test has failed")
        }
        println("Post test: photo zoom test was successful")

    }

    private fun testTags(post: WebElement){
        try {
            val postUrl = driver.currentUrl
            val tagHolder = post.findElement(By.className("post_tags_inner"))
            val tag = tagHolder.findElement(By.tagName("a"))
            val tagValues = tag.text.split(' ')

            var url = "https://www.tumblr.com/search/"
            url += tagValues.reduceIndexed{ idx, acc, it ->
                if (idx == 0) "$acc$it" else  "$acc+$it" }
            tag.click()

            if(driver.currentUrl !== url){
                println(url)
                throw Exception("Hash tag check test failed")
            }

            driver.get(postUrl)
        }
        catch(e:Exception){
            println("Post test: hash tag test has failed")
        }
        println("Post test: hash tag test was successful")
    }

    private fun testFooter(post: WebElement){
        try{
            val notesBlock = post.findElement(By.className("post_notes"))
            val controlsBlock = post.findElement(By.className("post_controls"))
            testNotesPopUp(notesBlock)

            val controlsTest = PostControls(controlsBlock)
            controlsTest.test()
        }
        catch(e:Exception){
            println("Post test: footer test has failed")
        }
        println("Post test: footer test was successful")
    }

    private fun testNotesPopUp(notesBlock: WebElement){
        try{
            val notesCount = notesBlock.findElement(By.tagName("span")).getAttribute("title")
            notesBlock.click()

            val popUp = driver.findElementByClassName("post-activity can-reply")
            val notesCountMsg = popUp.findElement(By.className("primary-message")).text

            if(notesCount !== notesCountMsg){
                throw Exception("Notes count in title and in notes section in post are different")
            }

            popUp.findElement(By.className("main-container"))
            popUp.findElement(By.className("footer-container"))
        }
        catch(e:Exception){
            println("Post footer: pop up notes test has failed")
        }
        println("Post footer: pop up notes test was successful")
    }

    private fun afterTest(){
        auth.logout()
    }
}