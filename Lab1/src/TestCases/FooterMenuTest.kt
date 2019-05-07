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
        // helpPageTest()
        developersPageTest()
        themesPageTest()
        jobsPageTest()
        newPageTest()
        privacyPageTest()
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

    private fun privacyPageTest(){
        try{
            footer!!.getToFooter()
            footer.openPrivacyPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/policy/privacy")
            driver.navigate().back()
            printSuccessMsg("privacyPageTest")
        }
        catch(e: Exception){
            printErrorMsg("privacyPageTest", e.message)
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

    private fun helpPageTest(){
        try{
            footer!!.getToFooter()
            footer.openHelpPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/help")
            driver.navigate().back()
            printSuccessMsg("helpPageTest")
        }
        catch(e: Exception){
            printErrorMsg("helpPageTest", e.message)
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

    private fun newPageTest(){
        try{
            footer!!.getToFooter()
            footer.openNewPage()
            waitForURLChange(driver, 20, "https://www.tumblr.com/policy/en/terms-of-service")
            driver.navigate().back()
            printSuccessMsg("newPageTest")
        }
        catch(e: Exception){
            printErrorMsg("newPageTest", e.message)
        }
    }

}