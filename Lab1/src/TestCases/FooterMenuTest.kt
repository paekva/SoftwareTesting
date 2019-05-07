package testCases

import Utils.*
import elements.Footer
import org.openqa.selenium.WebDriver

class FooterMenuTest(private val driver: WebDriver) {

    private val footer: Footer? = Footer(driver)
    fun runAllTests(){
        printInfoMsg("FOOTER tests")
        aboutPageTest()
        appsPageTest()
        tumblrPageTest()
        developersPageTest()
        themesPageTest()
        jobsPageTest()
        termsOfServiceTest()
        printInfoMsg("FOOTER tests FINISHED")
    }

    private fun aboutPageTest(){
        try{
            footer!!.getToFooter()
            footer.openAboutPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/about")
            driver.navigate().back()
            printSuccessMsg("aboutPageTest")
        }
        catch(e: Exception){
            printErrorMsg("aboutPageTest", e.message)
        }
    }

    private fun appsPageTest(){
        try{
            footer!!.getToFooter()
            footer.openAppsPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/apps")
            driver.navigate().back()
            printSuccessMsg("appsPageTest")
        }
        catch(e: Exception){
            printErrorMsg("appsPageTest", e.message)
        }
    }

    private fun tumblrPageTest(){
        try{
            footer!!.getToFooter()
            footer.openTumblrPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/dashboard")
            driver.navigate().back()
            printSuccessMsg("tumblrPageTest")
        }
        catch(e: Exception){
            printErrorMsg("tumblrPageTest", e.message)
        }
    }

    private fun developersPageTest(){
        try{
            footer!!.getToFooter()
            footer.openDevelopersPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/developers")
            driver.navigate().back()
            printSuccessMsg("developersPageTest")
        }
        catch(e: Exception){
            printErrorMsg("developersPageTest", e.message)
        }
    }

    private fun themesPageTest(){
        try{
            footer!!.getToFooter()
            footer.openThemesPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/themes/")
            driver.navigate().back()
            printSuccessMsg("themesPageTest")
        }
        catch(e: Exception){
            printErrorMsg("themesPageTest", e.message)
        }
    }

    private fun jobsPageTest(){
        try{
            footer!!.getToFooter()
            footer.openJobsPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/jobs")
            driver.navigate().back()
            printSuccessMsg("jobsPageTest")
        }
        catch(e: Exception){
            printErrorMsg("jobsPageTest", e.message)
        }
    }

    private fun termsOfServiceTest(){
        try{
            footer!!.getToFooter()
            footer.openNewPage()
            printSuccessMsg("termsOfServiceTest")
        }
        catch(e: Exception){
            printErrorMsg("termsOfServiceTest", e.message)
        }
    }

}