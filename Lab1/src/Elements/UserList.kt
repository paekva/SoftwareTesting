package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class UserList(private val driver: WebDriver, private val userListContainer: WebElement) {

    val list: ArrayList<User> = arrayListOf()
    init{
        val listOfElements = userListContainer.findElements(By.className("item"))
        listOfElements.forEach {
            val url = it.findElement(By.xpath("//a[1]"))
            val btn = it.findElement(By.xpath("//a[2]"))
            list.add(User(url, btn))
        }
    }
}

class User(val url: WebElement, val btn: WebElement)
