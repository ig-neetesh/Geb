import org.openqa.selenium.firefox.FirefoxDriver

driver = {
    def firefox = new FirefoxDriver()
    firefox.manage().window().maximize()
    return firefox
}

baseUrl = "http://google.com"

unexpectedPages = [PageNotFoundPage]
