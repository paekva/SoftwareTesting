package TestCases

import Pages.HomePage
import Pages.LoginPage
import org.openqa.selenium.WebDriver

class HomePageTest(private val driver: WebDriver){
    private var homePage: HomePage = HomePage(driver)


}